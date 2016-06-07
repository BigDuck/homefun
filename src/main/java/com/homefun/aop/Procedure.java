/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.aop;

import java.lang.annotation.*;

/**
 * Procedure declaration of REST API.
 * @author horiga
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Procedure {
	String description() default "";
}
