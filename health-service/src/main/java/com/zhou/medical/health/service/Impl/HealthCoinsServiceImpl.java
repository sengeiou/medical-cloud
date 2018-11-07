package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthCoins;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthCoinsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthCoinsService")
@Transactional
public class HealthCoinsServiceImpl extends GenericServiceImpl<HealthCoins, Integer>
		implements IHealthCoinsService {
	/**
	 * 
	 */
}
