package com.mental.contactbook;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import com.google.common.cache.CacheBuilder;

@EnableCaching
@SpringBootApplication
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * Create SimpleCacheManager with two GuavaCaches:
	 * 1) bookCache - for Contact entities called with particular id;
	 * 2) booksExpirableCache - for Contacts entities called with excluded regular expression;
	 * @return simpleCacheManager
	 */
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		GuavaCache bookCache = new GuavaCache("contact", CacheBuilder
				.newBuilder().maximumSize(100).build());
		GuavaCache booksExpirableCache = new GuavaCache("contacts",
				CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES)
						.build());
		simpleCacheManager.setCaches(Arrays.asList(bookCache,
				booksExpirableCache));
		return simpleCacheManager;
	}
}
