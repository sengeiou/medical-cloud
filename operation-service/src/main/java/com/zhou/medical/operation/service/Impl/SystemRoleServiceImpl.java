package com.zhou.medical.operation.service.Impl;

import com.zhou.medical.common.entity.operation.SystemRole;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.operation.service.ISystemRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author Administrator
 *
 */
@Service(value="systemRoleService")
@Transactional
public class SystemRoleServiceImpl extends GenericServiceImpl<SystemRole, Integer> implements ISystemRoleService {

}
