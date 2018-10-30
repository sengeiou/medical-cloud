package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IDoctorsTeamService;
import com.zhou.medical.common.entity.account.DoctorsTeam;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="doctorsTeamService")
@Transactional
public class DoctorsTeamServiceImpl extends GenericServiceImpl<DoctorsTeam, Integer>
        implements IDoctorsTeamService {

}
