package com.zhou.medical.account.service;


import com.zhou.medical.common.entity.account.PatientUser;
import com.zhou.medical.dao.service.GenericService;

/**
 * 
 * @author Administrator
 *
 */
public interface IPatientUserService extends GenericService<PatientUser, Integer> {
	
	PatientUser findPatientUser(String patientPhone);
	
	String findPatientUser(String patientId, String memberId);

	String saveMemberInfo(String phone, String name, String idNumber, String gender, String age);

	String savePatientInfo(String phone, String name, String memberPhone, String age, String gender,
                                  String occupation, String height, String idNumber);

	String updatePatientInfo(String phone, String name, String memberPhone, String age,
                                    String gender, String occupation, String height, String idNumber, String memberId, String patientId);

	String updateMemberInfo(String phone, String name, String updatePhone, String idNumber, String gender, String age);

}
