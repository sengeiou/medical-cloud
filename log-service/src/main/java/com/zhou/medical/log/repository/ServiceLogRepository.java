package com.zhou.medical.log.repository;

import com.zhou.medical.log.entity.ServiceLog;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ServiceLogRepository extends MongoRepository<ServiceLog, String> {

}
