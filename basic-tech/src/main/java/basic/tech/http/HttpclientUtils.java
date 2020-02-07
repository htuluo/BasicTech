package basic.tech.http;

import io.netty.util.Constant;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpclientUtils {
    private static CloseableHttpClient client = null;



    private static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    static {
        manager.setMaxTotal(10);
        manager.setDefaultMaxPerRoute(2);
        client = HttpClients.custom().setConnectionManager(manager).build();
    }

    public static String getStringFromUrl(String url, Map<String, String> parames){
        CloseableHttpResponse closeableHttpResponse = get(url, parames);
        return getStringFromResponse(closeableHttpResponse);
    }
    public static CloseableHttpResponse get(String url, Map<String, String> parames) {
        StringBuilder paramsStr = new StringBuilder();
        try {
            if (parames != null && parames.size() > 0) {
                for (Map.Entry<String, String> entry : parames.entrySet()) {
                    paramsStr.append(entry.getKey()).append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                            .append("&");
                }
            }
            url+="?"+paramsStr.toString();
            HttpGet get=new HttpGet(url);
            CloseableHttpResponse httpResponse = client.execute(get);
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {

        }


    }

    public static  String getStringFromResponse(CloseableHttpResponse response){
        if (response==null){
            return null;
        }
        try {
            return  EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }



}
