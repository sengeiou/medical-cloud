package com.zhou.medical.log.receiver;

import com.zhou.medical.log.config.RabbitConfig;
import com.zhou.medical.log.entity.ServiceLog;
import com.zhou.medical.log.service.ServiceLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_SERVICE_LOG)
public class ServiceLogReceiver {

    @Autowired
    private ServiceLogService serviceLogService;

    @RabbitHandler
    public void process(ServiceLog serviceLog) {
        log.debug("处理消息："+serviceLog);
        serviceLogService.save(serviceLog);
    }
}
