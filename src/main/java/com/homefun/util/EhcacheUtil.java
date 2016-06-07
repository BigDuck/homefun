/*
 * ©2016 wupjhy.cn.  All rights reserved.
 */

package com.homefun.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

/**
 * Name：EhcacheUtil
 * Time：2016/5/23 9:13
 * author：WPJ587
 * description：操作缓存
 **/

public class EhcacheUtil {
    @Autowired
    static CacheManager cacheManager;
    @Autowired
    static EhCacheManagerFactoryBean ehCacheManagerFactoryBean;
    /**
     * 增加缓存
     * @param cacheName 缓存的实例吧
     * @param key 缓存的内容的key
     * @param value 缓存内容的值
     */
    public static void put(String cacheName, Object key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key,value);
        }
    }

    /**
     * 删除缓存
     * @param cacheName
     * @return
     */
    public static boolean remove(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
             cache.clear();
            return true;
        }
        return false;
    }
    public static Object getCache(String cacheName,Object key){
        Cache cache1= cacheManager.getCache(cacheName);
        Cache.ValueWrapper valueWrapper=cache1.get(key);
        return valueWrapper.get();
    }
}