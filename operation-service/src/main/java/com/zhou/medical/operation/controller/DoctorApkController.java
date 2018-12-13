package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.PackageVersionDoctor;
import com.zhou.medical.log.annotation.SystemServiceLog;
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
    @SystemServiceLog("doctorApk-findByParam")
    public PackageVersionDoctor findByParam(String mapperId, @RequestBody PackageVersionDoctor packageVersionDoctor) {
        return packageVersionDoctorService.findByParam(mapperId, packageVersionDoctor);
    }

    @RequestMapping(value = "update")
    @SystemServiceLog("doctorApk-update")
    public Integer update(String mapperId, @RequestBody PackageVersionDoctor packageVersionDoctor) {
        return packageVersionDoctorService.update(mapperId, packageVersionDoctor);
    }

    @RequestMapping(value = "insert")
    @SystemServiceLog("doctorApk-insert")
    public Integer insert(@RequestBody PackageVersionDoctor packageVersionDoctor) {
        return packageVersionDoctorService.insert(packageVersionDoctor);
    }

    @RequestMapping(value = "getList")
    @SystemServiceLog("doctorApk-getList")
    public Pager<PackageVersionDoctor> getList(String mapperId, Integer page, Integer rows, @RequestBody PackageVersionDoctor packageVersionDoctor) {
        return packageVersionDoctorService.getList(mapperId, page, rows, packageVersionDoctor);
    }


}
