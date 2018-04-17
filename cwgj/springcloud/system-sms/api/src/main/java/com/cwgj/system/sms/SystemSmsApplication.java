package com.cwgj.system.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cwgj.system.push.api.mapper")
public class SystemSmsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SystemSmsApplication.class, args);
  }
}
