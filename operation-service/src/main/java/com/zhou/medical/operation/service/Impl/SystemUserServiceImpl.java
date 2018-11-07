package com.zhou.medical.operation.service.Impl;

import com.zhou.medical.common.entity.operation.SystemUser;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.operation.service.ISystemUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 意见反馈Service
 * @author Administrator
 *
 */
@Service(value="systemUserService")
@Transactional
public class SystemUserServiceImpl extends GenericServiceImpl<SystemUser, Integer>
		implements ISystemUserService {

}
