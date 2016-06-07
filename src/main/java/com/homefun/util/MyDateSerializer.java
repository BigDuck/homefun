/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于弥补json输出返回的时候无法正确格式化时间问题
 *
 */
public class MyDateSerializer extends JsonSerializer<Date> {
  @Override
  public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    jgen.writeString(formatter.format(value));
  }
}