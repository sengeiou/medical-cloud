package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Tree;
import com.zhou.medical.common.entity.operation.SystemResource;
import com.zhou.medical.log.annotation.SystemServiceLog;
import com.zhou.medical.operation.service.ISystemResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统资源
 *
 * @author zds
 */
@RestController
@RequestMapping("/systemResource")
public class SystemResourceController {

    @Autowired
    private ISystemResourceService systemResourceService;

    @RequestMapping(value = "getList")
    @SystemServiceLog("systemResource-getList")
    public List<SystemResource> getList(String mapperId,@RequestBody SystemResource systemResource) {
        return systemResourceService.getList(mapperId, systemResource);
    }

    @RequestMapping(value = "insert")
    @SystemServiceLog("systemResource-insert")
    public Integer insert(@RequestBody SystemResource systemResource) {
        return systemResourceService.insert(systemResource);
    }

    @RequestMapping(value = "findAllTree")
    @SystemServiceLog("systemResource-findAllTree")
    public List<Tree> findAllTree() {
        return systemResourceService.findAllTree();
    }

    @RequestMapping(value = "findAllTrees")
    @SystemServiceLog("systemResource-findAllTrees")
    public List<Tree> findAllTrees() {
        return systemResourceService.findAllTrees();
    }

    @RequestMapping(value = "findById")
    @SystemServiceLog("systemResource-findById")
    public SystemResource findById(String mapperId, Integer id) {
        return systemResourceService.findById(mapperId, id);
    }

    @RequestMapping(value = "update")
    @SystemServiceLog("systemResource-update")
    public Integer update(String mapperId, @RequestBody SystemResource systemResource) {
        return systemResourceService.update(mapperId, systemResource);
    }

    @RequestMapping(value = "delete")
    @SystemServiceLog("systemResource-delete")
    public Integer delete(String mapperId, @RequestBody SystemResource systemResource) {
        return systemResourceService.delete(mapperId, systemResource);
    }

    @RequestMapping(value = "findResourceUrlListByRoleId")
    @SystemServiceLog("systemResource-findResourceUrlListByRoleId")
    public List<SystemResource> findResourceUrlListByRoleId(Integer roleId) {
        System.out.println("findResourceUrlListByRoleIdfindResourceUrlListByRoleIdfindResourceUrlListByRoleId:" + roleId);
        return systemResourceService.getList("findResourceUrlListByRoleId", roleId);
    }


}
