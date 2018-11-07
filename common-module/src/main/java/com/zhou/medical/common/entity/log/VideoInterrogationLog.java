package com.zhou.medical.common.entity.log;

import java.util.Date;

public class VideoInterrogationLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.orders_id
     *
     * @mbggenerated
     */
    private Integer ordersId;
    
    private String videoUrl;

    public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.from_user_loginid
     *
     * @mbggenerated
     */
    private String fromUserLoginid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.from_user_phone
     *
     * @mbggenerated
     */
    private String fromUserPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.from_user_type
     *
     * @mbggenerated
     */
    private Integer fromUserType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.to_user_loginid
     *
     * @mbggenerated
     */
    private String toUserLoginid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.to_user_phone
     *
     * @mbggenerated
     */
    private String toUserPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.video_time
     *
     * @mbggenerated
     */
    private Integer videoTime;
    
    private Integer state;

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column video_interrogation_log.updatetime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.id
     *
     * @return the value of video_interrogation_log.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.id
     *
     * @param id the value for video_interrogation_log.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.orders_id
     *
     * @return the value of video_interrogation_log.orders_id
     *
     * @mbggenerated
     */
    public Integer getOrdersId() {
        return ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.orders_id
     *
     * @param ordersId the value for video_interrogation_log.orders_id
     *
     * @mbggenerated
     */
    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.from_user_loginid
     *
     * @return the value of video_interrogation_log.from_user_loginid
     *
     * @mbggenerated
     */
    public String getFromUserLoginid() {
        return fromUserLoginid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.from_user_loginid
     *
     * @param fromUserLoginid the value for video_interrogation_log.from_user_loginid
     *
     * @mbggenerated
     */
    public void setFromUserLoginid(String fromUserLoginid) {
        this.fromUserLoginid = fromUserLoginid == null ? null : fromUserLoginid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.from_user_phone
     *
     * @return the value of video_interrogation_log.from_user_phone
     *
     * @mbggenerated
     */
    public String getFromUserPhone() {
        return fromUserPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.from_user_phone
     *
     * @param fromUserPhone the value for video_interrogation_log.from_user_phone
     *
     * @mbggenerated
     */
    public void setFromUserPhone(String fromUserPhone) {
        this.fromUserPhone = fromUserPhone == null ? null : fromUserPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.from_user_type
     *
     * @return the value of video_interrogation_log.from_user_type
     *
     * @mbggenerated
     */
    public Integer getFromUserType() {
        return fromUserType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.from_user_type
     *
     * @param fromUserType the value for video_interrogation_log.from_user_type
     *
     * @mbggenerated
     */
    public void setFromUserType(Integer fromUserType) {
        this.fromUserType = fromUserType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.to_user_loginid
     *
     * @return the value of video_interrogation_log.to_user_loginid
     *
     * @mbggenerated
     */
    public String getToUserLoginid() {
        return toUserLoginid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.to_user_loginid
     *
     * @param toUserLoginid the value for video_interrogation_log.to_user_loginid
     *
     * @mbggenerated
     */
    public void setToUserLoginid(String toUserLoginid) {
        this.toUserLoginid = toUserLoginid == null ? null : toUserLoginid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.to_user_phone
     *
     * @return the value of video_interrogation_log.to_user_phone
     *
     * @mbggenerated
     */
    public String getToUserPhone() {
        return toUserPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.to_user_phone
     *
     * @param toUserPhone the value for video_interrogation_log.to_user_phone
     *
     * @mbggenerated
     */
    public void setToUserPhone(String toUserPhone) {
        this.toUserPhone = toUserPhone == null ? null : toUserPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.video_time
     *
     * @return the value of video_interrogation_log.video_time
     *
     * @mbggenerated
     */
    public Integer getVideoTime() {
        return videoTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.video_time
     *
     * @param videoTime the value for video_interrogation_log.video_time
     *
     * @mbggenerated
     */
    public void setVideoTime(Integer videoTime) {
        this.videoTime = videoTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.createtime
     *
     * @return the value of video_interrogation_log.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.createtime
     *
     * @param createtime the value for video_interrogation_log.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column video_interrogation_log.updatetime
     *
     * @return the value of video_interrogation_log.updatetime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column video_interrogation_log.updatetime
     *
     * @param updatetime the value for video_interrogation_log.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}