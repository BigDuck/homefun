package com.homefun.dao;

import com.homefun.common.MyMapper;
import com.homefun.model.ServiceDetail;

import java.util.List;
import java.util.Map;

public interface ServiceDetailMapper extends MyMapper<ServiceDetail> {
    /**
     * 根据
     * @param params   key startTime
     *                 key endTime
     * @return
     */
    List<ServiceDetail> selectDetailByEntity(Map<String, Object> params);
}