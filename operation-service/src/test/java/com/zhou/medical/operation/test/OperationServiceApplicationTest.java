package com.zhou.medical.operation.test;

import com.zhou.medical.common.entity.operation.PackageVersionPatient;
import com.zhou.medical.operation.OperationServiceApplication;
import com.zhou.medical.operation.service.IPackageVersionPatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = OperationServiceApplication.class)
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


    @Test
    public void testHash(){
        Map<String,String> hashtable = new Hashtable<>();
        hashtable.put("t1","1");
        hashtable.put("t2","2");
        hashtable.put("t3","3");

        Enumeration<Map.Entry<String,String>> iterator1 = (Enumeration<Map.Entry<String,String>>)hashtable.entrySet().iterator();
        hashtable.remove(iterator1.nextElement());
        while (iterator1.hasMoreElements()){
            System.out.println(iterator1.nextElement());
        }


        Map<String,String> hashMap = new Hashtable<>();
        hashMap.put("t1","1");
        hashMap.put("t2","2");
        hashMap.put("t3","3");

        Iterator<Map.Entry<String,String>> iterator2 = hashMap.entrySet().iterator();
        hashMap.remove(iterator2.next().getKey());
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

    }


}