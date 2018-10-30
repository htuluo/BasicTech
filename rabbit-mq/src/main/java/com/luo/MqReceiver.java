package com.luo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/10/29
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class MqReceiver {
    private final static String QUEUE_NAME = "test_queue";
    private final static String QUEUE_IP = "10.0.129.140";
    private final static int QUEUE_PORT = 5672;
    private final static String QUEUE_USER = "ygph_dev";
    private final static String QUEUE_PWD = "ygph_dev";
    private final static String QUEUE_V_HOST = "/ygph_dev";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(QUEUE_IP);
        connectionFactory.setPort(QUEUE_PORT);
        connectionFactory.setUsername(QUEUE_USER);
        connectionFactory.setPassword(QUEUE_PWD);
        connectionFactory.setVirtualHost(QUEUE_V_HOST);

        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String routingKey=envelope.getRoutingKey();
//                String contentType=properties.getContentType();
//                long deliverTag=envelope.getDeliveryTag();
                System.out.println(new String(body, "utf-8"));
//                channel.basicAck(deliverTag,true);
            }
        };
//        consumer.handleDelivery();
        String s = channel.basicConsume(QUEUE_NAME, false, "myconsumerTag", consumer);
        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//
//            consumer.handleDelivery("222");
//        }
        System.out.println(s + (System.currentTimeMillis() - start));
        channel.close();
        connection.close();
    }
}
