package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemRole;
import com.zhou.medical.log.annotation.SystemServiceLog;
import com.zhou.medical.operation.service.ISystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统角色
 *
 * @author zds
 */
@RestController
@RequestMapping("/systemRole")
public class SystemRoleController {

    @Autowired
    private ISystemRoleService systemRoleService;


    @RequestMapping(value = "getList")
    @SystemServiceLog("systemRole-getList")
    public List<SystemRole> getList(String mapperId, @RequestBody SystemRole systemRole) {
        return systemRoleService.getList(mapperId, systemRole);
    }

    @RequestMapping(value = "getPager")
    @SystemServiceLog("systemRole-getPager")
    public Pager<SystemRole> getPager(String mapperId,
                                          Integer page,
                                          Integer rows,
                                          @RequestBody SystemRole systemRole){
        return systemRoleService.getList(mapperId, page, rows, systemRole);
    }

    @RequestMapping(value = "insert")
    @SystemServiceLog("systemRole-insert")
    public Integer insert(@RequestBody SystemRole systemRole) {
        return systemRoleService.insert(systemRole);
    }


    @RequestMapping(value = "findById")
    @SystemServiceLog("systemRole-findById")
    public SystemRole findById(String mapperId, Integer id) {
        return systemRoleService.findById(mapperId, id);
    }


    @RequestMapping(value = "delete")
    @SystemServiceLog("systemRole-delete")
    public Integer delete(String mapperId, @RequestBody SystemRole systemRole) {
        return systemRoleService.delete(mapperId, systemRole);
    }

    @RequestMapping(value = "update")
    @SystemServiceLog("systemRole-update")
    public Integer update(String mapperId, @RequestBody SystemRole systemRole) {
        return systemRoleService.update(mapperId, systemRole);
    }

}
