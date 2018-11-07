package com.zhou.medical.manager.client.account;

import com.zhou.medical.common.entity.account.ContractedHospitalsDoctorsLink;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ACCOUNT-SERVICE")
@RequestMapping("/contractedHospitalsDoctorsLink")
public interface ContractedHospitalsDoctorsLinkFeignClient {


    @RequestMapping(value = "getList")
    List<ContractedHospitalsDoctorsLink> getList(@RequestParam("mapperId") String mapperId, @RequestBody Map<String, Object> paramMap);


    @RequestMapping(value = "delete")
    Integer inter(@RequestParam("mapperId") String mapperId, @RequestBody ContractedHospitalsDoctorsLink contractedHospitalsDoctorsLink);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody ContractedHospitalsDoctorsLink contractedHospitalsDoctorsLink);


    @RequestMapping(value = "findByParam")
    ContractedHospitalsDoctorsLink findByParam(@RequestParam("mapperId") String mapperId, @RequestBody Map<String, Object> paramMap);


}
