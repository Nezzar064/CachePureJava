package com.example.cache.models;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.TimeUnit;

public class User {

    private long id;
    private String content;

    public User(long id) {
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

    private String getDataSlow() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("LOG: *ERROR* sleep() DID NOT WORK AS INTENDED, INSERTING TEXT WITHOUT SLOW CALL");
        }
        return RandomStringUtils.randomAlphabetic(1000);
    }
}
