package com.zhou.medical.common.entity.account;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DoctorsUser implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.login_id
     *
     * @mbggenerated
     */
    private String loginId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.sex
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.head_url
     *
     * @mbggenerated
     */
    private String headUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.hospital
     *
     * @mbggenerated
     */
    private String hospital;
    
    private Integer hospitalId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.department
     *
     * @mbggenerated
     */
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.start_working_time
     *
     * @mbggenerated
     */
    private Date startWorkingTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.certificate_url
     *
     * @mbggenerated
     */
    private String certificateUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.department_telephone
     *
     * @mbggenerated
     */
    private String departmentTelephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.introduction
     *
     * @mbggenerated
     */
    private String introduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.updatetime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.is_phone_consultation
     *
     * @mbggenerated
     */
    private Integer isPhoneConsultation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.is_video_consultation
     *
     * @mbggenerated
     */
    private Integer isVideoConsultation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.easemob_uuid
     *
     * @mbggenerated
     */
    private String easemobUuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_user.doctorType
     *
     * @mbggenerated
     */
    private Integer doctortype;
    
    private Integer DoctorsPatientLinkCount;
    
    private DoctorsTeam doctorsTeam;
    
    private Integer doctorsTeamUserLinkCount;
    
    private String recommended;
    
    private int graphicConsulting;
    
    private int isQuickConsulting;
    
    private int isAssistantApply;
    
    private int testAccount;
    
    private String graphicConsultingPrice;
    
    private String videoConsultationPrice;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contracted_hospitals.city_name
     *
     * @mbggenerated
     */
    private String province;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contracted_hospitals.city_name
     *
     * @mbggenerated
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contracted_hospitals.area
     *
     * @mbggenerated
     */
    private String area;

    private Integer status;
    
    private String onlineState;
    
    private Integer sequence;
    
    public String getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.id
     *
     * @return the value of doctors_user.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.id
     *
     * @param id the value for doctors_user.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.password
     *
     * @return the value of doctors_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.password
     *
     * @param password the value for doctors_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.login_id
     *
     * @return the value of doctors_user.login_id
     *
     * @mbggenerated
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.login_id
     *
     * @param loginId the value for doctors_user.login_id
     *
     * @mbggenerated
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.phone
     *
     * @return the value of doctors_user.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.phone
     *
     * @param phone the value for doctors_user.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.name
     *
     * @return the value of doctors_user.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.name
     *
     * @param name the value for doctors_user.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.sex
     *
     * @return the value of doctors_user.sex
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.sex
     *
     * @param sex the value for doctors_user.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.head_url
     *
     * @return the value of doctors_user.head_url
     *
     * @mbggenerated
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.head_url
     *
     * @param headUrl the value for doctors_user.head_url
     *
     * @mbggenerated
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.title
     *
     * @return the value of doctors_user.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.title
     *
     * @param title the value for doctors_user.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.hospital
     *
     * @return the value of doctors_user.hospital
     *
     * @mbggenerated
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.hospital
     *
     * @param hospital the value for doctors_user.hospital
     *
     * @mbggenerated
     */
    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    /**
	 * @return the hospitalId
	 */
	public Integer getHospitalId() {
		return hospitalId;
	}

	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.department
     *
     * @return the value of doctors_user.department
     *
     * @mbggenerated
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.department
     *
     * @param department the value for doctors_user.department
     *
     * @mbggenerated
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.start_working_time
     *
     * @return the value of doctors_user.start_working_time
     *
     * @mbggenerated
     */
    public Date getStartWorkingTime() {
        return startWorkingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.start_working_time
     *
     * @param startWorkingTime the value for doctors_user.start_working_time
     *
     * @mbggenerated
     */
    public void setStartWorkingTime(Date startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.certificate_url
     *
     * @return the value of doctors_user.certificate_url
     *
     * @mbggenerated
     */
    public String getCertificateUrl() {
        return certificateUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.certificate_url
     *
     * @param certificateUrl the value for doctors_user.certificate_url
     *
     * @mbggenerated
     */
    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl == null ? null : certificateUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.department_telephone
     *
     * @return the value of doctors_user.department_telephone
     *
     * @mbggenerated
     */
    public String getDepartmentTelephone() {
        return departmentTelephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.department_telephone
     *
     * @param departmentTelephone the value for doctors_user.department_telephone
     *
     * @mbggenerated
     */
    public void setDepartmentTelephone(String departmentTelephone) {
        this.departmentTelephone = departmentTelephone == null ? null : departmentTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.introduction
     *
     * @return the value of doctors_user.introduction
     *
     * @mbggenerated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.introduction
     *
     * @param introduction the value for doctors_user.introduction
     *
     * @mbggenerated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.createtime
     *
     * @return the value of doctors_user.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.createtime
     *
     * @param createtime the value for doctors_user.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.updatetime
     *
     * @return the value of doctors_user.updatetime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.updatetime
     *
     * @param updatetime the value for doctors_user.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.is_phone_consultation
     *
     * @return the value of doctors_user.is_phone_consultation
     *
     * @mbggenerated
     */
    public Integer getIsPhoneConsultation() {
        return isPhoneConsultation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.is_phone_consultation
     *
     * @param isPhoneConsultation the value for doctors_user.is_phone_consultation
     *
     * @mbggenerated
     */
    public void setIsPhoneConsultation(Integer isPhoneConsultation) {
        this.isPhoneConsultation = isPhoneConsultation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.is_video_consultation
     *
     * @return the value of doctors_user.is_video_consultation
     *
     * @mbggenerated
     */
    public Integer getIsVideoConsultation() {
        return isVideoConsultation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.is_video_consultation
     *
     * @param isVideoConsultation the value for doctors_user.is_video_consultation
     *
     * @mbggenerated
     */
    public void setIsVideoConsultation(Integer isVideoConsultation) {
        this.isVideoConsultation = isVideoConsultation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.easemob_uuid
     *
     * @return the value of doctors_user.easemob_uuid
     *
     * @mbggenerated
     */
    public String getEasemobUuid() {
        return easemobUuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.easemob_uuid
     *
     * @param easemobUuid the value for doctors_user.easemob_uuid
     *
     * @mbggenerated
     */
    public void setEasemobUuid(String easemobUuid) {
        this.easemobUuid = easemobUuid == null ? null : easemobUuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_user.doctorType
     *
     * @return the value of doctors_user.doctorType
     *
     * @mbggenerated
     */
    public Integer getDoctortype() {
        return doctortype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_user.doctorType
     *
     * @param doctortype the value for doctors_user.doctorType
     *
     * @mbggenerated
     */
    public void setDoctortype(Integer doctortype) {
        this.doctortype = doctortype;
    }

	/**
	 * @return the doctorsPatientLinkCount
	 */
	public Integer getDoctorsPatientLinkCount() {
		return DoctorsPatientLinkCount;
	}

	/**
	 * @param doctorsPatientLinkCount the doctorsPatientLinkCount to set
	 */
	public void setDoctorsPatientLinkCount(Integer doctorsPatientLinkCount) {
		DoctorsPatientLinkCount = doctorsPatientLinkCount;
	}

	/**
	 * @return the doctorsTeam
	 */
	public DoctorsTeam getDoctorsTeam() {
		return doctorsTeam;
	}

	/**
	 * @param doctorsTeam the doctorsTeam to set
	 */
	public void setDoctorsTeam(DoctorsTeam doctorsTeam) {
		this.doctorsTeam = doctorsTeam;
	}

	/**
	 * @return the doctorsTeamUserLinkCount
	 */
	public Integer getDoctorsTeamUserLinkCount() {
		return doctorsTeamUserLinkCount;
	}

	/**
	 * @param doctorsTeamUserLinkCount the doctorsTeamUserLinkCount to set
	 */
	public void setDoctorsTeamUserLinkCount(Integer doctorsTeamUserLinkCount) {
		this.doctorsTeamUserLinkCount = doctorsTeamUserLinkCount;
	}

	/**
	 * @return the recommended
	 */
	public String getRecommended() {
		return recommended;
	}

	/**
	 * @param recommended the recommended to set
	 */
	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	/**
	 * @return the graphicConsulting
	 */
	public int getGraphicConsulting() {
		return graphicConsulting;
	}

	/**
	 * @param graphicConsulting the graphicConsulting to set
	 */
	public void setGraphicConsulting(int graphicConsulting) {
		this.graphicConsulting = graphicConsulting;
	}

	/**
	 * @return the isQuickConsulting
	 */
	public int getIsQuickConsulting() {
		return isQuickConsulting;
	}

	/**
	 * @param isQuickConsulting the isQuickConsulting to set
	 */
	public void setIsQuickConsulting(int isQuickConsulting) {
		this.isQuickConsulting = isQuickConsulting;
	}

	/**
	 * @return the isAssistantApply
	 */
	public int getIsAssistantApply() {
		return isAssistantApply;
	}

	/**
	 * @param isAssistantApply the isAssistantApply to set
	 */
	public void setIsAssistantApply(int isAssistantApply) {
		this.isAssistantApply = isAssistantApply;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the testAccount
	 */
	public int getTestAccount() {
		return testAccount;
	}

	/**
	 * @param testAccount the testAccount to set
	 */
	public void setTestAccount(int testAccount) {
		this.testAccount = testAccount;
	}

	/**
	 * @return the graphicConsultingPrice
	 */
	public String getGraphicConsultingPrice() {
		return graphicConsultingPrice;
	}

	/**
	 * @param graphicConsultingPrice the graphicConsultingPrice to set
	 */
	public void setGraphicConsultingPrice(String graphicConsultingPrice) {
		this.graphicConsultingPrice = graphicConsultingPrice;
	}

	/**
	 * @return the videoConsultationPrice
	 */
	public String getVideoConsultationPrice() {
		return videoConsultationPrice;
	}

	/**
	 * @param videoConsultationPrice the videoConsultationPrice to set
	 */
	public void setVideoConsultationPrice(String videoConsultationPrice) {
		this.videoConsultationPrice = videoConsultationPrice;
	}

	/**
	 * @return the sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}