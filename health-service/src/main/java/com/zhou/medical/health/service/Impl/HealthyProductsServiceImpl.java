package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthyProducts;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthyProductsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="healthyProductsService")
@Transactional
public class HealthyProductsServiceImpl extends GenericServiceImpl<HealthyProducts, Integer>
		implements IHealthyProductsService {
	/**
	 * 
	 */
}
