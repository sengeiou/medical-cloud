package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthReferee;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthRefereeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthRefereeService")
@Transactional
public class HealthRefereeServiceImpl extends GenericServiceImpl<HealthReferee, Integer>
		implements IHealthRefereeService {
	/**
	 * 
	 */
}
