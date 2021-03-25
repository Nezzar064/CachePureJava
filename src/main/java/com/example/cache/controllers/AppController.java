package com.example.cache.controllers;

import com.example.cache.models.Cache;
import com.example.cache.models.User;
import com.example.cache.services.ImplCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @Autowired
    ImplCacheService cacheService;

    @GetMapping(value = "/get-user-data")
    @ResponseBody
    public String getDataFromKey(@RequestParam Long id) throws InterruptedException {
        if (!cacheService.has(id)) {
            User user = new User(id, cacheService.getDataSlow());
            cacheService.set(id, user.getContent());
            System.out.println("LOG: NEW USER CREATED WITH REQUESTED ID AND IS PUT IN CACHE!");
        }
        try {
            return cacheService.getCache(id).getContent();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("LOG: ERROR, TTL FOR CACHE EXCEEDED, CACHE IS DELETED");
        }
        return "The Cache you have requested has been deleted due to TTL being exceeded";
    }
}
