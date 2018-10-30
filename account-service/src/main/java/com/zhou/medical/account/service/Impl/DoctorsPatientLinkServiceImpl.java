package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IDoctorsPatientLinkService;
import com.zhou.medical.common.entity.account.DoctorsPatientLink;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="doctorsPatientLinkService")
@Transactional
public class DoctorsPatientLinkServiceImpl extends GenericServiceImpl<DoctorsPatientLink, Integer>
        implements IDoctorsPatientLinkService
{

}
