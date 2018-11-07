package com.zhou.medical.common.entity.health;

import java.io.Serializable;
import java.util.Date;

public class ScienceArticle implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.subheading
     *
     * @mbggenerated
     */
    private String subheading;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.source
     *
     * @mbggenerated
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.article_url
     *
     * @mbggenerated
     */
    private String articleUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.image_url
     *
     * @mbggenerated
     */
    private String imageUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.column_id
     *
     * @mbggenerated
     */
    private Integer columnId;
    
    /**
     * 
     */
    private Integer flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column science_article.updatetime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.id
     *
     * @return the value of science_article.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.id
     *
     * @param id the value for science_article.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.title
     *
     * @return the value of science_article.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.title
     *
     * @param title the value for science_article.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.subheading
     *
     * @return the value of science_article.subheading
     *
     * @mbggenerated
     */
    public String getSubheading() {
        return subheading;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.subheading
     *
     * @param subheading the value for science_article.subheading
     *
     * @mbggenerated
     */
    public void setSubheading(String subheading) {
        this.subheading = subheading == null ? null : subheading.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.source
     *
     * @return the value of science_article.source
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.source
     *
     * @param source the value for science_article.source
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.article_url
     *
     * @return the value of science_article.article_url
     *
     * @mbggenerated
     */
    public String getArticleUrl() {
        return articleUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.article_url
     *
     * @param articleUrl the value for science_article.article_url
     *
     * @mbggenerated
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.image_url
     *
     * @return the value of science_article.image_url
     *
     * @mbggenerated
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.image_url
     *
     * @param imageUrl the value for science_article.image_url
     *
     * @mbggenerated
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.column_id
     *
     * @return the value of science_article.column_id
     *
     * @mbggenerated
     */
    public Integer getColumnId() {
        return columnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.column_id
     *
     * @param columnId the value for science_article.column_id
     *
     * @mbggenerated
     */
    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
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
     * This method returns the value of the database column science_article.createtime
     *
     * @return the value of science_article.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.createtime
     *
     * @param createtime the value for science_article.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column science_article.updatetime
     *
     * @return the value of science_article.updatetime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column science_article.updatetime
     *
     * @param updatetime the value for science_article.updatetime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}