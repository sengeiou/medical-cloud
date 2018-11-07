package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.HealthDeviceInfo;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IHealthDeviceInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 *
 */
@Service(value="healthDeviceInfoService")
@Transactional
public class HealthDeviceInfoServiceImpl extends GenericServiceImpl<HealthDeviceInfo, Integer>
        implements IHealthDeviceInfoService {

}
