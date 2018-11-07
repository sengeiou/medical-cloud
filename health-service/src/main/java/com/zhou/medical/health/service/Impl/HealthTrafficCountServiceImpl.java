package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthTrafficCount;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthTrafficCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthTrafficCountService")
@Transactional
public class HealthTrafficCountServiceImpl extends GenericServiceImpl<HealthTrafficCount, Integer>
		implements IHealthTrafficCountService {
	/**
	 * 
	 */
}
