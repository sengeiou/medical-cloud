package com.zhou.medical.log.service.impl;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.log.repository.RequestLogRepository;
import com.zhou.medical.log.entity.RequestLog;
import com.zhou.medical.log.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Override
    public void save(RequestLog requestLog) {
        requestLogRepository.save(requestLog);
    }

    @Override
    public List<RequestLog> findByActionNameLike(String actionName) {
        return requestLogRepository.findByActionNameLike(actionName);
    }

    @Override
    public Page<RequestLog> findByActionNameLike(String actionName, Integer page, Integer rows) {
        PageRequest pageRequest = new PageRequest(page - 1, rows);
        if (actionName == null) {
            actionName = "";
        }
        return requestLogRepository.findByActionNameLike(actionName, pageRequest);
    }

    @Override
    public Pager<RequestLog> findByConditionLike(RequestLog requestLog, String createTimeBeforeStr, String createTimeAfterStr, Integer page, Integer rows) {
        System.out.println("requestLog======================:" + requestLog);
        PageRequest pageRequest = new PageRequest(page - 1, rows);

        String actionName = "";
        String className = "";
        String methodName = "";
        int state = requestLog.getState();
        String request = "";
        String response = "";
        Date createTimeBefore = null;
        Date createTimeAfter = null;

        if (requestLog.getActionName() != null) {
            actionName = requestLog.getActionName();
        }

        if (requestLog.getClassName() != null) {
            className = requestLog.getClassName();
        }

        if (requestLog.getMethodName() != null) {
            methodName = requestLog.getMethodName();
        }

        if (requestLog.getRequest() != null) {
            request = requestLog.getRequest();
        }

        if (requestLog.getResponse() != null) {
            response = requestLog.getResponse();
        }

        if (createTimeBeforeStr == null) {
            createTimeBefore = new Date(0);
        } else {
            try {
                createTimeBefore = simpleDateFormat.parse(createTimeBeforeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (createTimeAfterStr == null) {
            createTimeAfter = new Date(System.currentTimeMillis() + 1 * 24 * 3600 * 1000);
        } else {
            try {
                createTimeAfter = simpleDateFormat.parse(createTimeAfterStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


//        state = 1;

        Page<RequestLog> pageRequestLog = requestLogRepository.findByConditionLike(actionName, className, methodName,
                state, request, response, createTimeBefore, createTimeAfter, pageRequest);
        Pager<RequestLog> pager = new Pager<>();
        pager.setList(pageRequestLog.getContent());
        pager.setTotalCount((int) pageRequestLog.getTotalElements());

        return pager;
    }


}
