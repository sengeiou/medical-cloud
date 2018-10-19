package com.zhou.medical.operation.test;

import com.zhou.medical.common.entity.manager.PackageVersionPatient;
import com.zhou.medical.operation.OperationServiceApplication;
import com.zhou.medical.operation.service.IPackageVersionPatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OperationServiceApplication.class)
public class OperationServiceApplicationTest {

    @Resource
    IPackageVersionPatientService packageVersionPatientService;

    @Test
    public void insertTest() {

        Map<String, String> map = new HashMap<>();
        map.put("hVender", "default");
        map.put("hModel", "default");

        PackageVersionPatient packageVersion = packageVersionPatientService.findByParam("selectByStatus_1", map);
        System.out.println("packageVersion=" + packageVersion);
        System.out.println("packageVersion.gethPackagename()=" + packageVersion.gethPackageurl());
    }
}