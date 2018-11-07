package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.ScienceColumn;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IScienceColumnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="scienceColumnService")
@Transactional
public class ScienceColumnServiceImpl extends GenericServiceImpl<ScienceColumn, Integer>
		implements IScienceColumnService {
	/**
	 * 
	 */
}
