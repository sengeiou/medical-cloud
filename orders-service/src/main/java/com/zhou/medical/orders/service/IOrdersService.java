package com.zhou.medical.orders.service;

import com.alibaba.fastjson.JSONObject;
import com.zhou.medical.common.entity.order.Orders;
import com.zhou.medical.dao.service.GenericService;

/**
 * 
 * @author Administrator
 *
 */
public interface IOrdersService extends GenericService<Orders, Integer> {

	void updateOrders(Orders orders);

	public String saveDoctorPatient(String doctorPhone, String patientPhone);
	
	public JSONObject createPaymentOrders(String orderNo, String amount, String subject, String body, String detail, String attach);
}
