package com.zhou.medical.manager.client.account;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.account.DoctorsUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "ACCOUNT-SERVICE")
@RequestMapping("/doctorsUser")
public interface DoctorsUserFeignClient {

    @RequestMapping(value = "findPage")
    Pager<DoctorsUser> findPage(@RequestParam("mapperId") String mapperId,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                @RequestBody Map<String, Object> map);

    @RequestMapping(value = "findById")
    DoctorsUser findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody DoctorsUser doctorsUser);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId,@RequestBody DoctorsUser doctorsUser);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId,@RequestBody DoctorsUser doctorsUser);

    @RequestMapping(value = "findByParam")
    DoctorsUser findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("phone") String phone) ;
}
