package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemUser;
import com.zhou.medical.operation.service.ISystemUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台客户系统
 *
 * @author luoyizhou
 */
@RestController
@RequestMapping("/systemUser")
public class SystemUserController {


    @Resource
    ISystemUserService systemUserService;


    @RequestMapping(value = "findByParamFromSelectByUserName")
    public SystemUser findByParamFromSelectByUserName(String param) {
        return systemUserService.findByParam("selectByUserName", param);
    }

    @RequestMapping(value = "findByParamFromSelectByPrimaryKey")
    public SystemUser findByParamFromSelectByPrimaryKey(Integer param) {
        return systemUserService.findByParam("selectByPrimaryKey", param);
    }


    @RequestMapping(value = "update")
    public Integer update(String mapperId, @RequestBody SystemUser systemUser) {
        return systemUserService.update(mapperId, systemUser);
    }

    @RequestMapping(value = "getList")
    public Pager<SystemUser> getList(String mapperId, Integer page, Integer rows, @RequestBody SystemUser systemUser) {
        return systemUserService.getList(mapperId, page, rows, systemUser);
    }

    @RequestMapping(value = "insert")
    public Integer insert(@RequestBody SystemUser systemUser) {
        return systemUserService.insert(systemUser);
    }

    @RequestMapping(value = "findById")
    public SystemUser findById(String mapperId, Object param) {
        return systemUserService.findByParam(mapperId, param);
    }

    @RequestMapping(value = "delete")
    public Integer delete(String mapperId, @RequestBody SystemUser systemUser) {
        return systemUserService.delete(mapperId, systemUser);
    }

}
