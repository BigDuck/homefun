/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.model;

import javax.persistence.*;

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
    private Integer empOther;
    /**
     * 员工归属店铺
     */
    @Column(name = "EMP_BELONG")
    private String empBelong;




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
    public Integer getEmpOther() {
        return empOther;
    }

    /**
     * @param empOther
     */
    public void setEmpOther(Integer empOther) {
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
}