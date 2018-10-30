package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IPatientLoginInfoService;
import com.zhou.medical.common.entity.account.PatientLoginInfo;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="patientLoginInfoService")
@Transactional
public class PatientLoginInfoServiceImpl extends GenericServiceImpl<PatientLoginInfo, Integer>
        implements IPatientLoginInfoService {

}
