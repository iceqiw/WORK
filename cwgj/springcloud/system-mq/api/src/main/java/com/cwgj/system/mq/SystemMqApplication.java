package com.cwgj.system.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cwgj.system.mq.api.mapper")
public class SystemMqApplication {

  public static void main(String[] args) {
    SpringApplication.run(SystemMqApplication.class, args);
  }
}
