package com.example.cache.services;

import com.example.cache.models.Cache;
import com.example.cache.models.User;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ImplCacheService implements CacheService {

    private Map<Long, Cache> cacheDB = new HashMap<>();
    private Map<Long, Long> timerDB = new HashMap<>();

    @Override
    public Cache getCache(Long key) {
        if (cacheIsExpired(key)) {
            delete(key);
        }
        return cacheDB.get(key);
    }


    @Override
    public void set(Long key, String data) {
        long TTL = setTTL(60);
        Cache cache = new Cache(key, data, TTL);
        cacheDB.put(key, cache);
        timerDB.put(key, System.nanoTime());
    }

    @Override
    public boolean has(Long key) {
        return cacheDB.containsKey(key);
    }

    @Override
    public boolean cacheIsExpired(Long key) {
        return (System.nanoTime() - timerDB.get(key)) > cacheDB.get(key).getTTL();
    }

    @Override
    public void delete(Long key) {
        cacheDB.remove(key);
    }

    @Override
    public long setTTL(long TTL) {
        return TimeUnit.SECONDS.toNanos(TTL);
    }
}


