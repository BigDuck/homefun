/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.controller;

import com.homefun.model.UserType;
import com.homefun.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Name：UserTypeController
 * Time：2016/5/21 11:09
 * author：WPJ587
 * description：用户类型控制器
 **/
@Controller
@RequestMapping("/type")
public class UserTypeController extends BaseController {
    @Autowired
    private UserTypeService userTypeService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public Object toTypeManager(ModelMap map) {
        List<UserType> res = userTypeService.getUserType(new UserType());
        map.put("types", res);
        myLogeer.info("res {}", res);

        return "package/packageMa";
    }

    /**
     * 更新用户类型或者说套餐
     *
     * @param userType
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Object update(UserType userType) {
        userType.setUserDate(new Date());
        myLogeer.info("待更新实体:{}", userType);
        return userTypeService.updateAll(userType);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Object deleteType(int id) {
        myLogeer.info("删除的id{}", id);
        return userTypeService.delete(id);
    }

    @RequestMapping(value = "/addUserType", method = RequestMethod.POST)
    public
    @ResponseBody
    Object addType(UserType userType) {
        userType.setUserDate(new Date());
        int id = 0;
        if (userType != null) {
            int res = userTypeService.save(userType);
            if (res == 1) {
                myLogeer.info("qian{}",userType.getId());
                id=userType.getId();
            }

        }
        return id;
    }
}
