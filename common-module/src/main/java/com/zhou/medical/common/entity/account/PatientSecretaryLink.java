package com.zhou.medical.common.entity.account;

import java.util.Date;

public class PatientSecretaryLink {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.patient_id
     *
     * @mbggenerated
     */
    private Integer patientId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.patient_name
     *
     * @mbggenerated
     */
    private String patientName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.patient_phone
     *
     * @mbggenerated
     */
    private String patientPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.patient_login_id
     *
     * @mbggenerated
     */
    private String patientLoginId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.secretary_id
     *
     * @mbggenerated
     */
    private Integer secretaryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.secretary_name
     *
     * @mbggenerated
     */
    private String secretaryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.secretary_phone
     *
     * @mbggenerated
     */
    private String secretaryPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.secretary_login_id
     *
     * @mbggenerated
     */
    private String secretaryLoginId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_secretary_link.updatetime
     *
     * @mbggenerated
     */
    private String updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.id
     *
     * @return the value of patient_secretary_link.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.id
     *
     * @param id the value for patient_secretary_link.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.patient_id
     *
     * @return the value of patient_secretary_link.patient_id
     *
     * @mbggenerated
     */
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.patient_id
     *
     * @param patientId the value for patient_secretary_link.patient_id
     *
     * @mbggenerated
     */
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.patient_name
     *
     * @return the value of patient_secretary_link.patient_name
     *
     * @mbggenerated
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.patient_name
     *
     * @param patientName the value for patient_secretary_link.patient_name
     *
     * @mbggenerated
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.patient_phone
     *
     * @return the value of patient_secretary_link.patient_phone
     *
     * @mbggenerated
     */
    public String getPatientPhone() {
        return patientPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.patient_phone
     *
     * @param patientPhone the value for patient_secretary_link.patient_phone
     *
     * @mbggenerated
     */
    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone == null ? null : patientPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.patient_login_id
     *
     * @return the value of patient_secretary_link.patient_login_id
     *
     * @mbggenerated
     */
    public String getPatientLoginId() {
        return patientLoginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.patient_login_id
     *
     * @param patientLoginId the value for patient_secretary_link.patient_login_id
     *
     * @mbggenerated
     */
    public void setPatientLoginId(String patientLoginId) {
        this.patientLoginId = patientLoginId == null ? null : patientLoginId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.secretary_id
     *
     * @return the value of patient_secretary_link.secretary_id
     *
     * @mbggenerated
     */
    public Integer getSecretaryId() {
        return secretaryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.secretary_id
     *
     * @param secretaryId the value for patient_secretary_link.secretary_id
     *
     * @mbggenerated
     */
    public void setSecretaryId(Integer secretaryId) {
        this.secretaryId = secretaryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.secretary_name
     *
     * @return the value of patient_secretary_link.secretary_name
     *
     * @mbggenerated
     */
    public String getSecretaryName() {
        return secretaryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.secretary_name
     *
     * @param secretaryName the value for patient_secretary_link.secretary_name
     *
     * @mbggenerated
     */
    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName == null ? null : secretaryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.secretary_phone
     *
     * @return the value of patient_secretary_link.secretary_phone
     *
     * @mbggenerated
     */
    public String getSecretaryPhone() {
        return secretaryPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.secretary_phone
     *
     * @param secretaryPhone the value for patient_secretary_link.secretary_phone
     *
     * @mbggenerated
     */
    public void setSecretaryPhone(String secretaryPhone) {
        this.secretaryPhone = secretaryPhone == null ? null : secretaryPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.secretary_login_id
     *
     * @return the value of patient_secretary_link.secretary_login_id
     *
     * @mbggenerated
     */
    public String getSecretaryLoginId() {
        return secretaryLoginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.secretary_login_id
     *
     * @param secretaryLoginId the value for patient_secretary_link.secretary_login_id
     *
     * @mbggenerated
     */
    public void setSecretaryLoginId(String secretaryLoginId) {
        this.secretaryLoginId = secretaryLoginId == null ? null : secretaryLoginId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.createtime
     *
     * @return the value of patient_secretary_link.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.createtime
     *
     * @param createtime the value for patient_secretary_link.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_secretary_link.updatetime
     *
     * @return the value of patient_secretary_link.updatetime
     *
     * @mbggenerated
     */
    public String getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_secretary_link.updatetime
     *
     * @param updatetime the value for patient_secretary_link.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }
}