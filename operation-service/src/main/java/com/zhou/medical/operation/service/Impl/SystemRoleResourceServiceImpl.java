package com.zhou.medical.operation.service.Impl;

import com.zhou.medical.common.entity.operation.SystemRoleResource;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.operation.service.ISystemRoleResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
@Service(value="systemRoleResourceService")
@Transactional
public class SystemRoleResourceServiceImpl extends GenericServiceImpl<SystemRoleResource, Integer> implements ISystemRoleResourceService {

	@Override
	public void updateRoleResource(Integer id, String resourceIds) {
        // 先删除该角色原来的资源，再新增资源
        List<SystemRoleResource> roleResourceList = this.getList("findRoleResourceListByRoleId", id);
        if (roleResourceList != null && (!roleResourceList.isEmpty())) {
            for (SystemRoleResource roleResource : roleResourceList) {
            	this.delete("deleteByPrimaryKey", roleResource);
            }
        }
        
        if (StringUtils.isNotBlank(resourceIds)) {
        	String[] resources = resourceIds.split(",");
            SystemRoleResource roleResource = new SystemRoleResource();
            for (String resourceId : resources) {
                roleResource.setRoleId(id);
                roleResource.setResourceId(Integer.parseInt(resourceId));
                this.insert(roleResource);
            }
        }
    }
	
	@Override
	public List<Integer> getRoleResourceIdListByRoleId(Integer id) {
		List<Integer> resourceIdList = new ArrayList<Integer>();
        // 先删除该角色原来的资源，再新增资源
        List<SystemRoleResource> roleResourceList = this.getList("findRoleResourceListByRoleId", id);
        if (roleResourceList != null && (!roleResourceList.isEmpty())) {
            for (SystemRoleResource roleResource : roleResourceList) {
            	resourceIdList.add(roleResource.getResourceId());
            }
        }
        
        return resourceIdList;
    }

}
