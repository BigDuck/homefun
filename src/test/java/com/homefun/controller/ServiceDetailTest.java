/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.homefun.model.ServiceDetail;
import com.homefun.service.ServiceDetailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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

}
