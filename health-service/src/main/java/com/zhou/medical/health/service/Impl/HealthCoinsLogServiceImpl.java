package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthCoinsLog;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthCoinsLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthCoinsLogService")
@Transactional
public class HealthCoinsLogServiceImpl extends GenericServiceImpl<HealthCoinsLog, Integer>
		implements IHealthCoinsLogService {
	/**
	 * 
	 */
}
