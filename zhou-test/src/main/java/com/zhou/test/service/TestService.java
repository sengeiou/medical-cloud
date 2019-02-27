package com.zhou.test.service;


import com.zhou.test.model.JsonDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class TestService extends BaseService{

    public void test1(){

        log.info("Test1前:"+gson.toJson(jsondomain));
        jsondomain.setState("Test1-------------------------");
        jsondomain.setTotalnum(0);
        jsondomain.setDatalist(new ArrayList<>());
        log.info("Test1后:"+gson.toJson(jsondomain));
    }

    public void test2(){
        log.info("Test2前:"+gson.toJson(jsondomain));
        jsondomain.setState("Test2------------------------");
        jsondomain.setTotalnum(0);
        jsondomain.setDatalist(null);
        log.info("Test2后:"+gson.toJson(jsondomain));

    }


    public void test3(Integer n){
        log.info(n+"Test3前:"+gson.toJson(jsondomain));
        jsondomain.setState("Test3-------------------------");
        jsondomain.setTotalnum(n);
        jsondomain.setDatalist(new ArrayList<>());
        log.info(n+"Test3后:"+gson.toJson(jsondomain));
    }


}
