/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.service;

import com.homefun.model.ServiceDetail;

import java.util.List;

/**
 * Created by WPJ587 on 2016/6/6.
 */
public interface ServiceDetailService extends IService<ServiceDetail> {
    /**
     * 根据服务详情获取服务详情列表
     * @param serviceDetail 服务详情
     * @return 服务详情列表
     */
    List<ServiceDetail> findDetailByServiceDetail(ServiceDetail serviceDetail);
}
