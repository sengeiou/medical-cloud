package com.zhou.medical.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhouzhou
 *
 */
@SpringBootApplication(scanBasePackages = "com.zhou.medical",
                         exclude = {DataSourceAutoConfiguration.class,
                                 HibernateJpaAutoConfiguration.class})
@EnableEurekaClient
public class LogServiceApplication  {
    public static void main(String[] args) {
        SpringApplication.run(LogServiceApplication.class, args);
    }

}
