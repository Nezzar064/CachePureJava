package com.example.cache.models;

public class Cache {

    private Long key;
    private String content;
    private long TTL;

    public Cache(Long key, String content, long TTL) {
        this.key = key;
        this.content = content;
        this.TTL = TTL;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTTL() {
        return TTL;
    }

    public void setTTL(long time) {
        this.TTL = time;
    }
}
