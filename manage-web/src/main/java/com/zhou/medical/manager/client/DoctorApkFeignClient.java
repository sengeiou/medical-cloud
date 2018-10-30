package com.zhou.medical.manager.client;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.manager.PackageVersionDoctor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OPERATION-SERVICE")
@RequestMapping("/doctorApk")
public interface DoctorApkFeignClient {

    @RequestMapping(value = "findByParam")
    PackageVersionDoctor findByParam(@RequestParam("mapperId") String mapperId,@RequestBody PackageVersionDoctor packageVersionDoctor);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody PackageVersionDoctor packageVersionDoctor);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody PackageVersionDoctor packageVersionDoctor);

    @RequestMapping(value = "getList")
    Pager<PackageVersionDoctor> getList(@RequestParam("mapperId") String mapperId,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                        @RequestBody PackageVersionDoctor packageVersionDoctor);


}
