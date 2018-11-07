package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthPrizes;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthPrizesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthPrizesService")
@Transactional
public class HealthPrizesServiceImpl extends GenericServiceImpl<HealthPrizes, Integer>
		implements IHealthPrizesService {
	/**
	 * 
	 */
}
