package com.example.demo;

import com.example.demo.domain.SendContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ds on 2021/03/29.
 */

@Slf4j
@RestController
public class TestController {

    private final KafkaContentSender kafkaContentSender;

    private final ObjectMapper mapper = new ObjectMapper();

    public TestController(final KafkaContentSender kafkaContentSender) {
        this.kafkaContentSender = kafkaContentSender;
    }

    @PostMapping(path = "/send")
    public ResponseEntity<String> sendContent(@RequestBody final SendContent sendContent) throws JsonProcessingException {
        final String value = mapper.writeValueAsString(sendContent);
        kafkaContentSender.send(value);
        return ResponseEntity.ok(value);
    }
}