/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.service;

import com.homefun.model.UserType;

import java.util.List;

/**
 * Created by WPJ587 on 2016/5/18.
 */
public interface UserTypeService extends IService<UserType> {
    /**
     * 获取客户购买的卡的类型
     * @param userType
     * @return
     */
    List<UserType> getUserType(UserType userType);
}
