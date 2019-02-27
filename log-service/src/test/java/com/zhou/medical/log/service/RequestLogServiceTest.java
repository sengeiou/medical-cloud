package com.zhou.medical.log.service;

import com.google.gson.Gson;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.log.LogServiceApplication;
import com.zhou.medical.log.entity.RequestLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LogServiceApplication.class)
public class RequestLogServiceTest {

    @Autowired
    private RequestLogService requestLogService;

    @Test
    public void save() {
        RequestLog requestLog = new RequestLog();
//        user.setId(1);
//        user.setAge(11);
//        user.setName("罗亦洲1111");
//        userService.save(user);
        requestLog.setActionName("");
        requestLogService.save(requestLog);
    }

    @Test
    public void query() {
//        List<User> list = userService.queryAll();
//        System.out.println(list);
    }

    @Test
    public void findByConditionLike() {
        RequestLog requestLog = new RequestLog();

        Pager<RequestLog> pager=  requestLogService.findByConditionLike(requestLog,null,null,1,20);
        Gson gson = new Gson();
        System.out.println("-------------------:"+gson.toJson(pager));
    }

}
