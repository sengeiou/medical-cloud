package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IContractedHospitalsDoctorsLinkService;
import com.zhou.medical.common.entity.account.ContractedHospitalsDoctorsLink;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 */
@Service(value = "contractedHospitalsDoctorsLinkService")
@Transactional
public class ContractedHospitalsDoctorsLinkServiceImpl extends GenericServiceImpl<ContractedHospitalsDoctorsLink, Integer> implements IContractedHospitalsDoctorsLinkService {

}
