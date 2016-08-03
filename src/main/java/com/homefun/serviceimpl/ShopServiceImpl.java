package com.homefun.serviceimpl;

import com.homefun.dao.ShopMapper;
import com.homefun.model.Shop;
import com.homefun.service.BaseService;
import com.homefun.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Name：ShopServiceImpl
 * Time：2016/7/19 14:43
 * author：WPJ587
 * description：商店
 **/
@Service
public class ShopServiceImpl extends BaseService<Shop> implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    /**
     * 获取简单的门店信息
     *
     * @return
     */
    @Override
    public List<Shop> shopDataList() {
        //TODO 加入redis后可以直接从缓存取值，取不到值的时候再去数据库进行查询
        return shopMapper.selectSimpleData();
    }
}
