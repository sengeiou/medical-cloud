package com.zhou.medical.manager.client.hystrix;

import com.zhou.medical.manager.client.operation.SystemRoleResourceFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemRoleResourceFeignClientHystrix implements SystemRoleResourceFeignClient {
    @Override
    public void updateRoleResource(Integer id, String resourceIds) {
        System.out.println("SystemRoleResourceFeignClientHystrix-updateRoleResource");

    }

    @Override
    public List<Integer> getRoleResourceIdListByRoleId(Integer id) {
        System.out.println("SystemRoleResourceFeignClientHystrix-getRoleResourceIdListByRoleId");

        return null;
    }
}
