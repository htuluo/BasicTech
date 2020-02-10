package basic.tech.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.*;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
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


/**
 * httpclient请求utils
 *
 * @author luolm
 * @date 2020-02-10
 */
public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final int CONNECT_TIMEOUT = 5000;//设置超时毫秒数

    private static final int SOCKET_TIMEOUT = 10000;//设置传输毫秒数

    private static final int REQUESTCONNECT_TIMEOUT = 3000;//获取连接池请求超时毫秒数

    private static final int CONNECT_TOTAL = 200;//最大连接数

    private static final int CONNECT_ROUTE = 20;//设置每个路由的基础连接数

    private static final int VALIDATE_TIME = 30000;//设置重用连接时间

    private static final String RESPONSE_CONTENT = "通信失败";

    private static PoolingHttpClientConnectionManager manager = null;

    private static CloseableHttpClient client = null;
    private static RequestConfig requestConfigDefault;

    static {
        ConnectionSocketFactory csf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory lsf = createSSLConnSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", csf).register("https", lsf).build();
        manager = new PoolingHttpClientConnectionManager(registry);
        manager.setMaxTotal(CONNECT_TOTAL);
        manager.setDefaultMaxPerRoute(CONNECT_ROUTE);
        manager.setValidateAfterInactivity(VALIDATE_TIME);
        SocketConfig config = SocketConfig.custom().setSoTimeout(SOCKET_TIMEOUT).build();
        manager.setDefaultSocketConfig(config);
        requestConfigDefault = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(REQUESTCONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();
        client = HttpClients.custom().setConnectionManager(manager).setDefaultRequestConfig(requestConfigDefault).setRetryHandler(
                //实现了HttpRequestRetryHandler接口的
                //public boolean retryRequest(IOException exception, int executionCount, HttpContext context)方法
                (exception, executionCount, context) -> {
                    if (executionCount >= 1)
                        return false;
                    if (exception instanceof NoHttpResponseException)//如果服务器断掉了连接那么重试
                        return true;
                    if (exception instanceof SSLHandshakeException)//不重试握手异常
                        return false;
                    if (exception instanceof InterruptedIOException)//IO传输中断重试
                        return true;
                    if (exception instanceof UnknownHostException)//未知服务器
                        return false;
                    if (exception instanceof ConnectTimeoutException)//超时就重试
                        return true;
                    if (exception instanceof SSLException)
                        return false;

                    HttpClientContext cliContext = HttpClientContext.adapt(context);
                    HttpRequest request = cliContext.getRequest();
                    if (!(request instanceof HttpEntityEnclosingRequest))
                        return true;
                    return false;
                }).build();
        if (manager != null && manager.getTotalStats() != null) {
            log.info("客户池状态：" + manager.getTotalStats().toString());
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
     * 读取response为字符串
     *
     * @param method
     * @return
     */
    private static String getResponseStr(HttpRequestBase method) {
        return getResponseStrWithRequestConfig(method, null);
    }

    /**
     * 读取字符串，带有requestConfig参数
     *
     * @param method
     * @param config
     * @return
     */
    private static String getResponseStrWithRequestConfig(HttpRequestBase method, RequestConfig config) {
        CloseableHttpResponse response = getResponseWithRequestConfig(method, config);
        return parseResponseToStr(response, method);
    }

    /**
     * 获取请求的response
     *
     * @param method
     * @param config
     * @return
     */
    public static CloseableHttpResponse getResponseWithRequestConfig(HttpRequestBase method, RequestConfig config) {
        HttpClientContext context = HttpClientContext.create();
        CloseableHttpResponse response = null;
        try {
            if (config != null) {
                RequestConfig.Builder requestBuilder = RequestConfig.copy(requestConfigDefault);
                if (-1 != config.getConnectTimeout()) {
                    requestBuilder.setConnectTimeout(config.getConnectTimeout());
                }
                if (-1 != config.getSocketTimeout()) {
                    requestBuilder.setSocketTimeout(config.getSocketTimeout());
                }
                if (-1 != config.getConnectionRequestTimeout()) {
                    requestBuilder.setConnectionRequestTimeout(config.getConnectionRequestTimeout());
                }
                RequestConfig requestConfig = requestBuilder.build();
                context.setRequestConfig(requestConfig);
            }
            response = client.execute(method, context);//执行GET/POST请求

        } catch (ConnectTimeoutException cte) {
            log.error("请求连接超时，由于-{},", cte.getLocalizedMessage(), cte);
        } catch (SocketTimeoutException ste) {
            log.error("请求通信超时，由于-{},", ste.getLocalizedMessage(), ste);
        } catch (ClientProtocolException cpe) {
            log.error("协议错误（比如构造HttpGet对象时传入协议不对(将'http'写成'htp')or响应内容不符合），由于-{}, ", cpe.getLocalizedMessage(), cpe);
        } catch (IOException ie) {
            log.error("实体转换异常或者网络异常， 由于-{},", ie.getLocalizedMessage(), ie);
        } catch (Exception ie) {
            log.error("出现异常， 由于-{},", ie.getLocalizedMessage(), ie);
        } finally {
//            if (method != null) {
//                method.releaseConnection();
//            }

        }

        return response;
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

    /**
     * 关闭response
     *
     * @param response
     * @param method
     */
    public static void closeResponse(CloseableHttpResponse response, HttpRequestBase method) {
        try {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
//            if (method != null) {
//                method.releaseConnection();
//            }
        } catch (IOException e) {
            log.error("closeResponse catch error ,msg-{}", e.getMessage(), e);
//            e.printStackTrace();
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
    public static CloseableHttpResponse doPostWithHeaderJsonString(String url, String jsonString, Map<String, String> headers, RequestConfig config) {
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(jsonString, Consts.UTF_8);
        } catch (Exception e) {
            log.error("StringEntity catch error,msg-{}", e.getLocalizedMessage(), e);
        }
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
    public static CloseableHttpResponse doPostWithStringEntity(String url, StringEntity stringEntity, Map<String, String> headers, RequestConfig config) {
        HttpPost httpPost = new HttpPost(url);
        if (null != headers && !headers.isEmpty()) {
            headers.entrySet().stream().forEach(item -> {
                httpPost.addHeader(item.getKey(), item.getValue());
            });
        }
        stringEntity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setEntity(stringEntity);
        return getResponseWithRequestConfig(httpPost, config);
    }

    public static String get(String url) {
        HttpGet get = new HttpGet(url);
        return getResponseStr(get);
    }


    public static String doPost(String url, String jsonString) {
        HttpPost post = new HttpPost(url);
        if (StringUtils.isNotBlank(jsonString)) {
            post.addHeader("Content-Type", "application/json");
        }
        post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
        return getResponseStr(post);
    }

    /**
     * 需要UrlEncodedFormEntity传参的post请求
     *
     * @param url
     * @param param
     * @param headers
     * @return
     */
    public static String doPostWithParam(String url, Map<String, String> param, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);
        try {
            List<NameValuePair> params = new ArrayList<>();
            if (null != param && !param.isEmpty()) {
                param.entrySet().stream().forEach(item -> {
                    params.add(new BasicNameValuePair(item.getKey(), item.getValue()));
                });
            }
            if (null != headers && !headers.isEmpty()) {
                headers.entrySet().stream().forEach(item -> {
                    post.addHeader(item.getKey(), item.getValue());
                });
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            post.setEntity(urlEncodedFormEntity);
        } catch (Exception e) {
            log.error("doPostWithParam catch error， msg-{}, " + e.getLocalizedMessage(), e);
        }
        return getResponseStr(post);
    }
}
