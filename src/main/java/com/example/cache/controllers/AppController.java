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
    public String getDataFromKey(@RequestParam Long id) {
        if (!cacheService.has(id)) {
            User user = new User(id);
            cacheService.set(id, user.getContent());
            System.out.println("LOG: NEW USER CREATED WITH REQUESTED ID AND IS SAVED IN CACHE!");
        }
        try {
            return cacheService.getCache(id).getContent();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("LOG: *ERROR* CACHE IS DELETED BECAUSE TTL IS EXCEEDED!");
        }
        return "The Cache you have requested has been deleted due to TTL being exceeded";
    }
}
