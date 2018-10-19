package com.zhou.medical.dao;

import com.zhou.medical.common.entity.Pager;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author mikel
 * @date 2013-7-15 上午9:29:19
 * @description 
 */

@SuppressWarnings("unchecked")
@Component
@Scope("prototype")
public class MybatisDAO {

	@Autowired
	@Qualifier("masterSqlSessionTemplate")
	private SqlSession masterSqlSessionTemplate;

	/**
	 * mapperName
	 */
	private String mapperName;
	
	/**
	 * @param mapperName the mapperName to set
	 */
	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public <T> T findById(Object id) {

		return (T)masterSqlSessionTemplate.selectOne(mapperName +".getById",id);
	}
	/**
	 * 根据参数查找
	 * @param mapperId
	 * @param params
	 * @return
	 */
	public <T> T findByParam(String mapperId,Object params){
		return (T)masterSqlSessionTemplate.selectOne(mapperName+"."+mapperId,params);
	}
	/**
	 * 查找
	 * @param mapperId
	 * @param params
	 * @return
	 */
	public Object findUnique(String mapperId,Object params){
		return masterSqlSessionTemplate.selectOne(mapperName+"."+mapperId,params);
	}
	
	/**
	 * 根据配置的指定查询语句查询实体列表
	 * @param params
	 * @return
	 */
	public <T> List<T> getList(Object params){
		return masterSqlSessionTemplate.selectList(mapperName+".getList", params);
	}
	/**
	 * 获取列表
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> getList(Object params,int pageIndex ,int pageSize){
		pageIndex=pageIndex<=0?1:pageIndex;
		pageSize=pageSize<=0?10:pageSize;
		int offset = (pageIndex-1)*pageSize;
		RowBounds row = new RowBounds(offset,pageSize);
		return masterSqlSessionTemplate.selectList(mapperName+".getList", params,row);
	}
	/**
	 * 获取列表
	 * @param mapperId
	 * @param pageIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public <T> Pager<T> getList(String mapperId, int pageIndex , int pageSize , Object params){
		Pager<T> pager = new Pager<T>();
		if(pageSize <= 0){
			pager.setQueryAll(true);
		}else{
			pager.setPageSize(pageSize);
		}
		pager.setPageIndex(pageIndex <= 0 ? 1 : pageIndex);
		List<T> list = masterSqlSessionTemplate.selectList(mapperName+"."+mapperId, params,pager);
		pager.setList(list);
		return pager;
	}
	/**
	 * 全局插入
	 * @param entity
	 * @return
	 */
	public <E> int globalInsert(E entity){
		return masterSqlSessionTemplate.insert(entity.getClass().getName()+".insert", entity);
	}
	/**
	 * 全局更新
	 * @param mapperId
	 * @param entity
	 * @return
	 */
	public <E> int globalUpdate(String mapperId ,E entity){
		return masterSqlSessionTemplate.insert(entity.getClass().getName()+"."+mapperId, entity);
	}
	/**
	 * 插入
	 * @param entity
	 * @return
	 */
	public <T> int insert(T entity){
		return masterSqlSessionTemplate.insert(mapperName+".insert", entity);
	}
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	public <T> int update(T entity){
		return masterSqlSessionTemplate.update(mapperName+".update", entity);
	}
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	public <T> int delete(T entity){
		return masterSqlSessionTemplate.update(mapperName+".delete", entity);
	}
	/**
	 * 插入
	 * @param mapperId
	 * @param entity
	 * @return
	 */
	public <T> int insert(String mapperId,T entity){
		return masterSqlSessionTemplate.insert(mapperName+"."+mapperId, entity);
	}
	/**
	 * 更新
	 * @param mapperId
	 * @param entity
	 * @return
	 */
	public <T> int update(String mapperId,T entity){
		return masterSqlSessionTemplate.update(mapperName+"."+mapperId, entity);
	}
	/**
	 * 删除
	 * @param mapperId
	 * @param entity
	 * @return
	 */
	public <T> int delete(String mapperId,T entity){
		return masterSqlSessionTemplate.update(mapperName+"."+mapperId, entity);
	}
	/**
	 * 获取列表
	 * @param mapperId
	 * @param params
	 * @return
	 */
	public <T> List<T> getList(String mapperId,Object params){
		return masterSqlSessionTemplate.selectList(mapperName+"."+mapperId, params);
	}
	/**
	 * 获取列表
	 * @param mapperId
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> getList(String mapperId,Object params,int pageIndex ,int pageSize){
		pageIndex=pageIndex<=0?1:pageIndex;
		pageSize=pageSize<=0?10:pageSize;
		int offset = (pageIndex-1)*pageSize;
		RowBounds row = new RowBounds(offset,pageSize);
		return masterSqlSessionTemplate.selectList(mapperName+"."+mapperId, params,row);
	}
	
	/**
	 * 获取列表
	 * @param mapperId
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String mapperId,Object params){
		return masterSqlSessionTemplate.selectList(mapperName+"."+mapperId, params);
	}
	/**
	 * 获取列表
	 * @param mapperId
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String mapperId,Object params,int pageIndex ,int pageSize){
		pageIndex=pageIndex<=0?1:pageIndex;
		pageSize=pageSize<=0?10:pageSize;
		int offset = (pageIndex-1)*pageSize;
		RowBounds row = new RowBounds(offset,pageSize);
		return masterSqlSessionTemplate.selectList(mapperName+"."+mapperId, params,row);
	}
	/**
	 * 获取列表
	 * @param mapperId
	 * @param pageIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Pager<List<Map<String, Object>>> queryForList(String mapperId, int pageIndex , int pageSize , Object params){
		Pager<List<Map<String, Object>>> pager = new Pager<List<Map<String, Object>>>();
		if(pageSize <= 0){
			pager.setQueryAll(true);
		}else{
			pager.setPageSize(pageSize);
		}
		pager.setPageIndex(pageIndex <= 0 ? 1 : pageIndex);
		List<List<Map<String, Object>>> list = masterSqlSessionTemplate.selectList(mapperName+"."+mapperId, params,pager);
		pager.setList(list);
		return pager;
	}
	
	
}
