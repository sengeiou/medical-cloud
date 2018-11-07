package com.zhou.medical.common.entity.inquiry;

import java.util.Date;

public class DoctorsFollowupPlanPointTemplate {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.doctors_followup_plan_template_id
     *
     * @mbggenerated
     */
    private Integer doctorsFollowupPlanTemplateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.far_from_last _time
     *
     * @mbggenerated
     */
    private Integer farFromLastTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.plan_time
     *
     * @mbggenerated
     */
    private Date planTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column doctors_followup_plan_point_template.updatetime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.id
     *
     * @return the value of doctors_followup_plan_point_template.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.id
     *
     * @param id the value for doctors_followup_plan_point_template.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.doctors_followup_plan_template_id
     *
     * @return the value of doctors_followup_plan_point_template.doctors_followup_plan_template_id
     *
     * @mbggenerated
     */
    public Integer getDoctorsFollowupPlanTemplateId() {
        return doctorsFollowupPlanTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.doctors_followup_plan_template_id
     *
     * @param doctorsFollowupPlanTemplateId the value for doctors_followup_plan_point_template.doctors_followup_plan_template_id
     *
     * @mbggenerated
     */
    public void setDoctorsFollowupPlanTemplateId(Integer doctorsFollowupPlanTemplateId) {
        this.doctorsFollowupPlanTemplateId = doctorsFollowupPlanTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.content
     *
     * @return the value of doctors_followup_plan_point_template.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.content
     *
     * @param content the value for doctors_followup_plan_point_template.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.type
     *
     * @return the value of doctors_followup_plan_point_template.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.type
     *
     * @param type the value for doctors_followup_plan_point_template.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.far_from_last _time
     *
     * @return the value of doctors_followup_plan_point_template.far_from_last _time
     *
     * @mbggenerated
     */
    public Integer getFarFromLastTime() {
        return farFromLastTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.far_from_last _time
     *
     * @param farFromLastTime the value for doctors_followup_plan_point_template.far_from_last _time
     *
     * @mbggenerated
     */
    public void setFarFromLastTime(Integer farFromLastTime) {
        this.farFromLastTime = farFromLastTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.plan_time
     *
     * @return the value of doctors_followup_plan_point_template.plan_time
     *
     * @mbggenerated
     */
    public Date getPlanTime() {
        return planTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.plan_time
     *
     * @param planTime the value for doctors_followup_plan_point_template.plan_time
     *
     * @mbggenerated
     */
    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.createtime
     *
     * @return the value of doctors_followup_plan_point_template.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.createtime
     *
     * @param createtime the value for doctors_followup_plan_point_template.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column doctors_followup_plan_point_template.updatetime
     *
     * @return the value of doctors_followup_plan_point_template.updatetime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column doctors_followup_plan_point_template.updatetime
     *
     * @param updatetime the value for doctors_followup_plan_point_template.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}