package com.zhou.test.model;

import java.util.List;
import java.util.Map;

public class JsonDomain {
    /**
     * state 状态说明
     * 00 调用成功
     * 01 请求方没有权限
     * 02  参数“信息查询条件”为空
     * 03  参数“信息查询条件”错误
     * 04 参数“JSON字符串格式”错误
     * 05 参数的某些字段类型有误
     * 06 服务方异常
     * 07 其他异常
     **/
    public String state;//状态码

    public int totalnum;//总数
    public List<Map<String,Object>> datalist;//数据集

    public List<Map<String,Object>> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Map<String,Object>> datalist) {
        this.datalist = datalist;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }

    public JsonDomain(){

    }

    public JsonDomain(String state){
        this.state = state;
    }
}
