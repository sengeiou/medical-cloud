package com.zhou.medical.manager.client.account;

import com.zhou.medical.common.entity.account.ContractedHospitals;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVICE")
@RequestMapping("/contractedHospitals")
public interface ContractedHospitalsFeignClient {

    @RequestMapping(value = "getList")
    List<ContractedHospitals> getList(@RequestParam("mapperId") String mapperId, @RequestParam("hospitalName") String hospitalName);


    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId,@RequestBody ContractedHospitals contractedHospitals);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody ContractedHospitals contractedHospitals);


    @RequestMapping(value = "findByParam")
    ContractedHospitals findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("hospitalName") String hospitalName);



}
