package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthSteps;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthStepsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthStepsService")
@Transactional
public class HealthStepsServiceImpl extends GenericServiceImpl<HealthSteps, Integer>
		implements IHealthStepsService {
	/**
	 * 
	 */
}
