package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IPatientSecretaryLinkService;
import com.zhou.medical.common.entity.account.PatientSecretaryLink;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="patientSecretaryLinkService")
@Transactional
public class PatientSecretaryLinkServiceImpl extends GenericServiceImpl<PatientSecretaryLink, Integer>
        implements IPatientSecretaryLinkService {

}
