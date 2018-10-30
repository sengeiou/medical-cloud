package com.zhou.medical.manager.client;

import com.zhou.medical.common.entity.Tree;
import com.zhou.medical.common.entity.manager.SystemResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "OPERATION-SERVICE")
@RequestMapping("/systemResource")
public interface SystemResourceFeignClient {

    @RequestMapping(value = "getList")
    List<SystemResource> getList(@RequestParam("mapperId") String mapperId, @RequestBody SystemResource systemResource);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody SystemResource systemResource);

    @RequestMapping(value = "findAllTree")
    List<Tree> findAllTree();

    @RequestMapping(value = "findAllTrees")
    List<Tree> findAllTrees();

    @RequestMapping(value = "findById")
    SystemResource findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody SystemResource systemResource);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SystemResource systemResource);

    @RequestMapping(value = "findResourceUrlListByRoleId")
    List<SystemResource> findResourceUrlListByRoleId(@RequestParam("roleId") Integer roleId);

}
