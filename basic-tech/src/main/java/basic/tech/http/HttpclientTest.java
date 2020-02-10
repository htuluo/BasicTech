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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author luolm
 * @description:
 * @createTime： 2020/1/17 19:20
 */
public class HttpclientTest {
    public static void main(String[] args) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "InAppDev=E9ABEB35FF8F709C561D94567A3632CE; HW_id_developer_huawei_com/consumer_developer_huawei_com=6422384e79ce4143989672158c9b2874; HW_idts_developer_huawei_com/consumer_developer_huawei_com=1581321278431; HW_idn_developer_huawei_com/consumer_developer_huawei_com=08ca286bd859467f9ccff31b38981b70; X-HD-SESSION=7b3f6ad5-2258-5bbe-a75f-9fd3359eb140; x-uid=900086000000029925; x-siteId=1; x-hd-grey=0; x-country=CN; x-userType=2; HW_refts_developer_huawei_com/consumer_developer_huawei_com=1581321286080; urlBeforeLogin=https%3A%2F%2Fdeveloper.huawei.com%2Fconsumer%2Fcn%2Fmonetize; state=89592; HuaweiID_CAS_ISCASLOGIN=true; CASLOGINSITE=1; LOGINACCSITE=1; developer_userinfo=%7B%22siteid%22%3A%221%22%2C%22expiretime%22%3A%2220200210T092049Z%22%2C%22csrftoken%22%3A%2205ABAB23744EC5C2D22F84F1F0CE6CE582FE99401534DFA710%22%7D; HW_idvc_developer_huawei_com/consumer_developer_huawei_com=5; HW_viewts_developer_huawei_com/consumer_developer_huawei_com=1581322852637");
        headers.put("accessToken", "wLdVSMvX9kzbUhtuQDKeDe6QnbDq4aRBYkhmwypu4fa5UK8yGwlTiSXateo/1rx1KpC2j9P0ilLzxDLEn2T4xosmdIVlhY4wHxcIVq/GGjBqdBBwGkvR8tA1wCfOLSurzDG/SRLe2DthUu5w2gfwGw==");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.put("Referer", "https://developer.huawei.com/consumer/cn/service/ads/publisher/html/index.html?type=1");
        String json = "{\"originType\":2,\"siteId\":\"\",\"adType\":\"\",\"adid\":\"\",\"profitType\":\"\",\"countryCode\":\"\",\"startDate\":\"2020-02-04\",\"endDate\":\"2020-02-10\",\"publisherCurrency\":\"CNY\"}";
        Map<String,String> param=new HashMap<>();
        param.put("keyword","");
        param.put("status","30");
        param.put("number","100");
        param.put("curpage","1");
        String url = "https://developer.huawei.com/consumer/cn/service/ads/publisher/media/getMediaList";
        String response = HttpClientUtil.doPostWithParam(url, param, headers);
        System.out.println("+++++"+response);


//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                String url = "https://docs.google.com/forms/u/0/";
//                System.out.println("=======+++==" + url);
//                String stringFromUrl = HttpClientUtil.get(url, null);
//                System.out.println(stringFromUrl);
//
//
//            }).start();
//
//        }


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void multiThreadTest() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String url = "http://127.0.0.1:8080/test/get?str=0";
                    System.out.println("=====" + url);
                    String stringFromUrl = HttpClientUtil.get(url);
                    System.out.println(stringFromUrl);


                }
            }).start();

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
