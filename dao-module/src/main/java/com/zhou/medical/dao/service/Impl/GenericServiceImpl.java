package com.zhou.medical.dao.service.Impl;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.util.GenericsUtils;
import com.zhou.medical.dao.MybatisDAO;
import com.zhou.medical.dao.SlavebatisDAO;
import com.zhou.medical.dao.service.GenericService;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 * @param <ID>
 */
//@Service(value = "genericService")
public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

	private static Logger log = Logger.getLogger(GenericServiceImpl.class);

	/**
	 * persistentClass
	 */
	private Class<T> persistentClass; // T类对应的持久类
	/**
	 * mybatisDAO
	 */
	protected MybatisDAO mybatisDAO;
	/**
	 * slavebatisDAO
	 */
	protected SlavebatisDAO slavebatisDAO;


	/**
	 * GenericServiceImpl
	 */
	@SuppressWarnings("unchecked")
	public GenericServiceImpl() {
//		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.persistentClass = GenericsUtils.getSuperClassGenricType(getClass(),0);
	}

	/**
	 * @param mybatisDAO the mybatisDAO to set
	 */
	
	/**
	 * setMybatisDAO
	 * @param mybatisDAO
	 */
	@Resource
	public void setMybatisDAO(MybatisDAO mybatisDAO) {
		mybatisDAO.setMapperName(persistentClass.getName());
		this.mybatisDAO = mybatisDAO;
	}
	/**
	 * @return the slavebatisDAO
	 */
	public SlavebatisDAO getSlavebatisDAO() {
		return slavebatisDAO;
	}

	/**
	 * @param slavebatisDAO the slavebatisDAO to set
	 */
	@Resource
	public void setSlavebatisDAO(SlavebatisDAO slavebatisDAO) {
		slavebatisDAO.setMapperName(persistentClass.getName());
		this.slavebatisDAO = slavebatisDAO;
	}

	/**
	 * findById
	 */
	@Override
	public T findById(String mapperId, ID id) {
		return slavebatisDAO.findByParam(mapperId, id);
	}
	/**
	 * findByParam
	 */
	@Override
	public T findByParam(String mapperId, Object param) {
		return slavebatisDAO.findByParam(mapperId, param);
	}
	/**
	 * getList
	 */
	@Override
	public List<T> getList(String mapperId, Object params) {
		return slavebatisDAO.getList(mapperId, params);
	}
	/**
	 * getList
	 */
	@Override
	public List<T> getList(String mapperId, Object params, int pageIndex, int pageSize) {
		return slavebatisDAO.getList(mapperId, params, pageIndex, pageSize);
	}
	/**
	 * getList
	 */
	@Override
	public Pager<T> getList(String mapperId, int pageIndex, int pageSize,
							Object params) {
		return slavebatisDAO.getList(mapperId, pageIndex, pageSize, params);
	}
	/**
	 * insert
	 */
	@Override
	public int insert(T entity) {
		return mybatisDAO.insert(entity);
	}
	/**
	 * insert
	 */
	@Override
	public int insert(String mapperId, T entity) {
		return mybatisDAO.insert(mapperId, entity);
	}
	/**
	 * update
	 */
	@Override
	public int update(T entity) {
		return mybatisDAO.update(entity);
	}
	/**
	 * update
	 */
	@Override
	public int update(String mapperId, T entity) {
		return mybatisDAO.update(mapperId, entity);
	}
	/**
	 * delete
	 */
	@Override
	public int delete(T entity) {
		return mybatisDAO.delete(entity);
	}
	/**
	 * delete
	 */
	@Override
	public int delete(String mapperId, T entity) {
		return mybatisDAO.delete(mapperId, entity);
	}
	/**
	 * count
	 */
	@Override
	public int count(String mapperId, Object ob) {
		return slavebatisDAO.findByParam(mapperId, ob);
	}
	/**
	 * findById
	 */
	@Override
	public T findById(ID id)  {
		return slavebatisDAO.findById(id);
	}
	/**
	 * getList
	 */
	@Override
	public List<T> getList(Object params) {
		return slavebatisDAO.getList(params);
	}
	/**
	 * getList
	 */
	@Override
	public List<T> getList(Object params, int pageIndex, int pageSize) {
		return slavebatisDAO.getList(params, pageIndex, pageSize);
	}
	/**
	 * findObjectByParam
	 */
	@Override
	public Map<String, Object> findObjectByParam(String mapperId, Object param) {
		// TODO Auto-generated method stub
		return slavebatisDAO.findByParam(mapperId, param);
	}
	/**
	 * queryForList
	 */
	@Override
	public List<Map<String, Object>> queryForList(String mapperId, Object params) {
		return slavebatisDAO.queryForList(mapperId,params);
	}
	/**
	 * queryForList
	 */
	@Override
	public List<Map<String, Object>> queryForList(String mapperId, Object params, int pageIndex, int pageSize) {
		return slavebatisDAO.queryForList(mapperId,params,pageIndex,pageSize);
	}
	/**
	 * queryForList
	 */
	@Override
	public Pager<List<Map<String, Object>>> queryForList(String mapperId,
                                                         int pageIndex, int pageSize, Object params) {
		return slavebatisDAO.queryForList(mapperId,pageIndex,pageSize,params);
	}
	
}
