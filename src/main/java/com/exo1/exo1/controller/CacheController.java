package com.exo1.exo1.controller;

import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
public class CacheController {
    private final CacheManager cacheManager;

    @GetMapping("/cache")
    public Map<String, Object> getCachedData() {
        Map<String, Object> cachedData = new HashMap<>();

        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);

            if (cache == null) {
                continue;
            }

//            cachedData.put(cacheName, cache.getNativeCache().toString());
            // Pour mieux formatter le retour JSON
            cachedData.put(cacheName, cache.getNativeCache());
        }

        return cachedData;
    }
}
