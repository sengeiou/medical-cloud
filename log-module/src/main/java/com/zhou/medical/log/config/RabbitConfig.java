package com.zhou.medical.log.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_REQUEST_LOG = "queue.request.log";
    public static final String QUEUE_SERVICE_LOG = "queue.service.log";

    public static final String EXCHANGE_REQUEST_LOG = "exchange.request.log";
    public static final String EXCHANGE_SERVICE_LOG = "exchange.service.log";

    public static final String ROUTING_KEY_REQUEST_LOG = "routing.key.request.log";
    public static final String ROUTING_KEY_SERVICE_LOG = "routing.key.service.log";


    @Bean
    public Queue queueRequestLog() {
        return new Queue(QUEUE_REQUEST_LOG);
    }
    @Bean
    public Queue queueServiceLog() {
        return new Queue(QUEUE_SERVICE_LOG);
    }


    @Bean
    TopicExchange requestExchange() {
        return new TopicExchange(EXCHANGE_REQUEST_LOG);
    }

    @Bean
    TopicExchange serviceExchange() {
        return new TopicExchange(EXCHANGE_SERVICE_LOG);
    }


    @Bean
    Binding bindingRequestExchange(Queue queueRequestLog, TopicExchange requestExchange) {
        return BindingBuilder.bind(queueRequestLog).to(requestExchange).with(ROUTING_KEY_REQUEST_LOG);
//        return BindingBuilder.bind(queueRequestLog).to(exchange);
    }

    @Bean
    Binding bindingServiceExchange(Queue queueServiceLog, TopicExchange serviceExchange) {
        return BindingBuilder.bind(queueServiceLog).to(serviceExchange).with(ROUTING_KEY_SERVICE_LOG);
//        return BindingBuilder.bind(queueRequestLog).to(exchange);
    }


}
