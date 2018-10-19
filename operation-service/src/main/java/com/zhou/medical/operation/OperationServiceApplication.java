package com.zhou.medical.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhouzhou
 *
 */
@SpringBootApplication(scanBasePackages = "com.zhou.medical")
@EnableEurekaClient
public class OperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationServiceApplication.class, args);
    }
}
