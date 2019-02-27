package com.zhou.medical.manager.client.operation;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemRole;
import com.zhou.medical.manager.client.hystrix.SystemRoleFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "OPERATION-SERVICE",fallback = SystemRoleFeignClientHystrix.class,path = "/systemRole")
//@RequestMapping(value = "/systemRole")
public interface SystemRoleFeignClient {

    @RequestMapping(value = "getPager")
    Pager<SystemRole> getList(@RequestParam("mapperId") String mapperId,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                              @RequestBody SystemRole systemRole);

    @RequestMapping(value = "getList")
    List<SystemRole> getList(@RequestParam("mapperId") String mapperId,
                             @RequestBody SystemRole systemRole);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody SystemRole systemRole);


    @RequestMapping(value = "findById")
    SystemRole findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SystemRole systemRole);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody SystemRole systemRole);

}
