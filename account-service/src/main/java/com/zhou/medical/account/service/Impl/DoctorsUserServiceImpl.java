package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IDoctorsUserService;
import com.zhou.medical.common.entity.account.DoctorsUser;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="doctorsUserService")
@Transactional
public class DoctorsUserServiceImpl extends GenericServiceImpl<DoctorsUser, Integer>
        implements IDoctorsUserService {

}
