package com.zhou.medical.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class JSPController {

    private String hello;

    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String,Object> map){
        System.out.println("HelloController.helloJsp().hello=hello");
        map.put("hello", "zhouzhou");
        return "index";
    }
}