package com.zhou.medical.common.entity.health;

import java.util.Date;

public class HealthyProducts {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.img_src
     *
     * @mbggenerated
     */
    private String imgSrc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.page_url
     *
     * @mbggenerated
     */
    private String pageUrl;
    
    /**
     * 
     */
    private Integer flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.introduction
     *
     * @mbggenerated
     */
    private String introduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.price
     *
     * @mbggenerated
     */
    private String price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column healthy_products.updatetime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.id
     *
     * @return the value of healthy_products.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.id
     *
     * @param id the value for healthy_products.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.type
     *
     * @return the value of healthy_products.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.type
     *
     * @param type the value for healthy_products.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.title
     *
     * @return the value of healthy_products.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.title
     *
     * @param title the value for healthy_products.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.img_src
     *
     * @return the value of healthy_products.img_src
     *
     * @mbggenerated
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.img_src
     *
     * @param imgSrc the value for healthy_products.img_src
     *
     * @mbggenerated
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc == null ? null : imgSrc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.page_url
     *
     * @return the value of healthy_products.page_url
     *
     * @mbggenerated
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.page_url
     *
     * @param pageUrl the value for healthy_products.page_url
     *
     * @mbggenerated
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    /**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.introduction
     *
     * @return the value of healthy_products.introduction
     *
     * @mbggenerated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.introduction
     *
     * @param introduction the value for healthy_products.introduction
     *
     * @mbggenerated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.price
     *
     * @return the value of healthy_products.price
     *
     * @mbggenerated
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.price
     *
     * @param price the value for healthy_products.price
     *
     * @mbggenerated
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.createtime
     *
     * @return the value of healthy_products.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.createtime
     *
     * @param createtime the value for healthy_products.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column healthy_products.updatetime
     *
     * @return the value of healthy_products.updatetime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column healthy_products.updatetime
     *
     * @param updatetime the value for healthy_products.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}