//package com.llm.config;
//
//import org.apache.http.StatusLine;
//import org.elasticsearch.client.Request;
//import org.elasticsearch.client.Response;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.boot.json.JsonParser;
//import org.springframework.boot.json.JsonParserFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StreamUtils;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
///*
// *@author:luoleiming
// *@date:2022/6/30 20:03
// *@description: 重写健康检查
// */
//@Configuration
//public class ElasticsearchHealthIndicator implements HealthIndicator {
//    private final JsonParser jsonParser;
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//
//    public ElasticsearchHealthIndicator() {
//        this.jsonParser = JsonParserFactory.getJsonParser();
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public Health health() {
//        Health.Builder builder = new Health.Builder();
//        try {
//            Response response = restHighLevelClient.getLowLevelClient().performRequest(new Request("GET", "/_cluster/health/"));
//            StatusLine statusLine = response.getStatusLine();
//            if (statusLine.getStatusCode() != 200) {
//                builder.down();
//                builder.withDetail("statusCode", statusLine.getStatusCode());
//                builder.withDetail("reasonPhrase", statusLine.getReasonPhrase());
//            } else {
//                InputStream inputStream = response.getEntity().getContent();
//                Throwable var5 = null;
//
//                try {
//                    this.doHealthCheck(builder, StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
//                } catch (Throwable var14) {
//                    var5 = var14;
//                    throw var14;
//                } finally {
//                    if (inputStream != null) {
//                        if (var5 != null) {
//                            try {
//                                inputStream.close();
//                            } catch (Throwable var13) {
//                                var5.addSuppressed(var13);
//                            }
//                        } else {
//                            inputStream.close();
//                        }
//                    }
//
//                }
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return builder.build();
//    }
//
//    private void doHealthCheck(Health.Builder builder, String json) {
//        Map<String, Object> response = this.jsonParser.parseMap(json);
//        String status = (String) response.get("status");
//        if ("red".equals(status)) {
//            builder.outOfService();
//        } else {
//            builder.up();
//        }
//
//        builder.withDetails(response);
//    }
//}
