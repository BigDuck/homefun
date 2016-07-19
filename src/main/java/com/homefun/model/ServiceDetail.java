package com.homefun.model;

import com.homefun.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column(name = "SERVICE_ETIME")
    private Date serviceEtime;
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
    private String serviceOther;

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
    /**
     * 应收取费用should pay
     */
    @Column(name = "SERVICE_SPAY")
    private Integer serviceSpay;
    /**
     * 实际收取费用
     */
    @Column(name = "SERVICE_PAY")
    private Integer servicePay;
    /**
     * 套餐类型
     */
    @Column(name = "SERVICE_TYPE")
    private Integer serviceType;
    @Transient
    private String serviceTypeName;

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public Date getServiceEtime() {
        return serviceEtime;
    }

    public void setServiceEtime(Date serviceEtime) {
        this.serviceEtime = serviceEtime;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getServicePay() {
        return servicePay;
    }

    public void setServicePay(Integer servicePay) {
        this.servicePay = servicePay;
    }

    public Integer getServiceSpay() {
        return serviceSpay;
    }

    public void setServiceSpay(Integer serviceSpay) {
        this.serviceSpay = serviceSpay;
    }

    public List<String> getEmp_list() {
        emp_list=new ArrayList<String>();
        if(StringUtils.isNoneEmtryAndNull(this.getEmpNum())){
            String []res=this.empNum.split("-");
            for (int i = 0; i <res.length ; i++) {
                this.emp_list.add(res[i]);
                //TODO 以后直接在这边直接从redis或者其他缓存里加载数据把员工编码转换为员工名字
            }
        }

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
    public String getServiceOther() {
        return serviceOther;
    }

    /**
     * 设置其他
     *
     * @param serviceOther 其他
     */
    public void setServiceOther(String serviceOther) {
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
                ", serviceEtime=" + serviceEtime +
                ", serviceOrder='" + serviceOrder + '\'' +
                ", serviceCallback='" + serviceCallback + '\'' +
                ", serviceScore=" + serviceScore +
                ", serviceOther='" + serviceOther + '\'' +
                ", serviceCount=" + serviceCount +
                ", emp_list=" + emp_list +
                ", serviceSpay=" + serviceSpay +
                ", servicePay=" + servicePay +
                ", serviceType=" + serviceType +
                '}';
    }
}