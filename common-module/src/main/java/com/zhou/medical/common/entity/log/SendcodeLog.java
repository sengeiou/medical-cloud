package com.zhou.medical.common.entity.log;

import java.util.Date;

public class SendcodeLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sendcode_log.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sendcode_log.phonenumber
     *
     * @mbggenerated
     */
    private String phonenumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sendcode_log.sendCodelimitTimes
     *
     * @mbggenerated
     */
    private Integer sendcodelimittimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sendcode_log.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sendcode_log.updatetime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sendcode_log.id
     *
     * @return the value of sendcode_log.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sendcode_log.id
     *
     * @param id the value for sendcode_log.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sendcode_log.phonenumber
     *
     * @return the value of sendcode_log.phonenumber
     *
     * @mbggenerated
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sendcode_log.phonenumber
     *
     * @param phonenumber the value for sendcode_log.phonenumber
     *
     * @mbggenerated
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sendcode_log.sendCodelimitTimes
     *
     * @return the value of sendcode_log.sendCodelimitTimes
     *
     * @mbggenerated
     */
    public Integer getSendcodelimittimes() {
        return sendcodelimittimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sendcode_log.sendCodelimitTimes
     *
     * @param sendcodelimittimes the value for sendcode_log.sendCodelimitTimes
     *
     * @mbggenerated
     */
    public void setSendcodelimittimes(Integer sendcodelimittimes) {
        this.sendcodelimittimes = sendcodelimittimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sendcode_log.createtime
     *
     * @return the value of sendcode_log.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sendcode_log.createtime
     *
     * @param createtime the value for sendcode_log.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sendcode_log.updatetime
     *
     * @return the value of sendcode_log.updatetime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sendcode_log.updatetime
     *
     * @param updatetime the value for sendcode_log.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}