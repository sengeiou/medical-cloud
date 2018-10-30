package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IPatientDoctorsLinkService;
import com.zhou.medical.common.entity.account.PatientDoctorsLink;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="patientDoctorsLinkService")
@Transactional
public class PatientDoctorsLinkServiceImpl extends GenericServiceImpl<PatientDoctorsLink, Integer>
        implements IPatientDoctorsLinkService {

}
