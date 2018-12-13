package com.zhou.medical.log.repository;

import com.zhou.medical.log.entity.RequestLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;


public interface RequestLogRepository extends MongoRepository<RequestLog, String> {

    List<RequestLog> findByActionNameLike(String actionName);

    Page<RequestLog> findByActionNameLike(String actionName, PageRequest pageRequest);


    @Query(value = "{" +
            "    actionName:{$regex:?0},\n" +
            "    className:{$regex:?1},\n" +
            "    methodName:{$regex:?2},\n" +
            "    state:?3,\n" +
            "    request:{$regex:?4},\n" +
            "    response:{$regex:?5},\n" +
            "    createTime:{$gte:?6,$lte:?7}\n" +
            "}")
    Page<RequestLog> findByConditionLike(String actionName, String className, String methodName, int state,
                                         String request,String response, Date createTimeBefore,
                                         Date createTimeAfter, PageRequest pageRequest);

}
