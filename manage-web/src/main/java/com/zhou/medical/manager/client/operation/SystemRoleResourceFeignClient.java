package com.zhou.medical.manager.client.operation;

import com.zhou.medical.manager.client.hystrix.SystemRoleResourceFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "OPERATION-SERVICE",fallback = SystemRoleResourceFeignClientHystrix.class,path = "/systemRoleResource")
//@RequestMapping("/systemRoleResource")
public interface SystemRoleResourceFeignClient {

    @RequestMapping(value = "updateRoleResource")
    void updateRoleResource(@RequestParam("id") Integer id,@RequestParam("resourceIds")  String resourceIds);

    @RequestMapping(value = "getRoleResourceIdListByRoleId")
    List<Integer> getRoleResourceIdListByRoleId(@RequestParam("id") Integer id);

}
