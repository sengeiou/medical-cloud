package com.zhou.medical.manager.client.operation;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.PackageVersionPatient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OPERATION-SERVICE")
@RequestMapping("/patientApk")
public interface PatientApkFeignClient {

    @RequestMapping(value = "findByParam")
    PackageVersionPatient findByParam(@RequestParam("mapperId") String mapperId,@RequestBody PackageVersionPatient packageVersionPatient);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody PackageVersionPatient packageVersionPatient);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody PackageVersionPatient packageVersionPatient);

    @RequestMapping(value = "getList")
    Pager<PackageVersionPatient> getList(@RequestParam("mapperId") String mapperId,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                         @RequestBody PackageVersionPatient packageVersionPatient);

}
