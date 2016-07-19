package com.homefun.vo;

import com.homefun.model.BaseEntity;

/**
 * Name：SimpleEmployeesVO
 * Time：2016/7/16 10:02
 * author：WPJ587
 * description：简单员工信息VO类
 **/

public class SimpleEmployeesVO extends BaseEntity{
    private static final long serialVersionUID = 6566958436779211428L;
    /**
     * 员工ID
     */
    private Integer empId;
    /**
     * 图片地址
     */
    private String empPhotoUrl;
    /**
     * 所属员工点名
     */
    private String empShopName;
    /**
     * 员工状态 在职/离职
     */
    private String empState;
    /**
     * 工作置为
     */
    private String empJobName;
    /**
     * 员工名
     */
    private String empName;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpPhotoUrl() {
        return empPhotoUrl;
    }

    public void setEmpPhotoUrl(String empPhotoUrl) {
        this.empPhotoUrl = empPhotoUrl;
    }

    public String getEmpShopName() {
        return empShopName;
    }

    public void setEmpShopName(String empShopName) {
        this.empShopName = empShopName;
    }

    public String getEmpState() {
        return empState;
    }

    public void setEmpState(String empState) {
        this.empState = empState;
    }

    public String getEmpJobName() {
        return empJobName;
    }

    public void setEmpJobName(String empJobName) {
        this.empJobName = empJobName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public SimpleEmployeesVO(Integer empId, String empPhotoUrl, String empShopName, String empState, String empJobName, String empName) {
        this.empId = empId;
        this.empPhotoUrl = empPhotoUrl;
        this.empShopName = empShopName;
        this.empState = empState;
        this.empJobName = empJobName;
        this.empName = empName;
    }
}
