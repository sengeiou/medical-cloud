package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.PackageVersionPatient;
import com.zhou.medical.log.annotation.SystemServiceLog;
import com.zhou.medical.operation.service.IPackageVersionPatientService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author adminstrator
 * @ClassName: 包版本Controller
 * @Description: TODO
 * @date 2017年4月10日 上午11:50:27
 */
@RestController
@RequestMapping("/patientApk")
public class PatientApkController {

    @Resource
    private IPackageVersionPatientService packageVersionPatientService;

    @RequestMapping(value = "findByParam")
    @SystemServiceLog("patientApk-findByParam")
    public PackageVersionPatient findByParam(String mapperId, @RequestBody PackageVersionPatient packageVersionPatient) {
        return packageVersionPatientService.findByParam(mapperId, packageVersionPatient);
    }

    @RequestMapping(value = "update")
    @SystemServiceLog("patientApk-update")
    public Integer update(String mapperId, @RequestBody PackageVersionPatient packageVersionPatient) {
        return packageVersionPatientService.update(mapperId, packageVersionPatient);
    }

    @RequestMapping(value = "insert")
    @SystemServiceLog("patientApk-insert")
    public Integer insert(@RequestBody PackageVersionPatient packageVersionPatient) {
        return packageVersionPatientService.insert(packageVersionPatient);
    }

    @RequestMapping(value = "getList")
    @SystemServiceLog("patientApk-getList")
    public Pager<PackageVersionPatient> getList(String mapperId, Integer page, Integer rows, @RequestBody PackageVersionPatient packageVersionPatient) {
        return packageVersionPatientService.getList(mapperId, page, rows, packageVersionPatient);
    }

}
