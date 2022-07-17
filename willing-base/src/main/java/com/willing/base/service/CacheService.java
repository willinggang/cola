package com.willing.base.service;

import java.util.concurrent.TimeUnit;

/**
 * @author Szg
 * @ClassName: CacheService
 * @Description: 缓存服务
 * @date 2022/7/1710:41
 */
public interface CacheService {


    /**
     * 保存商品信息
     *
     * @param <T>
     * @param expireTimeOut  失效时长
     * @param expireTimeUnit 失效时长单位
     * @param key            缓存key
     * @param data           缓存数据
     */
    <T> void save(Integer expireTimeOut, TimeUnit expireTimeUnit, String key, T data);

    /**
     * 读取缓存
     *
     * @param <T>
     * @param key 缓存key
     * @return
     */
    <T> T read(String key);

    /**
     * 使用Kryo进行序列化
     *
     * @param <T>
     * @param expireTimeOut  失效时长
     * @param expireTimeUnit 失效时长单位
     * @param key            缓存key
     * @param data           缓存数据
     */
    <T> void saveKyro(Integer expireTimeOut, TimeUnit expireTimeUnit, String key, T data);

    /**
     * 读取Kryo序列化缓存数据
     *
     * @param <T>
     * @param key 缓存key
     * @return
     */
    <T> T readKryo(String key);
}
