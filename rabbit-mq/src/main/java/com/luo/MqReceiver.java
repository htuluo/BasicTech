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
    private final static String QUEUE_IP = "172.24.161.234";
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
                long deliverTag=envelope.getDeliveryTag();
                System.out.println("receive the deliverTag "+deliverTag+"  "+new String(body, "utf-8"));
                channel.basicAck(deliverTag,false);
            }
        };
        Consumer consumer2 = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String routingKey=envelope.getRoutingKey();
//                String contentType=properties.getContentType();
                long deliverTag=envelope.getDeliveryTag();
                System.out.println("receive the deliverTag2 "+deliverTag+"  "+new String(body, "utf-8"));
                channel.basicAck(deliverTag,false);
            }
        };
        String s = channel.basicConsume(QUEUE_NAME, false, consumer);
        String s2 = channel.basicConsume(QUEUE_NAME, false, consumer2);
//        consumer.handleDelivery();
//        long start = System.currentTimeMillis();
//        System.out.println(s + (System.currentTimeMillis() - start));
//        channel.close();
//        connection.close();
    }
}
