/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.github.pagehelper.PageInfo;
import com.homefun.model.BTRequestParams;
import com.homefun.model.Customer;
import com.homefun.model.UserType;
import com.homefun.service.CustomerService;
import com.homefun.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Name：IndexController
 * Time：2016/1/19 23:09
 * author：WPJ587
 * description：首页控制器
 **/
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserTypeService userTypeService;
    @RequestMapping(value = {"", "/index"})
    public Object index() {
        return "index";
    }

    @RequestMapping(value = "/customer")
    public Object customerList(ModelMap map) {
        List<UserType> res = userTypeService.getUserType(new UserType());
        List<UserType> results=new ArrayList<>();
        for (int i = 0; i <res.size() ; i++) {
            results.add(new UserType(res.get(i).getId(),res.get(i).getUserType()));
        }
        map.put("userTypes",results);
        return "customer/customserList";
    }

    /**
     *  根据前端发来的请求进行分页
     * @param btRequestParams 请求参数实体类
     * @return
     */
    @RequestMapping("/data")
    public
    @ResponseBody
    Object getData(
    BTRequestParams btRequestParams
    ) {
        // 暂时不考虑排序
        myLogeer.info("查询参数"+btRequestParams.toString());

        List<Customer> result = customerService.getCustomersByBTRequest(btRequestParams);
        Map<String, Object> totalAndRows = new HashMap<>();

        totalAndRows.put("total", new PageInfo<>(result).getTotal());
        totalAndRows.put("rows", result);

        return totalAndRows;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public Object toCustomer(ModelMap map) {
        map.put("userTypes",userTypeService.getUserType(new UserType()));
        return "customer/add";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public
    @ResponseBody
    Object addCustomer(Customer customer) {
        customer.setCustomerRtime(new Date());
        int res = customerService.save(customer);
        return res;
    }

    @RequestMapping(value = "/toUpdate")
    public Object toUpdateCustomer(int id, ModelMap map) {
        myLogeer.info("user ID {}",id);
        myLogeer.info("result{}",customerService.selectByKey(id));
         map.put("cus",customerService.selectByKey(id));
        map.put("userTypes",userTypeService.getUserType(new UserType()));

        return "customer/update";
    }
    @RequestMapping(value = "/updateCus",method = RequestMethod.POST)
    public
    @ResponseBody
    Object updateCustomer(Customer customer) {
        customer.setCustomerRtime(new Date());
        int res = customerService.updateAll(customer);
        myLogeer.info("更新结果{}", res);
        return res;
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody Object deleteCustomer(Customer customer){
        customer.setCustomerRtime(new Date());
        myLogeer.info("--->{}:",customer);
        customer.setCustomerIsdel(true);
        int res=customerService.updateNotNull(customer);
        return res;
    }
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        System.out.println("-----------------");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        //initBinder(request, binder);
    }
}
