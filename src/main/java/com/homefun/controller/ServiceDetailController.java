package com.homefun.controller;

import com.homefun.model.ServiceDetail;
import com.homefun.service.ServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Name：ServiceDetailController
 * Time：2016/6/7 11:46
 * author：WPJ587
 * description：服务详情
 **/
@Controller
@RequestMapping("/admin")
public class ServiceDetailController extends BaseController {
    @Autowired
    private ServiceDetailService serviceDetailService;
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Object searchCustom(@RequestParam(name = "id") int id, ModelMap map) {
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setServiceOrder(String.valueOf(id));
        List<ServiceDetail> listTmp=serviceDetailService.findDetailByServiceDetail(serviceDetail);
        map.put("details",listTmp);
        map.put("SERVICE_ORDER",id);
        return "customer/serviceDetail";
    }
}
