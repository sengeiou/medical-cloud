package com.zhou.medical.account.controller;


import com.zhou.medical.account.service.IContractedHospitalsService;
import com.zhou.medical.common.entity.account.ContractedHospitals;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/contractedHospitals")
public class ContractedHospitalsController {


    @Resource
    IContractedHospitalsService contractedHospitalsService;

    @RequestMapping(value = "getList")
    List<ContractedHospitals> getList(@RequestParam("mapperId") String mapperId, @RequestParam("hospitalName") String hospitalName) {
        return contractedHospitalsService.getList(mapperId, hospitalName);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody ContractedHospitals contractedHospitals) {
        return contractedHospitalsService.delete(mapperId, contractedHospitals);
    }

}
