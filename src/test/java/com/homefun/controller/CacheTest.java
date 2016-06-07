/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.homefun.model.UserType;
import com.homefun.service.UserTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;

/**
 * Name：CacheTest
 * Time：2016/5/22 21:51
 * author：WPJ587
 * description：缓存
 **/

public class CacheTest extends BaseTest {
    @Autowired
    CacheManager cacheManager;
    @Autowired
    UserTypeService userTypeService;
    @Test
    public void cacheT(){
        Cache cache = cacheManager.getCache("myCache");
        if (cache != null) {
            cache.put("userType",userTypeService.selectAll());
        }
        System.out.println("------------------");
         Cache cache1= cacheManager.getCache("myCache");
        Cache.ValueWrapper valueWrapper=cache1.get("userType");
        List<UserType> userTypes= (List<UserType>) valueWrapper.get();
        for (int i = 0; i <userTypes.size() ; i++) {
            System.out.println(userTypes.get(i).getUserType());
        }
    }
}
