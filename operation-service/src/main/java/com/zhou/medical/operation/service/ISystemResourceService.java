package com.zhou.medical.operation.service;

import com.zhou.medical.common.entity.Tree;
import com.zhou.medical.common.entity.manager.SystemResource;
import com.zhou.medical.dao.service.GenericService;

import java.util.List;


/**
 * 
 * @author Administrator
 *
 */
public interface ISystemResourceService extends GenericService<SystemResource, Integer> {

	List<Tree> findAllTree();

	List<Tree> findAllTrees();

	List<SystemResource> findResourceUrlListByRoleId(Integer roleId);
	
}
