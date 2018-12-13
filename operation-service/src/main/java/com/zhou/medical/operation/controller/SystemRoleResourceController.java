package com.zhou.medical.operation.controller;

import com.zhou.medical.log.annotation.SystemServiceLog;
import com.zhou.medical.operation.service.ISystemRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统资源
 * 
 * @author zds
 *
 */
@RestController
@RequestMapping("/systemRoleResource")
public class SystemRoleResourceController {

    @Autowired
    private ISystemRoleResourceService systemRoleResourceService;


    @RequestMapping(value = "updateRoleResource")
    @SystemServiceLog("systemRoleResource-updateRoleResource")
    public void updateRoleResource(Integer id, String resourceIds) {
        systemRoleResourceService.updateRoleResource(id, resourceIds);
    }

    @RequestMapping(value = "getRoleResourceIdListByRoleId")
    @SystemServiceLog("systemRoleResource-getRoleResourceIdListByRoleId")
    public List<Integer> getRoleResourceIdListByRoleId(Integer id) {
       return systemRoleResourceService.getRoleResourceIdListByRoleId(id);
    }


}
