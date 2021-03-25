package com.example.cache.services;

import com.example.cache.models.Cache;
import com.example.cache.models.User;

public interface CacheService {

    Cache getCache(Long key);

    void set(Long key, String data) throws InterruptedException;

    boolean has(Long key);

    boolean cacheIsExpired(Long key);

    void delete(Long key);

    long setTTL(long TTL);

    String getDataSlow() throws InterruptedException;

}
