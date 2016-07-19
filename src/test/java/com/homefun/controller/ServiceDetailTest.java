/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.homefun.model.BTRequestParams;
import com.homefun.model.ServiceDetail;
import com.homefun.service.ServiceDetailService;
import com.homefun.util.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Name：ServiceDetailTest
 * Time：2016/6/6 16:23
 * author：WPJ587
 * description：
 **/

public class ServiceDetailTest extends BaseTest{
    @Autowired
    ServiceDetailService serviceDetailService;
    @Test
    public void test(){
        ServiceDetail serviceDetail=new ServiceDetail();
        serviceDetail.setEmpNum("123233");
        serviceDetail.setServiceCallback("hello");
        serviceDetail.setServiceCount(1);
        serviceDetail.setServiceOrder("2312");
        serviceDetail.setServiceScore(60);
        serviceDetail.setServiceTime(new Date());
       int i= serviceDetailService.save(serviceDetail);
        System.out.println(i);
        System.out.println(serviceDetail.getServiceId());
        System.out.println(serviceDetail.toString());
    }
    @Test
    public void getList(){
        BTRequestParams btRequestParams=    new BTRequestParams();
        btRequestParams.setStartTime(DateUtil.str2Date("2016-05-01","yyyy-MM-dd"));
        btRequestParams.setEndTime(DateUtil.str2Date("2016-05-04","yyyy-MM-dd"));
        List<ServiceDetail> result=serviceDetailService.getDetailListByBTRquest(btRequestParams);
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
        System.out.println(result);
    }
    @Test
    public void g(){
        ServiceDetail serviceDetail=new ServiceDetail();
        serviceDetail.setServiceOrder("15090001");
     List<ServiceDetail>  re  =serviceDetailService.findDetailByServiceDetail(serviceDetail);
        System.out.println(re);
    }

}
