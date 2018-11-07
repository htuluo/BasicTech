package com.luo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class MqSender
{
    private final static String QUEUE_NAME="test_queue";
    private final static String QUEUE_NAME2="test_queue_2";
    private final static String QUEUE_IP="172.24.161.234";
    private final static int QUEUE_PORT=5672;
    private final static String QUEUE_USER="ygph_dev";
    private final static String QUEUE_PWD="ygph_dev";
    private final static String QUEUE_V_HOST="/ygph_dev";
    private final static String ROUTING_KEY="routing-key-llm";
    private final static String EXCHANGE_LLM = "exchange_llm2";


    public static void main( String[] args ) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost(QUEUE_IP);
        connectionFactory.setPort(QUEUE_PORT);
        connectionFactory.setUsername(QUEUE_USER);
        connectionFactory.setPassword(QUEUE_PWD);
        connectionFactory.setVirtualHost(QUEUE_V_HOST);


        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,true,false,false,null);
        channel.exchangeDeclare(EXCHANGE_LLM,"fanout");
        channel.queueBind(QUEUE_NAME,EXCHANGE_LLM,ROUTING_KEY);
        channel.queueBind(QUEUE_NAME2,EXCHANGE_LLM,ROUTING_KEY);
        String message="hello world";
        long start=System.currentTimeMillis();
        int j=0;
        for (int i=j;i<j+10;i++ ){
            channel.basicPublish(EXCHANGE_LLM,"aaa", MessageProperties.PERSISTENT_TEXT_PLAIN,(message+i).getBytes());
        }
        System.out.println( "Hello World!" +(System.currentTimeMillis()-start));
        channel.close();
        connection.close();
    }
}
