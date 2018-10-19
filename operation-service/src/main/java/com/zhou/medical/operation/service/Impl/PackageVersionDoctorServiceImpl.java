package com.zhou.medical.operation.service.Impl;

import com.zhou.medical.common.entity.manager.PackageVersionDoctor;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.operation.service.IPackageVersionDoctorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="packageVersionDoctorService")
@Transactional
public class PackageVersionDoctorServiceImpl extends GenericServiceImpl<PackageVersionDoctor, Integer> implements IPackageVersionDoctorService {

}
