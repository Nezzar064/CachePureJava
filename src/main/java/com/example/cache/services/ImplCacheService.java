package com.example.cache.services;

import com.example.cache.models.Cache;
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
        if (isCacheExpired(key)) {
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

    public boolean isCacheExpired(Long key) {
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


