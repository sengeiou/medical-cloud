package com.zhou.test;

import com.zhou.test.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class BaseServiceTest {

    @Autowired
    TestService testService;


    @Test
    public void Test3() {
        System.out.println("Test3Test3Test3Test3Test3Test3Test3");
        for (int i=0; i < 20; i++) {
            System.out.println("i:"+i);
            int n = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testService.test3(n);
                }
            }).start();
        }

    }

    @Test
    public void Test4() {
        String key = "1";
        System.out.println(key.hashCode());
        System.out.println(hash(key));
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }



}
