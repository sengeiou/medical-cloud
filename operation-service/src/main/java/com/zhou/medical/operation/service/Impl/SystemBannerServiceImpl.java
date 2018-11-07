package com.zhou.medical.operation.service.Impl;

import com.zhou.medical.common.entity.operation.SystemBanner;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.operation.service.ISystemBannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 意见反馈Service
 * @author Administrator
 *
 */
@Service(value="systemBannerService")
@Transactional
public class SystemBannerServiceImpl extends GenericServiceImpl<SystemBanner, Integer>
		implements ISystemBannerService {
	/**
	 * 
	 */
}
