package com.homefun.controller;

import com.github.pagehelper.PageInfo;
import com.homefun.model.BTRequestParams;
import com.homefun.model.Customer;
import com.homefun.model.ServiceDetail;
import com.homefun.service.ServiceDetailService;
import com.homefun.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Name：ServiceDetailController
 * Time：2016/6/7 11:46
 * author：WPJ587
 * description：服务详情
 **/
@Controller
@RequestMapping("/admin/detail")
public class ServiceDetailController extends BaseController {
    @Autowired
    private ServiceDetailService serviceDetailService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object searchCustom(@RequestParam(name = "id") int id, ModelMap map) {
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setServiceOrder(String.valueOf(id));
        List<ServiceDetail> listTmp = serviceDetailService.findDetailByServiceDetail(serviceDetail);
        map.put("details", listTmp);
        map.put("SERVICE_ORDER", id);
        return "customer/serviceDetail";
    }

    /**
     * 展示订单服务记录
     *
     * @return
     */
    @RequestMapping(value = "/toList")
    public Object toDetaiList() {
        myLogeer.info("访问：toList");
        return Constant.CUSTOMER + "/detailList";
    }

    /**
     * 根据请求条件获取填充bootstrap-table的数据
     *
     * @param btRequestParams
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getDetailList(BTRequestParams btRequestParams) {
        List<ServiceDetail> detailList = serviceDetailService.getDetailListByBTRquest(btRequestParams);
        Map<String, Object> totalAndRows = new HashMap<>();
        totalAndRows.put("total", new PageInfo<>(detailList).getTotal());
        totalAndRows.put("rows", detailList);
        return totalAndRows;
    }

    /**
     * 跳转到更新的页面
     *
     * @return
     */
    @RequestMapping(value = "/pre")
    public Object toAddDetail() {
        return Constant.CUSTOMER + "/addDetail";
    }

    /**
     * 增加服务记录
     *
     * @param serviceDetail 服务记录实体
     * @return 1 成功 0 获取其他代表失败
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addDetail(ServiceDetail serviceDetail) {
        myLogeer.debug("新增的实体是{}", serviceDetail);
        Object res = serviceDetailService.save(serviceDetail);

        return res;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public Object getById(@RequestParam("serviceId") String serviceId,
                          @RequestParam("type") String type,
                          ModelMap map) {
        map.put("res", serviceDetailService.selectByKey(serviceId));
        map.put("type", type);

        return Constant.CUSTOMER + "/detailCon";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateData(ServiceDetail serviceDetail) {
        myLogeer.info("待更新实体{}", serviceDetail);
        return serviceDetailService.updateNotNull(serviceDetail);
    }

    /**
     * 根据id进行订单记录的删除
     *
     * @param serviceId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@RequestParam("serviceId") String serviceId) {
        myLogeer.info("删除数据{}", serviceId);
        return serviceDetailService.delete(serviceId);
    }
}
