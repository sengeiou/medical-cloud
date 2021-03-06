package com.zhou.medical.common.entity.account;

import java.io.Serializable;
import java.util.Date;

public class PatientUser implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -3830423439770386259L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.password
     *
     * @mbggenerated
     */
    private String password;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.login_id
     *
     * @mbggenerated
     */
    private String loginId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.sex
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.birthday
     *
     * @mbggenerated
     */
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.head_url
     *
     * @mbggenerated
     */
    private String headUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.location
     *
     * @mbggenerated
     */
    private String location;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.occupation
     *
     * @mbggenerated
     */
    private String occupation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.visit_state
     *
     * @mbggenerated
     */
    private String visitState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.hospital
     *
     * @mbggenerated
     */
    private String hospital;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.remarks
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.updatetime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.easemob_uuid
     *
     * @mbggenerated
     */
    private String easemobUuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.source
     *
     * @mbggenerated
     */
    private Integer source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.identification
     *
     * @mbggenerated
     */
    private String identification;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_user.bp_deviceID
     *
     * @mbggenerated
     */
    private String bpDeviceid;
    private String bsDeviceid;
    
    public String getBsDeviceid() {
		return bsDeviceid;
	}

	public void setBsDeviceid(String bsDeviceid) {
		this.bsDeviceid = bsDeviceid;
	}

	private String medicalCard;

    public String getMedicalCard() {
		return medicalCard;
	}

	public void setMedicalCard(String medicalCard) {
		this.medicalCard = medicalCard;
	}

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.id
     *
     * @return the value of patient_user.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.id
     *
     * @param id the value for patient_user.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.password
     *
     * @return the value of patient_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.password
     *
     * @param password the value for patient_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.login_id
     *
     * @return the value of patient_user.login_id
     *
     * @mbggenerated
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.login_id
     *
     * @param loginId the value for patient_user.login_id
     *
     * @mbggenerated
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.phone
     *
     * @return the value of patient_user.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.phone
     *
     * @param phone the value for patient_user.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.name
     *
     * @return the value of patient_user.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.name
     *
     * @param name the value for patient_user.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.sex
     *
     * @return the value of patient_user.sex
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.sex
     *
     * @param sex the value for patient_user.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.birthday
     *
     * @return the value of patient_user.birthday
     *
     * @mbggenerated
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.birthday
     *
     * @param birthday the value for patient_user.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.head_url
     *
     * @return the value of patient_user.head_url
     *
     * @mbggenerated
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.head_url
     *
     * @param headUrl the value for patient_user.head_url
     *
     * @mbggenerated
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.location
     *
     * @return the value of patient_user.location
     *
     * @mbggenerated
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.location
     *
     * @param location the value for patient_user.location
     *
     * @mbggenerated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.occupation
     *
     * @return the value of patient_user.occupation
     *
     * @mbggenerated
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.occupation
     *
     * @param occupation the value for patient_user.occupation
     *
     * @mbggenerated
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.visit_state
     *
     * @return the value of patient_user.visit_state
     *
     * @mbggenerated
     */
    public String getVisitState() {
        return visitState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.visit_state
     *
     * @param visitState the value for patient_user.visit_state
     *
     * @mbggenerated
     */
    public void setVisitState(String visitState) {
        this.visitState = visitState == null ? null : visitState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.hospital
     *
     * @return the value of patient_user.hospital
     *
     * @mbggenerated
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.hospital
     *
     * @param hospital the value for patient_user.hospital
     *
     * @mbggenerated
     */
    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.remarks
     *
     * @return the value of patient_user.remarks
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.remarks
     *
     * @param remarks the value for patient_user.remarks
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.createtime
     *
     * @return the value of patient_user.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.createtime
     *
     * @param createtime the value for patient_user.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.updatetime
     *
     * @return the value of patient_user.updatetime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.updatetime
     *
     * @param updatetime the value for patient_user.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.easemob_uuid
     *
     * @return the value of patient_user.easemob_uuid
     *
     * @mbggenerated
     */
    public String getEasemobUuid() {
        return easemobUuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.easemob_uuid
     *
     * @param easemobUuid the value for patient_user.easemob_uuid
     *
     * @mbggenerated
     */
    public void setEasemobUuid(String easemobUuid) {
        this.easemobUuid = easemobUuid == null ? null : easemobUuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.source
     *
     * @return the value of patient_user.source
     *
     * @mbggenerated
     */
    public Integer getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.source
     *
     * @param source the value for patient_user.source
     *
     * @mbggenerated
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.identification
     *
     * @return the value of patient_user.identification
     *
     * @mbggenerated
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.identification
     *
     * @param identification the value for patient_user.identification
     *
     * @mbggenerated
     */
    public void setIdentification(String identification) {
        this.identification = identification == null ? null : identification.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_user.bp_deviceID
     *
     * @return the value of patient_user.bp_deviceID
     *
     * @mbggenerated
     */
    public String getBpDeviceid() {
        return bpDeviceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_user.bp_deviceID
     *
     * @param bpDeviceid the value for patient_user.bp_deviceID
     *
     * @mbggenerated
     */
    public void setBpDeviceid(String bpDeviceid) {
        this.bpDeviceid = bpDeviceid == null ? null : bpDeviceid.trim();
    }
}