package com.zhou.medical.log.service;

import com.zhou.medical.log.entity.ServiceLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceLogService {

    void save(ServiceLog serviceLog);

}
