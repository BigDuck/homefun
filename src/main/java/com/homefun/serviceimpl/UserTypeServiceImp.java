/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.serviceimpl;

import com.homefun.dao.UserTypeMapper;
import com.homefun.model.UserType;
import com.homefun.service.BaseService;
import com.homefun.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Name：UserTypeServiceImp
 * Time：2016/5/18 15:42
 * author：WPJ587
 * description：用户类型
 **/
@Service
public class UserTypeServiceImp extends BaseService<UserType> implements UserTypeService {
    @Autowired
    private UserTypeMapper userTypeMapper;

    /**
     * 获取客户购买的卡的类型
     * 缓存目前全部缓存，不考虑查询条件
     * @param userType
     * @return
     */
    @Cacheable(value = "myCache",key ="#root.targetClass")
    @Override
    public List<UserType> getUserType(UserType userType) {
        logger.info("查询条件{}",userTypeMapper);
        return userTypeMapper.select(userType);
    }
}
