package com.zhou.medical.manager.client.account;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.account.DoctorsTeam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ACCOUNT-SERVICE")
@RequestMapping("/doctorTeam")
public interface DoctorsTeamFeignClient {

    @RequestMapping(value = "getList")
    List<DoctorsTeam> getList(@RequestParam("mapperId") String mapperId, @RequestBody DoctorsTeam doctorsTeam);

    @RequestMapping(value = "selectByHospitalName")
    List<DoctorsTeam> selectByHospitalName(@RequestParam("mapperId") String mapperId, @RequestParam("hospitalName") String hospitalName);


    @RequestMapping(value = "selectKeyWordBySys")
    Pager<DoctorsTeam> selectKeyWordBySys(@RequestParam("mapperId") String mapperId,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                          @RequestBody Map<String, Object> map);


    @RequestMapping(value = "findById")
    DoctorsTeam findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody DoctorsTeam doctorsTeam);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody DoctorsTeam doctorsTeam);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody DoctorsTeam doctorsTeam);


    @RequestMapping(value = "findByParam")
    DoctorsTeam findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("hospitalName") String hospitalName);
}
