package com.zhou.medical.account.service.Impl;

import com.zhou.medical.account.service.IDoctorsSecretaryLinkService;
import com.zhou.medical.common.entity.account.DoctorsSecretaryLink;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="doctorsSecretaryLinkService")
@Transactional
public class DoctorsSecretaryLinkServiceImpl extends GenericServiceImpl<DoctorsSecretaryLink, Integer>
        implements IDoctorsSecretaryLinkService {

}
