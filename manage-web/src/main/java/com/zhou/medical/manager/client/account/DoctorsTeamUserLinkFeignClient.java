package com.zhou.medical.manager.client.account;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.account.DoctorsTeamUserLink;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ACCOUNT-SERVICE")
@RequestMapping("/doctorsTeamUserLink")
public interface DoctorsTeamUserLinkFeignClient {

    @RequestMapping(value = "getList")
    List<DoctorsTeamUserLink> getList(@RequestParam("mapperId") String mapperId, @RequestBody DoctorsTeamUserLink doctorsTeamUserLink);


    @RequestMapping(value = "getListByParamMap")
    List<DoctorsTeamUserLink> getListByParamMap(@RequestParam("mapperId") String mapperId, @RequestBody Map<String, Object> paramMap);


    @RequestMapping(value = "findPage")
    Pager<DoctorsTeamUserLink> findPage(@RequestParam("mapperId") String mapperId,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                          @RequestBody Map<String, Object> map);


    @RequestMapping(value = "findById")
    DoctorsTeamUserLink findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody DoctorsTeamUserLink doctorsTeamUserLink);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId,@RequestBody DoctorsTeamUserLink doctorsTeamUserLink);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId,@RequestBody DoctorsTeamUserLink doctorsTeamUserLink);

}
