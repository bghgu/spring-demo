package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by ds on 2021/03/29.
 */

@Data
public class ReceiveContent {

    private String title;

    private String body;

    private String createdAt = LocalDateTime.now().toString();
}