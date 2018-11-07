package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthPrizesLog;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthPrizesLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthPrizesLogService")
@Transactional
public class HealthPrizesLogServiceImpl extends GenericServiceImpl<HealthPrizesLog, Integer>
		implements IHealthPrizesLogService {
	/**
	 * 
	 */
}
