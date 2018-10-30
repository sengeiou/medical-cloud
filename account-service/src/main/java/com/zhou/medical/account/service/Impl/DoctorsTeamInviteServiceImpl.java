package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IDoctorsTeamInviteService;
import com.zhou.medical.common.entity.account.DoctorsTeamInvite;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="doctorsTeamInviteService")
@Transactional
public class DoctorsTeamInviteServiceImpl extends GenericServiceImpl<DoctorsTeamInvite, Integer>
        implements IDoctorsTeamInviteService {

}
