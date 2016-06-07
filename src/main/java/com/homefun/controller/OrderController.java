/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.github.abel533.echarts.json.GsonUtil;
import com.homefun.model.BTRequestParams;
import com.homefun.model.BaiduMap;
import com.homefun.model.Customer;
import com.homefun.model.UserType;
import com.homefun.service.CustomerService;
import com.homefun.service.UserTypeService;
import com.homefun.util.DateUtil;
import com.homefun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Name：OrderController
 * Time：2016/5/28 11:54
 * author：WPJ587
 * description：订单概况 百度地图分布
 **/
@RequestMapping("/order")
@Controller
public class OrderController extends BaseController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserTypeService userTypeService;

    /**
     * 百度地图展示订单分布情况
     *
     * @return
     */
    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public String toBadiduMapOrder(Map map) {
        map.put("userTypes", userTypeService.getUserType(new UserType()));
        return "customer/surveyMap";
    }

    /**
     * 分类在百度地图展示分布
     *
     * @param btRequestParams
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public
    @ResponseBody
    Object getData(
            BTRequestParams btRequestParams) {
        if (btRequestParams.getParams() != null) {
            btRequestParams.setSearchCondition(3); //按照地址
        }
        if (btRequestParams.getStartTime() == null) {
            // 没有指定时间默认显示6个月的数据
            btRequestParams.setStartTime(DateUtil.getYesterday(new Date(), true, 180));
            btRequestParams.setEndTime(new Date());
        }
        btRequestParams.setPaging(false);
        // 百度地图填充参数
        List<BaiduMap> baidu = new ArrayList<>();
        List<Customer> customers = customerService.getCustomersByBTRequest(btRequestParams); //获取结果集
        for (Customer c : customers) {
            baidu.add(new BaiduMap().toBaiduMap(c));
        }
        return baidu;
    }

    /**
     *跳转到图标统计
     */
    @RequestMapping(value = "/toChart", method = RequestMethod.GET)
    public Object toEchart() {
        return "customer/echartShow";
    }

    /**
     * 根据前台发来的参数返回echarts图标的option
     * @param request
     * @return 符合echarts 的json字符
     */
    @RequestMapping(value = "/chartData")
    public @ResponseBody Object  getEchartsData(
            HttpServletRequest request){
        int time_code=0;
        Date startTime = null;
        Date endTime= null;
        if(StringUtils.isNoneEmtryAndNull(request.getParameter("time_code"))){
            time_code=Integer.parseInt(request.getParameter("time_code"));
        }
        if(StringUtils.isNoneEmtryAndNull(request.getParameter("startTime"))){
            String temp=request.getParameter("startTime").substring(0,request.getParameter("startTime").length()-1).replace("/","-");
            System.out.println(temp);
            startTime=DateUtil.str2Date(temp,DateUtil.YMD);
        }
        if(StringUtils.isNoneEmtryAndNull(request.getParameter("endTime"))){
            String temp=request.getParameter("endTime").substring(0,request.getParameter("endTime").length()-1).replace("/","-");
            System.out.println(temp);
            endTime=DateUtil.str2Date(temp,DateUtil.YMD);
        }
        List<HashMap<String, Object>> res=customerService.findBuyTimeAndCount(time_code,startTime,endTime);
        if (res==null){
            return null;
        }
        String subText;
        if (startTime == null||endTime==null) { // 如果开始时间等于空那么默认往前推31
            subText=DateUtil.date2Str(DateUtil.getYesterday(new Date(), true, 31),DateUtil.YMD)+"至"+DateUtil.date2Str(new Date(),DateUtil.YMD);
        }else {
          subText=DateUtil.date2Str(startTime,DateUtil.YMD)+"至"+DateUtil.date2Str(endTime,DateUtil.YMD);
        }
        return GsonUtil.prettyFormat(customerService.hashMapData2Echarts(res,"订单统计",subText));
    }
    @RequestMapping(value = "/chartData/detail")
    public @ResponseBody Object getEchartDetail(BTRequestParams btRequestParams){
        btRequestParams.setPaging(false);
        List<Customer> customers=customerService.getCustomersByBTRequest(btRequestParams);
        String temp=customerService.customers2EchartsPie(customers,"订单类型",btRequestParams.getStartTime()+"").toString();
        return temp.replace("\n","");
    }

}
