package com.example.demo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ds on 2021/03/16.
 */

@Document
public class Content {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}