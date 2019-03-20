package com.zhou.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot默认支持两种技术来和ES交互
 * 1、Jest（默认不生效）
 * 需要导入jest的工具包（io.searchbox.client.JestClient）
 * 2、SpringData ElasticSearch[ES版本有可能不合适]
 *  版本说明：https://github.com/spring-projects/spring-data-elasticsearch
 *  如果版本不适配：2.4.6
 *          1）升级Springboot版本
 *          2）安装对应版本的ES
 *  1)、Client 节点信息clusterNode:clusterName
 *  2)、ElasticsearchTemplate 操作es
 *  3）、编一个ElasticsearchRepository的子接口来操作es
 * 两种方法：ttps://github.com/spring-projects/spring-data-elasticsearch
 *  1）编写一个ElasticsearchRepository
 */
@SpringBootApplication
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

}
