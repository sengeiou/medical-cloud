package com.zhou.medical.manager.client.operation;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OPERATION-SERVICE")
@RequestMapping("/systemUser")
public interface SystemUserFeignClient {


    @RequestMapping(value = "findByParamFromSelectByUserName")
    SystemUser findByParamFromSelectByUserName(@RequestParam("param") String param);


    @RequestMapping(value = "findByParamFromSelectByPrimaryKey")
    SystemUser findByParamFromSelectByPrimaryKey(@RequestParam("param") Integer param);


    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, SystemUser systemUser);

    @RequestMapping(value = "getList")
    Pager<SystemUser> getList(@RequestParam("mapperId") String mapperId,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                              @RequestBody SystemUser systemUser);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody SystemUser systemUser);

    @RequestMapping(value = "findById")
    SystemUser findById(@RequestParam("mapperId") String mapperId, @RequestParam("param") Integer param);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SystemUser systemUser);

}
