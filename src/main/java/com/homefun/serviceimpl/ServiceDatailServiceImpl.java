/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.homefun.dao.ServiceDetailMapper;
import com.homefun.model.BTRequestParams;
import com.homefun.model.ServiceDetail;
import com.homefun.service.BaseService;
import com.homefun.service.ServiceDetailService;
import com.homefun.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(!StringUtils.isEmpty(serviceDetail.getEmp_list())&&serviceDetail.getEmp_list().size()>0){
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

    /**
     * 列表展示服务记录
     *
     * @param btRequestParams
     * @return
     */
    @Override
    public List<ServiceDetail> getDetailListByBTRquest(BTRequestParams btRequestParams) {
        if (btRequestParams == null) {
            return null;
        }
        Date startTime;
        Date endTime;
        // 默认查询一个月内的数据
        if(btRequestParams.getStartTime()==null){
            startTime = DateUtil.getYesterday(new Date(), true, 31);
        }else {
            startTime=btRequestParams.getStartTime();
        }
        if(btRequestParams.getEndTime()==null){
            endTime=new Date();
        }else {
            endTime=btRequestParams.getEndTime();
        }
        Map<String,Object> params=new HashMap<>();
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        if (btRequestParams.isPaging()) {
            int page = btRequestParams.getOffset() / btRequestParams.getLimit() + 1;
            PageHelper.startPage(page, btRequestParams.getLimit(), true);
        }
        //
        return serviceDetailMapper.selectDetailByEntity(params);
    }

}
