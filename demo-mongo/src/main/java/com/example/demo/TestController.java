package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by ds on 2021/03/16.
 */

@RestController
public class TestController {

    private final ContentRepository contentRepository;

    public TestController(final ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/test")
    public Object getContents() {
        Content content = new Content();
        content.setTitle(LocalDateTime.now().toString());
        contentRepository.save(content);
        return contentRepository.findAll();
    }
}