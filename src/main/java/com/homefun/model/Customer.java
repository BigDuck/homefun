/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homefun.util.EhcacheUtil;

import java.util.Date;
import javax.persistence.*;

public class Customer extends BaseEntity {
    private static final long serialVersionUID = -5132447537538782408L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 合同或者客户号
     */
    @Column(name = "CONTRACT_NUM")
    private Integer contractNum;

    /**
     * 客户名字
     */
    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    /**
     * 客户联系方式
     */
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;

    /**
     * 客户地址
     */
    @Column(name = "CUSTOMER_ADDRESS")
    private String customerAddress;

    /**
     * 客户地址经纬度
     */
    @Column(name = "CUSTOMER_GPS")
    private String customerGps;

    /**
     * 客户附近公交站
     */
    @Column(name = "CUSTOMER_BUS")
    private String customerBus;

    /**
     * 客户固定服务时间
     */
    @Column(name = "CUSTOMER_STIME")
    private String customerStime;

    /**
     * 客户家户型
     */
    @Column(name = "CUSTOMER_HOME")
    private String customerHome;

    /**
     * 用户类型：年卡，月卡，体验卡
     */
    @Column(name = "CUSTOMER_TYPE")
    @JsonIgnore
    private Integer customerType;

    /**
     * 住宅性质
     */
    @Column(name = "CUSTOMER_HOME_KIND")
    private String customerHomeKind;

    /**
     * 用户购卡时间
     */
    @Column(name = "CUSTOMER_BUYTIME")
    private Date customerBuytime;

    /**
     * 登记时间
     */
    @Column(name = "CUSTOMER_RTIME")
    private Date customerRtime;
    /**
     * 是否是删除订单
     * 0 未删除
     * 1 删除
     */
    @Column(name ="CUSTOMER_ISDEL")
    @JsonIgnore
    private Boolean customerIsdel=false;

    /**
     * 客户类型，即购买的套餐类型
     *
     */
    @Transient
    private String userTypeName;
    /**
     * 获取合同或者客户号
     *
     * @return CONTRACT_NUM - 合同或者客户号
     */
    public Integer getContractNum() {
        return contractNum;
    }

    /**
     * 设置合同或者客户号
     *
     * @param contractNum 合同或者客户号
     */
    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    /**
     * 获取客户名字
     *
     * @return CUSTOMER_NAME - 客户名字
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户名字
     *
     * @param customerName 客户名字
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获取客户联系方式
     *
     * @return CUSTOMER_PHONE - 客户联系方式
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * 设置客户联系方式
     *
     * @param customerPhone 客户联系方式
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * 获取客户地址
     *
     * @return CUSTOMER_ADDRESS - 客户地址
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * 设置客户地址
     *
     * @param customerAddress 客户地址
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * 获取客户地址经纬度
     *
     * @return CUSTOMER_GPS - 客户地址经纬度
     */
    public String getCustomerGps() {
        return customerGps;
    }

    /**
     * 设置客户地址经纬度
     *
     * @param customerGps 客户地址经纬度
     */
    public void setCustomerGps(String customerGps) {
        this.customerGps = customerGps;
    }

    /**
     * 获取客户附近公交站
     *
     * @return CUSTOMER_BUS - 客户附近公交站
     */
    public String getCustomerBus() {
        return customerBus;
    }

    /**
     * 设置客户附近公交站
     *
     * @param customerBus 客户附近公交站
     */
    public void setCustomerBus(String customerBus) {
        this.customerBus = customerBus;
    }

    /**
     * 获取客户固定服务时间
     *
     * @return CUSTOMER_STIME - 客户固定服务时间
     */
    public String getCustomerStime() {
        return customerStime;
    }

    /**
     * 设置客户固定服务时间
     *
     * @param customerStime 客户固定服务时间
     */
    public void setCustomerStime(String customerStime) {
        this.customerStime = customerStime;
    }

    /**
     * 获取客户家户型
     *
     * @return CUSTOMER_HOME - 客户家户型
     */
    public String getCustomerHome() {
        return customerHome;
    }

    /**
     * 设置客户家户型
     *
     * @param customerHome 客户家户型
     */
    public void setCustomerHome(String customerHome) {
        this.customerHome = customerHome;
    }

    /**
     * 获取用户类型：年卡，月卡，体验卡
     *
     * @return CUSTOMER_TYPE - 用户类型：年卡，月卡，体验卡
     */
    public Integer getCustomerType() {
        return customerType;
    }

    /**
     * 设置用户类型：年卡，月卡，体验卡
     *
     * @param customerType 用户类型：年卡，月卡，体验卡
     */
    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    /**
     * 获取住宅性质
     *
     * @return CUSTOMER_HOME_KIND - 住宅性质
     */
    public String getCustomerHomeKind() {
        return customerHomeKind;
    }

    /**
     * 设置住宅性质
     *
     * @param customerHomeKind 住宅性质
     */
    public void setCustomerHomeKind(String customerHomeKind) {
        this.customerHomeKind = customerHomeKind;
    }

    /**
     * 获取用户购卡时间
     *
     * @return CUSTOMER_BUYTIME - 用户购卡时间
     */
    public Date getCustomerBuytime() {
        return customerBuytime;
    }

    /**
     * 设置用户购卡时间
     *
     * @param customerBuytime 用户购卡时间
     */
    public void setCustomerBuytime(Date customerBuytime) {
        this.customerBuytime = customerBuytime;
    }

    /**
     * 获取登记时间
     *
     * @return CUSTOMER_RTIME - 登记时间
     */
    public Date getCustomerRtime() {
        return customerRtime;
    }

    /**
     * 设置登记时间
     *
     * @param customerRtime 登记时间
     */
    public void setCustomerRtime(Date customerRtime) {
        this.customerRtime = customerRtime;
    }

    public void setCustomerIsdel(Boolean customerIsdel) {
        this.customerIsdel = customerIsdel;
    }

    public Boolean getCustomerIsdel() {
        return customerIsdel;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "contractNum=" + contractNum +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerGps='" + customerGps + '\'' +
                ", customerBus='" + customerBus + '\'' +
                ", customerStime='" + customerStime + '\'' +
                ", customerHome='" + customerHome + '\'' +
                ", customerType=" + customerType +
                ", customerHomeKind='" + customerHomeKind + '\'' +
                ", customerBuytime=" + customerBuytime +
                ", customerRtime=" + customerRtime +
                '}';
    }
}