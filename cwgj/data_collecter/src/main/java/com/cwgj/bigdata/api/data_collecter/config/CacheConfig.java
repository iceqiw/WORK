package com.cwgj.bigdata.api.data_collecter.config;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

  /**
   * 配置全局缓存参数，3600秒过期，最大个数1000
   */
  @Bean
  public CacheManager cacheManager() {
    GuavaCacheManager cacheManager = new GuavaCacheManager();
    cacheManager.setCacheBuilder(
        CacheBuilder.newBuilder().expireAfterWrite(3600, TimeUnit.SECONDS).maximumSize(1000));
    return cacheManager;
  }

}
