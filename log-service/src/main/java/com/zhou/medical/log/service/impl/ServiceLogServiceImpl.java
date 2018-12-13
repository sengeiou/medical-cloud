package com.zhou.medical.log.service.impl;

import com.zhou.medical.log.repository.ServiceLogRepository;
import com.zhou.medical.log.entity.ServiceLog;
import com.zhou.medical.log.service.ServiceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogServiceImpl implements ServiceLogService {

    @Autowired
    private ServiceLogRepository serviceLogRepository;

    @Override
    public void save(ServiceLog serviceLog){
        serviceLogRepository.save(serviceLog);
    }

}
