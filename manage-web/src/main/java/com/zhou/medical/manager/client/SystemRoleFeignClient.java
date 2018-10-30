package com.zhou.medical.manager.client;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.manager.SystemRole;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "OPERATION-SERVICE")
public interface SystemRoleFeignClient {

    @RequestMapping(value = "/systemRole/getPager")
    Pager<SystemRole> getList(@RequestParam("mapperId") String mapperId,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                              @RequestBody SystemRole systemRole);

    @RequestMapping(value = "/systemRole/getList")
    List<SystemRole> getList(@RequestParam("mapperId") String mapperId,
                             @RequestBody SystemRole systemRole);

    @RequestMapping(value = "/systemRole/insert")
    Integer insert(@RequestBody SystemRole systemRole);


    @RequestMapping(value = "/systemRole/findById")
    SystemRole findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "/systemRole/delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SystemRole systemRole);

    @RequestMapping(value = "/systemRole/update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody SystemRole systemRole);

}
