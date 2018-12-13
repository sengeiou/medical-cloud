package com.zhou.medical.log.receiver;

import com.zhou.medical.log.config.RabbitConfig;
import com.zhou.medical.log.entity.RequestLog;
import com.zhou.medical.log.service.RequestLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_REQUEST_LOG)
public class RequestLogReceiver {

    @Autowired
    private RequestLogService requestLogService;

    @RabbitHandler
    public void process(RequestLog requestLog) {
        log.debug("处理消息："+requestLog);
        requestLogService.save(requestLog);
    }
}
