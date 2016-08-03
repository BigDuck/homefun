/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.model;

import com.homefun.util.Constant;

import javax.persistence.*;

/**
 * 员工基本信息类
 */
public class Employees extends BaseEntity {
    private static final long serialVersionUID = -3643161333674075500L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 员工名字
     */
    @Column(name = "EMP_NAME")
    private String empName;

    /**
     * 工号
     */
    @Column(name = "EMP_NUM")
    private String empNum;

    /**
     * 身份证
     */
    @Column(name = "EMP_IDCARD")
    private String empIdcard;

    @Column(name = "EMP_ADDRESS")
    private String empAddress;

    /**
     * 性别(0:男，1：女)
     */
    @Column(name = "EMP_SEX")
    private Boolean empSex;

    @Column(name = "EMP_AGE")
    private Integer empAge;

    /**
     * 员工头像地址
     */
    @Column(name = "EMP_PHOTO")
    private String empPhoto;

    /**
     * 员工描述
     */
    @Column(name = "EMP_DESE")
    private String empDese;

    @Column(name = "EMP_OTHER")
    private String empOther;
    /**
     * 员工归属店铺
     */
    @Column(name = "EMP_BELONG")
    private String empBelong;
    /**
     * 归属小组
     */
    @Column(name = "EMP_GROUP")
    private String empGroup;

    /**
     * 工作（外键）
     */
    @Column(name = "EMP_JOB")
    private String empJob;
    /**
     * 员工状体
     */
    @Column(name = "EMP_STATE")
    private Integer empState;
    /**
     * 店铺名字
     */
    @Transient
    private String shopName;
    /**
     * 工作名字
     */
    @Transient
    private String jobName;
    /**
     * 员工状态名字
     */
    @Transient
    private String stateName;

    public Employees(String empName, String empNum) {
        this.empName = empName;
        this.empNum = empNum;
    }

    public Employees() {
    }

    public Employees(String empName, String empNum, String empIdcard, String empAddress, Boolean empSex, Integer empAge, String empPhoto, String empDese, String empOther, String empBelong) {
        this.empName = empName;
        this.empNum = empNum;
        this.empIdcard = empIdcard;
        this.empAddress = empAddress;
        this.empSex = empSex;
        this.empAge = empAge;
        this.empPhoto = empPhoto;
        this.empDese = empDese;
        this.empOther = empOther;
        this.empBelong = empBelong;
    }

    public Employees(int id, String empName, String empPhoto, String jobName, String shopName, String stateName) {
        this.id = id;
        this.empName = empName;
        this.empPhoto = empPhoto;
        this.jobName = jobName;
        this.shopName = shopName;
        this.stateName = stateName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getShopName() {
        this.shopName = Constant.SHOP_NAME.get(this.getEmpBelong());
        return shopName;
    }

    public void setShopName(String shopName) {

        this.shopName = shopName;
    }

    public String getStateName() {
        this.stateName = Constant.EMP_STATE.get(this.getEmpState());

        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public String getEmpGroup() {
        return empGroup;
    }

    public void setEmpGroup(String empGroup) {
        this.empGroup = empGroup;
    }

    public Integer getEmpState() {
        return empState;
    }

    public void setEmpState(Integer empState) {
        this.empState = empState;
    }

    /**
     * 获取员工名字
     *
     * @return EMP_NAME - 员工名字
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置员工名字
     *
     * @param empName 员工名字
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * 获取工号
     *
     * @return EMP_NUM - 工号
     */
    public String getEmpNum() {
        return empNum;
    }

    /**
     * 设置工号
     *
     * @param empNum 工号
     */
    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    /**
     * 获取身份证
     *
     * @return EMP_IDCARD - 身份证
     */
    public String getEmpIdcard() {
        return empIdcard;
    }

    /**
     * 设置身份证
     *
     * @param empIdcard 身份证
     */
    public void setEmpIdcard(String empIdcard) {
        this.empIdcard = empIdcard;
    }

    /**
     * @return EMP_ADDRESS
     */
    public String getEmpAddress() {
        return empAddress;
    }

    /**
     * @param empAddress
     */
    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    /**
     * 获取性别(0:男，1：女)
     *
     * @return EMP_SEX - 性别(0:男，1：女)
     */
    public Boolean getEmpSex() {
        return empSex;
    }

    /**
     * 设置性别(0:男，1：女)
     *
     * @param empSex 性别(0:男，1：女)
     */
    public void setEmpSex(Boolean empSex) {
        this.empSex = empSex;
    }

    /**
     * @return EMP_AGE
     */
    public Integer getEmpAge() {
        return empAge;
    }

    /**
     * @param empAge
     */
    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    /**
     * 获取员工头像地址
     *
     * @return EMP_PHOTO - 员工头像地址
     */
    public String getEmpPhoto() {
        return empPhoto;
    }

    /**
     * 设置员工头像地址
     *
     * @param empPhoto 员工头像地址
     */
    public void setEmpPhoto(String empPhoto) {
        this.empPhoto = empPhoto;
    }

    /**
     * 获取员工描述
     *
     * @return EMP_DESE - 员工描述
     */
    public String getEmpDese() {
        return empDese;
    }

    /**
     * 设置员工描述
     *
     * @param empDese 员工描述
     */
    public void setEmpDese(String empDese) {
        this.empDese = empDese;
    }

    /**
     * @return EMP_OTHER
     */
    public String getEmpOther() {
        return empOther;
    }

    /**
     * @param empOther
     */
    public void setEmpOther(String empOther) {
        this.empOther = empOther;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpBelong() {
        return empBelong;
    }

    public void setEmpBelong(String empBelong) {
        this.empBelong = empBelong;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", empNum='" + empNum + '\'' +
                ", empIdcard='" + empIdcard + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", empSex=" + empSex +
                ", empAge=" + empAge +
                ", empPhoto='" + empPhoto + '\'' +
                ", empDese='" + empDese + '\'' +
                ", empOther='" + empOther + '\'' +
                ", empBelong='" + empBelong + '\'' +
                ", empGroup='" + empGroup + '\'' +
                ", empJob='" + empJob + '\'' +
                ", empState=" + empState +
                ", shopName='" + shopName + '\'' +
                ", jobName='" + jobName + '\'' +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}