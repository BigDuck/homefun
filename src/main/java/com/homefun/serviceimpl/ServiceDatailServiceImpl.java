/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.serviceimpl;

import com.homefun.dao.ServiceDetailMapper;
import com.homefun.model.ServiceDetail;
import com.homefun.service.BaseService;
import com.homefun.service.ServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Name：ServiceDatailService
 * Time：2016/6/6 16:19
 * author：WPJ587
 * description：订单的服务详情
 **/
@Service
public class ServiceDatailServiceImpl extends BaseService<ServiceDetail> implements ServiceDetailService {
    @Autowired
    private ServiceDetailMapper serviceDetailMapper;
    /**
     * 根据服务详情获取服务详情列表
     * @param serviceDetail 服务详情
     * @return 服务详情列表
     */
    @Override
    public List<ServiceDetail> findDetailByServiceDetail(ServiceDetail serviceDetail) {

        Example example = new Example(ServiceDetail.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(serviceDetail.getEmp_list())){
            String listStr=serviceDetail.getEmp_list().toString();
            String empNums=listStr.substring(1,listStr.toString().length()-1).replace(",","+").trim();
            criteria.andEqualTo("empNum",empNums);
        }

        if(!StringUtils.isEmpty(serviceDetail.getServiceScore())){
            criteria.andGreaterThanOrEqualTo("serviceScore",serviceDetail.getServiceScore());
        }
        if(!StringUtils.isEmpty(serviceDetail.getServiceOrder())){
            criteria.andEqualTo("serviceOrder",serviceDetail.getServiceOrder());
        }
        return serviceDetailMapper.selectByExample(example);
    }
}
