package com.cwgj.gateway.handler;

import com.alibaba.fastjson.JSON;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthHandler {

  private final static Logger logger = LoggerFactory.getLogger("AuthHandler");

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Value("${sys.restAuthUrl}")
  String restAuthUrl;

  public boolean vaild(String token, String url) {
    List<String> urls = getTokenUrl(token);
    return urls.contains(url);
  }

  public void addTokenUrl(String token, List<String> urls) {
    logger.info("addTokenUrl");
    String authStr = JSON.toJSONString(urls);
    redisTemplate.opsForValue().set(token, authStr);
    redisTemplate.expire(token, 30, TimeUnit.MINUTES);
  }

  public List<String> getTokenUrl(String token) {
    logger.info("getTokenUrl");
    String authCache = redisTemplate.opsForValue().get(token);
    if (StringUtils.isEmpty(authCache)) {
      List<String> urls = restTemplate.getForObject(restAuthUrl + token, List.class);
      if (ObjectUtils.isEmpty(urls)) {
        logger.info("getTokenUrl isEmpty");
        return Arrays.asList("");
      }
      addTokenUrl(token, urls);
      return urls;
    }
    return JSON.parseObject(authCache, List.class);
  }


}
