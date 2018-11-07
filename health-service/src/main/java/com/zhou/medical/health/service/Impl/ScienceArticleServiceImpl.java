package com.zhou.medical.health.service.Impl;

import com.zhou.medical.common.entity.health.ScienceArticle;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.health.service.IScienceArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Administrator
 *
 */
@Service(value="scienceArticleService")
@Transactional
public class ScienceArticleServiceImpl extends GenericServiceImpl<ScienceArticle, Integer>
		implements IScienceArticleService {
	/**
	 * 
	 */
}
