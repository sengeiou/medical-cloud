package com.zhou.medical.operation.service;

import com.zhou.medical.common.entity.manager.SystemRoleResource;
import com.zhou.medical.dao.service.GenericService;

import java.util.List;


/**
 * 
 * @author Administrator
 *
 */
public interface ISystemRoleResourceService extends GenericService<SystemRoleResource, Integer> {

	void updateRoleResource(Integer id, String resourceIds);

	List<Integer> getRoleResourceIdListByRoleId(Integer id);
	
}
