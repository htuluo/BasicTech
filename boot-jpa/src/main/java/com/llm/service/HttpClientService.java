package com.llm.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/1/18
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Service
public class HttpClientService {
    @Scheduled(cron = "* 0/5 * * * *")
    public void testHttp() {


    }

    public static void postWithStream () {
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
