package com.zhou.medical.manager.client.hystrix;

import com.zhou.medical.common.entity.Tree;
import com.zhou.medical.common.entity.operation.SystemResource;
import com.zhou.medical.manager.client.operation.SystemResourceFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SystemResourceFeignClientHystrix implements SystemResourceFeignClient {
    @Override
    public List<SystemResource> getList(String mapperId, SystemResource systemResource) {
        System.out.println("SystemResourceFeignClientHystrix-getList");

        return new ArrayList<>();
    }

    @Override
    public Integer insert(SystemResource systemResource) {
        System.out.println("SystemResourceFeignClientHystrix-insert");

        return null;
    }

    @Override
    public List<Tree> findAllTree() {
        System.out.println("SystemResourceFeignClientHystrix-findAllTree");

        return null;
    }

    @Override
    public List<Tree> findAllTrees() {
        System.out.println("SystemResourceFeignClientHystrix-findAllTrees");

        return null;
    }

    @Override
    public SystemResource findById(String mapperId, Integer id) {
        System.out.println("SystemResourceFeignClientHystrix-findById");

        return null;
    }

    @Override
    public Integer update(String mapperId, SystemResource systemResource) {
        System.out.println("SystemResourceFeignClientHystrix-update");

        return null;
    }

    @Override
    public Integer delete(String mapperId, SystemResource systemResource) {
        System.out.println("SystemResourceFeignClientHystrix-delete");

        return null;
    }

    @Override
    public List<SystemResource> findResourceUrlListByRoleId(Integer roleId) {
        System.out.println("SystemResourceFeignClientHystrix-findResourceUrlListByRoleId");

        return null;
    }
}
