package com.zhou.test.controller;

import com.zhou.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/testService/Test1")
    public String testService1() {
        testService.test1();
        return "Test1";
    }

    @RequestMapping("/testService/Test2")
    public String testService2() {
        testService.test2();
        return "Test2";
    }

}
