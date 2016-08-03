package com.homefun.service;

import com.homefun.model.Shop;

import java.util.List;

/**
 * Created by WPJ587 on 2016/7/19.
 */
public interface ShopService extends IService<Shop> {
    /**
     * 获取简单的门店信息
     * @return
     */
    List<Shop> shopDataList();
}
