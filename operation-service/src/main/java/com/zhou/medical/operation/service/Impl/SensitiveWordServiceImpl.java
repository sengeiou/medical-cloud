package com.zhou.medical.operation.service.Impl;

import com.zhou.medical.common.entity.operation.SensitiveWord;
import com.zhou.medical.dao.service.Impl.GenericServiceImpl;
import com.zhou.medical.operation.service.ISensitiveWordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 敏感词Service
 * @author Administrator
 *
 */
@Service(value="sensitiveWordService")
@Transactional
public class SensitiveWordServiceImpl extends GenericServiceImpl<SensitiveWord, Integer>
		implements ISensitiveWordService {
	/**
	 * 
	 */
//	public boolean isSensitiveWord(String word) {
//		List<SensitiveWord> list= this.getList("selectAll", null);
//		for(SensitiveWord sensitiveWord:list){
//			if(word.contains(sensitiveWord.getText())){
//				return true;
//			}
//		}
//		return false;
//	}

}
