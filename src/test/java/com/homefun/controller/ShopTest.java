package com.homefun.controller;

import com.homefun.dao.ShopMapper;
import com.homefun.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Name：ShopTest
 * Time：2016/7/19 14:50
 * author：WPJ587
 * description：
 **/

public class ShopTest extends BaseTest {
    @Autowired
    ShopMapper shopMapper;

    @org.junit.Test
    public void getData() {
        List<Shop> result = shopMapper.selectSimpleData();
        System.out.println(result);
    }
}
