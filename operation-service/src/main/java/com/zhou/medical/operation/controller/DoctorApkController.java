package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.manager.PackageVersionDoctor;
import com.zhou.medical.operation.service.IPackageVersionDoctorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhouzhou
 */
@RestController
@RequestMapping("/doctorApk")
public class DoctorApkController {

    @Resource
    private IPackageVersionDoctorService packageVersionDoctorService;


    @RequestMapping(value = "findByParam")
    public PackageVersionDoctor findByParam(String mapperId, @RequestBody PackageVersionDoctor packageVersionDoctor) {
        System.out.println("findByParamfindByParamfindByParamfindByParamfindByParamfindByParamfindByParam");
        return packageVersionDoctorService.findByParam(mapperId, packageVersionDoctor);
    }

    @RequestMapping(value = "update")
    public Integer update(String mapperId, @RequestBody PackageVersionDoctor packageVersionDoctor) {
        return packageVersionDoctorService.update(mapperId, packageVersionDoctor);
    }

    @RequestMapping(value = "insert")
    public Integer insert(@RequestBody PackageVersionDoctor packageVersionDoctor) {
        return packageVersionDoctorService.insert(packageVersionDoctor);
    }

    @RequestMapping(value = "getList")
    public Pager<PackageVersionDoctor> getList(String mapperId, Integer page, Integer rows, @RequestBody PackageVersionDoctor packageVersionDoctor) {
        return packageVersionDoctorService.getList(mapperId, page, rows, packageVersionDoctor);
    }


}
