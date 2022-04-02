package com.llm.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
    @KafkaListener(topics = "${kafka.consumer.topic}")
    public void onMessage(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
        log.info(MessageFormat.format("{0}\t{1}\t{2}", consumerRecord.value(), consumerRecord.offset(), consumerRecord.partition()));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
