package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IDoctorsLoginInfoService;
import com.zhou.medical.common.entity.account.DoctorsLoginInfo;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="doctorsLoginInfoService")
@Transactional
public class DoctorsLoginInfoServiceImpl extends GenericServiceImpl<DoctorsLoginInfo, Integer>
        implements IDoctorsLoginInfoService {

}
