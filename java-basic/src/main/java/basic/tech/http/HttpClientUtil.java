package basic.tech.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * httpclient请求utils
 * 使用httpclient4.5.3版本
 *
 * @author luolm
 */
public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final int CONNECT_TIMEOUT = 5000;//设置超时毫秒数

    private static final int SOCKET_TIMEOUT = 30000;//设置传输毫秒数

    private static final int REQUESTCONNECT_TIMEOUT = 3000;//获取连接池请求超时毫秒数

    private static final int CONNECT_TOTAL = 30;//最大连接数

    private static final int CONNECT_ROUTE = 50;//设置每个路由的基础连接数

    private static final int VALIDATE_TIME = 10000;//设置重用连接时间

    private static PoolingHttpClientConnectionManager MANAGER = null;

    private static CloseableHttpClient CLIENT = null;
    private static RequestConfig REQUEST_CONFIG_DEFAULT;

    static {
        ConnectionSocketFactory csf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory lsf = createSSLConnSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", csf).register("https", lsf).build();
        MANAGER = new PoolingHttpClientConnectionManager(registry);
        MANAGER.setMaxTotal(CONNECT_TOTAL);
        MANAGER.setDefaultMaxPerRoute(CONNECT_ROUTE);
        MANAGER.setValidateAfterInactivity(VALIDATE_TIME);
        SocketConfig config = SocketConfig.custom().setSoTimeout(SOCKET_TIMEOUT).build();
        MANAGER.setDefaultSocketConfig(config);
        REQUEST_CONFIG_DEFAULT = RequestConfig.custom()
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setConnectionRequestTimeout(REQUESTCONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT).build();
        CLIENT = HttpClients.custom().setConnectionManager(MANAGER).setDefaultRequestConfig(REQUEST_CONFIG_DEFAULT).setRetryHandler(
            //实现了HttpRequestRetryHandler接口的
            //public boolean retryRequest(IOException exception, int executionCount, HttpContext context)方法
            (exception, executionCount, context) -> {
                if (executionCount >= 1) {
                    //重试次数1次，先不重试
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    //如果服务器断掉了连接那么重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {
                    //不重试握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    //IO传输中断重试
                    return true;
                }
                if (exception instanceof UnknownHostException) {
                    //未知服务器
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    //超时就重试
                    return true;
                }
                if (exception instanceof SSLException) {
                    return false;
                }

                HttpClientContext cliContext = HttpClientContext.adapt(context);
                HttpRequest request = cliContext.getRequest();
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }).build();
        if (MANAGER != null && MANAGER.getTotalStats() != null) {
            log.info("客户池状态：" + MANAGER.getTotalStats().toString());
        }
    }

    /**
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        SSLContext context;
        try {
            X509TrustManager x509m = new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
            };
            context = SSLContext.getInstance(SSLConnectionSocketFactory.SSL);
            // 初始化SSLContext实例
            try {
                //最关键的必须有这一步，否则抛出SSLContextImpl未被初始化的异常
                context.init(null,
                    new TrustManager[]{x509m},
                    new java.security.SecureRandom());
            } catch (KeyManagementException e) {
                log.warn("SSL上下文初始化失败， 由于 {}", e.getLocalizedMessage());
            }
            sslsf = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            log.warn("SSL上下文创建失败，由于 {}", e.getLocalizedMessage());
        }
        return sslsf;
    }

    /**
     * 读取字符串，带有requestConfig参数
     *
     * @param method
     * @param headers
     * @param config
     * @return
     */
    private static String getResponseStrWithRequestConfig(HttpRequestBase method, Map<String, String> headers, RequestConfig config) throws Exception {
        CloseableHttpResponse response = getResponseWithRequestConfig(method, headers, config);
        return parseResponseToStr(response, method);
    }

    /**
     * 获取请求的response
     *
     * @param method
     * @param headers
     * @param config
     * @return
     */
    public static CloseableHttpResponse getResponseWithRequestConfig(HttpRequestBase method, Map<String, String> headers, RequestConfig config) throws Exception {
        HttpClientContext context = HttpClientContext.create();
        CloseableHttpResponse response = null;
        try {
            if (config != null) {
                RequestConfig.Builder requestBuilder = RequestConfig.copy(REQUEST_CONFIG_DEFAULT);
                if (-1 != config.getConnectTimeout()) {
                    requestBuilder.setConnectTimeout(config.getConnectTimeout());
                }
                if (-1 != config.getSocketTimeout()) {
                    requestBuilder.setSocketTimeout(config.getSocketTimeout());
                }
                if (-1 != config.getConnectionRequestTimeout()) {
                    requestBuilder.setConnectionRequestTimeout(config.getConnectionRequestTimeout());
                }
                if(!config.getCookieSpec().isEmpty()){
                    requestBuilder.setCookieSpec(config.getCookieSpec());
                }
                RequestConfig requestConfig = requestBuilder.build();
                context.setRequestConfig(requestConfig);

            }
            if (null != headers && !headers.isEmpty()) {
                headers.entrySet().stream().forEach(item -> {
                    method.addHeader(item.getKey(), item.getValue());
                });
            }
            response = CLIENT.execute(method, context);//执行GET/POST请求
        }
//        catch (ConnectTimeoutException cte) {
//            log.error("请求连接超时，由于-{},", cte.getLocalizedMessage(), cte);
//        } catch (SocketTimeoutException ste) {
//            log.error("请求通信超时，由于-{},", ste.getLocalizedMessage(), ste);
//        } catch (ClientProtocolException cpe) {
//            log.error("协议错误（比如构造HttpGet对象时传入协议不对(将'http'写成'htp')or响应内容不符合），由于-{}, ", cpe.getLocalizedMessage(), cpe);
//        }
        catch (IOException ie) {
            log.warn("实体转换异常或者网络异常， 由于-{},", ie.getLocalizedMessage(), ie);
            throw new IOException(ie);
        } catch (Exception ie) {
            log.warn("出现异常， 由于-{},", ie.getLocalizedMessage(), ie);
            throw new Exception(ie);
        } finally {

        }
        return response;
    }

    /**
     * 关闭response
     *
     * @param response
     */
    public static void closeResponse(CloseableHttpResponse response) {
        closeResponse(response, null);
    }

    /**
     * 关闭response,带有httpMethod
     *
     * @param response
     * @param method   httpMethod
     */

    public static void closeResponse(CloseableHttpResponse response, HttpRequestBase method) {
        try {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
            if (method != null) {
                method.releaseConnection();
            }
        } catch (IOException e) {
            log.error("closeResponse catch error ,msg-{}", e.getMessage(), e);
        }
    }

    /**
     * 字符串的post
     *
     * @param url
     * @param jsonString
     * @param headers
     * @param config
     * @return
     */
    public static CloseableHttpResponse doPostWithHeaderJsonString(String url, String jsonString, Map<String, String> headers, RequestConfig config) throws Exception {
        StringEntity stringEntity = null;
        stringEntity = new StringEntity(jsonString, Consts.UTF_8);
        return doPostWithStringEntity(url, stringEntity, headers, config);
    }


    /**
     * SringEntity 的post
     *
     * @param url
     * @param stringEntity
     * @param headers
     * @param config
     * @return
     */
    public static CloseableHttpResponse doPostWithStringEntity(String url, StringEntity
        stringEntity, Map<String, String> headers, RequestConfig config) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        stringEntity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setEntity(stringEntity);
        return getResponseWithRequestConfig(httpPost, headers, config);
    }

    /**
     * Get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        return getResponseStrWithRequestConfig(get, null, null);
    }

    /**
     * 短连接请求
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGetWithHeaderCloseConnection(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "close");
        return getResponseStrWithRequestConfig(get, header, null);
    }

    /**
     * 短连接请求
     * @param url
     * @param socketTimeOut
     * @return
     * @throws Exception
     */
    public static String doGetWithTimeOut(String url, int socketTimeOut) throws Exception {
        HttpGet get = new HttpGet(url);
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "close");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(socketTimeOut * 1000).build();
        return getResponseStrWithRequestConfig(get, header, requestConfig);
    }


    /**
     * 带headers的GET
     *
     * @param url
     * @param headers
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, String> headers) throws Exception {
        HttpGet get = new HttpGet(url);
        return getResponseStrWithRequestConfig(get, headers, null);
    }


    /**
     * json post请求
     *
     * @param url
     * @param jsonString
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String jsonString) throws Exception {
        HttpPost post = new HttpPost(url);
        if (StringUtils.isNotBlank(jsonString)) {
            post.addHeader("Content-Type", "application/json");
        }
        post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
        return getResponseStrWithRequestConfig(post, null, null);
    }

    /**
     * 需要UrlEncodedFormEntity传参的post请求
     *
     * @param url
     * @param param
     * @param headers
     * @return
     */
    public static String doPostWithUrlParam(String url, Map<String, String> param, Map<String, String> headers) throws Exception {
        HttpPost post = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<>();
        if (null != param && !param.isEmpty()) {
            param.entrySet().stream().forEach(item -> {
                params.add(new BasicNameValuePair(item.getKey(), item.getValue()));
            });
        }
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        post.setEntity(urlEncodedFormEntity);

        return getResponseStrWithRequestConfig(post, headers, null);
    }


    /**
     * 读取response为字符
     *
     * @param response
     * @return
     */
    public static String parseResponseToStr(CloseableHttpResponse response, HttpRequestBase method) {
        String content = null;
        try {
            if (null == response) {
                return content;
            }
            HttpEntity entity = response.getEntity();//获取响应实体
            if (entity != null) {
                Charset charset = ContentType.getOrDefault(entity).getCharset();
                content = EntityUtils.toString(entity, charset);
            }
        } catch (IOException e) {
            log.error("parseResponseToStr catch error ,msg-{}", e.getMessage(), e);
        } finally {
            //关闭连接！！！
            closeResponse(response, method);
        }
        return content;
    }
}


