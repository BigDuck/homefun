/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.model;

/**
 * Name：BaiduMap
 * Time：2016/5/28 15:14
 * author：WPJ587
 * description：百度地图填充数据
 **/

public class BaiduMap extends BaseEntity {
    private static final long serialVersionUID = 8581888837772318156L;
    /**
     * x坐标
     */
    private double x;
    /**
     * y 坐标
     */
    private double y;
    /**
     *地图显示的信息
     */
    private String label;
    /**
     * 标注信息
     */
    private  String marker;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * 把Customer转化为百度地图实体类
     * @param customer
     */
    public BaiduMap toBaiduMap(Customer customer) {
        BaiduMap baiduMap=new BaiduMap();
        if(customer.getCustomerGps()!=null&&customer.getCustomerGps()!=""){
            String[] gps=customer.getCustomerGps().split("-");
            try {
                baiduMap.setX(Double.valueOf(gps[0]));
                baiduMap.setY(Double.valueOf(gps[1]));
            }catch (Exception e){
                baiduMap.setX(0);
                baiduMap.setY(0);
            }
        }
        baiduMap.setLabel(customer.getCustomerAddress());
        baiduMap.setMarker(customer.getCustomerPhone()+"-"+customer.getUserTypeName());
        return baiduMap;
    }

    public BaiduMap(double x, double y, String label, String marker) {
        this.x = x;
        this.y = y;
        this.label = label;
        this.marker = marker;
    }

    public BaiduMap() {
    }
}
