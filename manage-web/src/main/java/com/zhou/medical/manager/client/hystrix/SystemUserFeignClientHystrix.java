package com.zhou.medical.manager.client.hystrix;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemUser;
import com.zhou.medical.manager.client.operation.SystemUserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class SystemUserFeignClientHystrix implements SystemUserFeignClient {

    @Override
    public SystemUser findByParamFromSelectByUserName(String param) {
        System.out.println("SystemUserFeignClientHystrix-findByParamFromSelectByUserName");
        return null;
    }

    @Override
    public SystemUser findByParamFromSelectByPrimaryKey(Integer param) {
        System.out.println("SystemUserFeignClientHystrix-findByParamFromSelectByPrimaryKey");

        return null;
    }

    @Override
    public Integer update(String mapperId, SystemUser systemUser) {
        System.out.println("SystemUserFeignClientHystrix-update");

        return null;
    }

    @Override
    public Pager<SystemUser> getList(String mapperId, Integer page, Integer rows, SystemUser systemUser) {
        System.out.println("SystemUserFeignClientHystrix-getList");

        return null;
    }

    @Override
    public Integer insert(SystemUser systemUser) {
        System.out.println("SystemUserFeignClientHystrix-insert");

        return null;
    }

    @Override
    public SystemUser findById(String mapperId, Integer param) {
        System.out.println("SystemUserFeignClientHystrix-findById");

        return null;
    }

    @Override
    public Integer delete(String mapperId, SystemUser systemUser) {
        System.out.println("SystemUserFeignClientHystrix-delete");

        return null;
    }
}
