package com.zhou.medical.manager;

import com.zhou.ribbon.config.MySelfRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;

@EnableFeignClients
@RibbonClient(name = "OPERATION-SERVICE",configuration = MySelfRuleConfig.class)
//@RibbonClients(value = {@RibbonClient(name = "OPERATION-SERVICE"),@RibbonClient("ACCOUNT-SERVICE")},defaultConfiguration = MySelfRuleConfig.class)
@EnableEurekaClient
@EnableHystrix
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,MongoAutoConfiguration.class})
@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.zhou.medical")

//@EnableCircuitBreaker//对hytrix熔断机制的支持

public class ManagerWebApplication{

    public static void main(String[] args) {
        SpringApplication.run(ManagerWebApplication.class, args);
    }
}