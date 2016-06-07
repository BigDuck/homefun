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
        return "UserType{" +"id="+id+
                "userType='" + userType + '\'' +
                ", userDese='" + userDese + '\'' +
                ", userDate=" + userDate +
                '}';
    }
}