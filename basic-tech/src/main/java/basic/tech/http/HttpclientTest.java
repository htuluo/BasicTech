package basic.tech.http;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author luolm
 * @description:
 * @createTime： 2020/1/17 19:20
 */
public class HttpclientTest {
    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        postWithEntityUtils();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }


        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void post() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://www.baidu.com/");

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, Charset.forName("utf-8")) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     * util消費
     */
    public static void postWithEntityUtils() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            HttpGet httpget = new HttpGet("http://www.baidu.com/");
            httpResponse = httpclient.execute(httpget);

            System.out.println("Executing request " + httpget.getRequestLine());


            // Create a custom response handler
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------before EntityUtils--------------------------------");

//            String responseBody = EntityUtils.toString(httpResponse.getEntity());
//            System.out.println("----------after EntityUtils------------------------------");
//            System.out.println(responseBody);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            httpResponse.close();
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     *
     */
    public static void postWithStream() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://www.baidu.com/");

            System.out.println("Executing request " + httpget.getRequestLine());

            CloseableHttpResponse httpResponse = httpclient.execute(httpget);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream inputStream = httpEntity.getContent();
            Reader reader = new InputStreamReader(inputStream, Charset.forName("utf-8"));
            char[] tmp = new char[1024];
            int l;
            int i = (int) httpEntity.getContentLength();
            if (i < 0) {
                i = 4096;
            }
            CharArrayBuffer buffer = new CharArrayBuffer(i);
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }

            String var9 = buffer.toString();
            // Create a custom response handler
            System.out.println("----------------------------------------");
            System.out.println(var9);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
