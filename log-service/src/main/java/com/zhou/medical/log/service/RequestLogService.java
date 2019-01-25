package com.zhou.medical.log.service;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.log.entity.RequestLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestLogService {

    void save(RequestLog requestLog);

    List<RequestLog> findByActionNameLike(String actionName);

    Page<RequestLog> findByActionNameLike(String actionName, Integer page, Integer rows);

    Pager<RequestLog> findByConditionLike(RequestLog requestLog,String createTimeBeforeStr, String createTimeAfterStr, Integer page, Integer rows);

}
