package com.amod.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration("names")
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager() {
	    SimpleCacheManager cacheManager = new SimpleCacheManager();
	    List<Cache> caches = new ArrayList<Cache>();
	    caches.add(new ConcurrentMapCache("names"));
	    cacheManager.setCaches(caches);
	    return cacheManager;
	}
	
}
