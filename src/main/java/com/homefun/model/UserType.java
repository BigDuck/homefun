/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_type")
public class UserType extends BaseEntity {
    private static final long serialVersionUID = -4929058447729152356L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 套餐名字
     */

    @Column(name = "USER_TYPE")
    private String userType;
    /**
     * 套餐描述
     */
    @Column(name = "USER_DESE")
    private String userDese;
    /**
     * 套餐登记日期
     */
    @Column(name = "USER_DATE")
    private Date userDate;
    /**
     * 服务次数
     */
    @Column(name = "USER_TIMES")
    private Integer userTimes;
    /**
     * 套餐售价
     */
    @Column(name = "USER_SALE")
    private Integer userSale;

    public UserType(Integer id,String userType) {
        this.id=id;
        this.userType = userType;
    }

    public UserType() {
    }

    public Integer getUserTimes() {
        return userTimes;
    }

    public void setUserTimes(Integer userTimes) {
        this.userTimes = userTimes;
    }

    public Integer getUserSale() {
        return userSale;
    }

    public void setUserSale(Integer userSale) {
        this.userSale = userSale;
    }

    public void setUserDate(Date userDate) {
        this.userDate =userDate;
    }

    public Date getUserDate() {
        return userDate;
    }

    /**
     * @return USER_TYPE
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return USER_DESE
     */
    public String getUserDese() {
        return userDese;
    }

    /**
     * @param userDese
     */
    public void setUserDese(String userDese) {
        this.userDese = userDese;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                ", userDese='" + userDese + '\'' +
                ", userDate=" + userDate +
                ", userTimes=" + userTimes +
                ", userSale=" + userSale +
                '}';
    }
}