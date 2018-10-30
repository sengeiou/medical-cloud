package com.zhou.medical.manager.client;

import com.zhou.medical.common.entity.account.DoctorsTeam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVICE")
@RequestMapping("/doctorTeam")
public interface DoctorsTeamFeignClient {

    @RequestMapping(value = "getList")
    List<DoctorsTeam> getList(@RequestParam("mapperId") String mapperId,@RequestBody DoctorsTeam doctorsTeam);

}
