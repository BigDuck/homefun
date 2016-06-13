package com.homefun.model;

import com.sun.tools.javac.util.List;

import javax.persistence.*;
import java.util.Date;

@Table(name = "service_detail")
public class ServiceDetail  extends BaseEntity {
    private static final long serialVersionUID = -3148855319589284499L;
    /**
     * 服务id(uuid)
     */
    @Id
    @Column(name = "SERVICE_ID")
    @GeneratedValue(generator = "UUID")
    private String serviceId;

    /**
     * 员工工号多个员工时默认以+分割
     */
    @Column(name = "EMP_NUM")
    private String empNum;

    /**
     * 服务的时间
     */
    @Column(name = "SERVICE_TIME")
    private Date serviceTime;

    /**
     * 服务的订单号
     */
    @Column(name = "SERVICE_ORDER")
    private String serviceOrder;

    /**
     * 服务的反馈
     */
    @Column(name = "SERVICE_CALLBACK")
    private String serviceCallback;

    /**
     * 服务的分数
     */
    @Column(name = "SERVICE_SCORE")
    private Integer serviceScore;

    /**
     * 其他
     */
    @Column(name = "SERVICE_OTHER")
    private Integer serviceOther;

    /**
     * 第几次服务
     */
    @Column(name = "SERVICE_COUNT")
    private Integer serviceCount;
    /**
     * 服务的员工编号
     */
    @Transient
    private List<String> emp_list;

    public List<String> getEmp_list() {
        return emp_list;
    }

    public void setEmp_list(List<String> emp_list) {
        this.emp_list = emp_list;
    }

    /**
     * 获取服务id(uuid)
     *
     * @return SERVICE_ID - 服务id(uuid)
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置服务id(uuid)
     *
     * @param serviceId 服务id(uuid)
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 获取员工工号
     *
     * @return EMP_NUM - 员工工号
     */
    public String getEmpNum() {
        return empNum;
    }

    /**
     * 设置员工工号
     *
     * @param empNum 员工工号
     */
    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    /**
     * 获取服务的时间
     *
     * @return SERVICE_TIME - 服务的时间
     */
    public Date getServiceTime() {
        return serviceTime;
    }

    /**
     * 设置服务的时间
     *
     * @param serviceTime 服务的时间
     */
    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * 获取服务的订单号
     *
     * @return SERVICE_ORDER - 服务的订单号
     */
    public String getServiceOrder() {
        return serviceOrder;
    }

    /**
     * 设置服务的订单号
     *
     * @param serviceOrder 服务的订单号
     */
    public void setServiceOrder(String serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    /**
     * 获取服务的反馈
     *
     * @return SERVICE_CALLBACK - 服务的反馈
     */
    public String getServiceCallback() {
        return serviceCallback;
    }

    /**
     * 设置服务的反馈
     *
     * @param serviceCallback 服务的反馈
     */
    public void setServiceCallback(String serviceCallback) {
        this.serviceCallback = serviceCallback;
    }

    /**
     * 获取服务的分数
     *
     * @return SERVICE_SCORE - 服务的分数
     */
    public Integer getServiceScore() {
        return serviceScore;
    }

    /**
     * 设置服务的分数
     *
     * @param serviceScore 服务的分数
     */
    public void setServiceScore(Integer serviceScore) {
        this.serviceScore = serviceScore;
    }

    /**
     * 获取其他
     *
     * @return SERVICE_OTHER - 其他
     */
    public Integer getServiceOther() {
        return serviceOther;
    }

    /**
     * 设置其他
     *
     * @param serviceOther 其他
     */
    public void setServiceOther(Integer serviceOther) {
        this.serviceOther = serviceOther;
    }

    /**
     * 获取第几次服务
     *
     * @return SERVICE_COUNT - 第几次服务
     */
    public Integer getServiceCount() {
        return serviceCount;
    }

    /**
     * 设置第几次服务
     *
     * @param serviceCount 第几次服务
     */
    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    @Override
    public String toString() {
        return "ServiceDetail{" +
                "serviceId='" + serviceId + '\'' +
                ", empNum='" + empNum + '\'' +
                ", serviceTime=" + serviceTime +
                ", serviceOrder='" + serviceOrder + '\'' +
                ", serviceCallback='" + serviceCallback + '\'' +
                ", serviceScore=" + serviceScore +
                ", serviceOther=" + serviceOther +
                ", serviceCount=" + serviceCount +
                '}';
    }
}