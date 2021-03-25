package com.example.cache.models;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.TimeUnit;

public class User {

    private long id;
    private String content;

    public User(long id) throws InterruptedException {
        this.id = id;
        this.content = getDataSlow();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String getDataSlow() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return RandomStringUtils.randomAlphabetic(1000);
    }
}
