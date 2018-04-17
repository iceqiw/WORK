package com.cwgj.system.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cwgj.system"})
public class SystemTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemTestApplication.class, args);
	}
}
