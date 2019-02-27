package com.zhou.medical.manager.client.hystrix;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemRole;
import com.zhou.medical.manager.client.operation.SystemRoleFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemRoleFeignClientHystrix implements SystemRoleFeignClient {
    @Override
    public Pager<SystemRole> getList(String mapperId, Integer page, Integer rows, SystemRole systemRole) {
        System.out.println("SystemRoleFeignClientHystrix-getList");
        Pager<SystemRole> pager = new Pager<>();

        return pager;
    }

    @Override
    public List<SystemRole> getList(String mapperId, SystemRole systemRole) {
        System.out.println("SystemRoleFeignClientHystrix-getList");

        return null;
    }

    @Override
    public Integer insert(SystemRole systemRole) {
        System.out.println("SystemRoleFeignClientHystrix-insert");

        return null;
    }

    @Override
    public SystemRole findById(String mapperId, Integer id) {
        System.out.println("SystemRoleFeignClientHystrix-findById");

        return null;
    }

    @Override
    public Integer delete(String mapperId, SystemRole systemRole) {
        System.out.println("SystemRoleFeignClientHystrix-delete");

        return null;
    }

    @Override
    public Integer update(String mapperId, SystemRole systemRole) {
        System.out.println("SystemRoleFeignClientHystrix-update");

        return null;
    }
}
