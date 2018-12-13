package com.zhou.medical.log.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@Slf4j
public class RabbitMqSender<T> implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    public void send(String exchange,String routingKey,T t) {
        log.debug("Sender-发送消息 : "+exchange+"-"+routingKey+"-" + t);
        this.rabbitTemplate.convertAndSend(exchange,routingKey, t);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.debug("Sender消息发送成功:" + correlationData);
        } else {
            log.debug("Sender消息发送失败:" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.debug(message.getMessageProperties().getCorrelationIdString() + " 发送失败");
    }
}
