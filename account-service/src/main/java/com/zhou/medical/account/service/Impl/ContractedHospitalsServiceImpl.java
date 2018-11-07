package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IContractedHospitalsService;
import com.zhou.medical.common.entity.account.ContractedHospitals;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="contractedHospitalsService")
@Transactional
public class ContractedHospitalsServiceImpl extends GenericServiceImpl<ContractedHospitals, Integer> implements IContractedHospitalsService {

}
