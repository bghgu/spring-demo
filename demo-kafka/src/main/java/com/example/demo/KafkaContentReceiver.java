package com.example.demo;

import com.example.demo.domain.ReceiveContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Created by ds on 2021/03/29.
 */

@Slf4j
public class KafkaContentReceiver {

    private final String TOPIC = "push";

    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = TOPIC)
    public void listen(final ConsumerRecord<?, ?> data) throws JsonProcessingException {
        final String topic = data.topic();
        final String value = String.valueOf(data.value());
        final long timestamp = data.timestamp();

        final ReceiveContent receiveContent = mapper.readValue(value, ReceiveContent.class);

        log.info("Received: " + receiveContent.toString() + " Topic: " + topic + " timestamp: " + timestamp);
    }
}