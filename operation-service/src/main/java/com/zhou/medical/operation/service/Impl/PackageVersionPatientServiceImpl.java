package com.zhou.medical.operation.service.Impl;

import com.zhou.medical.common.entity.operation.PackageVersionPatient;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.operation.service.IPackageVersionPatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 */
@Service(value = "packageVersionPatientService")
@Transactional
public class PackageVersionPatientServiceImpl extends GenericServiceImpl<PackageVersionPatient, Integer> implements IPackageVersionPatientService {

}
