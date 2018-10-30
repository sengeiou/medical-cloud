package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IDoctorsTeamUserLinkService;
import com.zhou.medical.common.entity.account.DoctorsTeamUserLink;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="doctorsTeamUserLinkService")
@Transactional
public class DoctorsTeamUserLinkServiceImpl extends GenericServiceImpl<DoctorsTeamUserLink, Integer>
        implements IDoctorsTeamUserLinkService {

}
