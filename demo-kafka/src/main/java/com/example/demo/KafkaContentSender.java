package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by ds on 2021/03/29.
 */

@Slf4j
@Service
public class KafkaContentSender {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaContentSender(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final String payload) {
        final ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, payload);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("[KafkaContentSender kafkaSend] ==== success : {}", payload);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("[KafkaContentSender kafkaSend] ==== failed : {}", payload);
            }
        });
    }
}