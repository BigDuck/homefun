package com.homefun.controller;

import com.github.pagehelper.PageInfo;
import com.homefun.dao.ShopMapper;
import com.homefun.model.EmpListRequest;
import com.homefun.model.Employees;
import com.homefun.service.EmployeesService;
import com.homefun.service.ShopService;
import com.homefun.util.Constant;
import com.homefun.vo.SimpleEmployeesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name：EmployeesController
 * Time：2016/7/5 21:23
 * author：WPJ587
 * description：员工控制器
 **/
@Controller
@RequestMapping("/admin/emp")
public class EmployeesController extends BaseController{
    @Autowired
    EmployeesService employeesService;
    @Autowired
    ShopService shopService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@RequestParam(name = "type", defaultValue = "0", required = false) Integer type) {
        List<Employees> temp = employeesService.selectAll();
        List<Employees> result = new ArrayList<>();
        if (type == 1) { // 1的时候说明只是获取员工名字和员工号为了减少网络传输把不需要的属性置为null
            for (int i = 0; i < temp.size(); i++) {
                result.add(new Employees(temp.get(i).getEmpName(), temp.get(i).getEmpNum()));
            }
        } else {
            result = temp;
        }
        // 列表
        return result;
    }

    /**
     * 员工列表
     *
     * @return
     */
    @RequestMapping("/toemp")
    public Object empPage(ModelMap map) {
        // 获取门店数据传到前台
        map.put("shopList", shopService.shopDataList());
        return Constant.EMP + "/empManage";
    }

    /**
     * 获取员工列表数据
     *
     * @param empListRequest 请求封装的实体类
     * @return 员工数据
     */
    @ResponseBody
    @RequestMapping("/getData")
    public Object getEmpData(EmpListRequest empListRequest) {
        List<Employees> result = employeesService.getSomeMsgebyEmpRequest(empListRequest);
        List<SimpleEmployeesVO> res = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            res.add(new SimpleEmployeesVO(
                    result.get(i).getId(),
                    result.get(i).getEmpPhoto(),
                    result.get(i).getShopName(),
                    result.get(i).getStateName(),
                    result.get(i).getJobName(),
                    result.get(i).getEmpName()));
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("empList", res);
        resultMap.put("total", new PageInfo<>(result).getTotal());
        return resultMap;
    }

    /**
     * 员工详情
     *
     * @param map
     * @param empId 员工id
     * @return
     */
    @RequestMapping(value = "/detail/{empId}", method = RequestMethod.GET)
    public Object empList(ModelMap map, @PathVariable("empId") int empId) {
        Employees res=employeesService.selectByKey(empId);
        map.put("emp", res);
        return Constant.EMP + "/detail";
    }

    /**
     * 展示阿姨选项
     *
     * @param map
     * @return
     */
    @RequestMapping("/select")
    public Object selectList(ModelMap map) {
        return Constant.COMMON + "/employeeSelect";
    }
    @RequestMapping("/toadd")
    public Object toAddEmp(ModelMap map){

        return Constant.EMP+"/empAdd";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object addEmployees(Employees employees){
        myLogeer.info("添加的{}",employees.toString());
        int result; // 0 失败 1成功
        try {
          result= employeesService.save(employees);
        }catch (Exception e){
            //TODO 此处未进行异常友好化的处理
            e.printStackTrace();
            result= 0;
        }
        return result;
    }

}
