package com.llm.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/3/31
 * @description： //TODO
 */

@Slf4j
@Component
public class ConsumerDemo {
    @Value("${kafka.consumer.sleepSeconds}")
    private Integer sleepSeconds;

    @KafkaListener(topics = "${kafka.consumer.topic}")
    public void onMessage(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
        log.info(MessageFormat.format("offset:{1}\tpartition:{2}\t{0}\t", consumerRecord.value(), consumerRecord.offset(), consumerRecord.partition()));
        try {
            Thread.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
