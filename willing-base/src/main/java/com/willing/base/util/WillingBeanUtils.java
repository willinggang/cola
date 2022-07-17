package com.willing.base.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.objenesis.ObjenesisStd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: WillingBeanUtils
 * @description: TODO 类描述
 * @author: Szg
 * @date: 2021/1/20
 **/
public class WillingBeanUtils {

    private static ThreadLocal<ObjenesisStd> objenesisStdThreadLocal = ThreadLocal.withInitial(ObjenesisStd::new);
    /**
     * 默认缓存
     */
    private static ConcurrentHashMap<Class<?>, ConcurrentHashMap<Class<?>, BeanCopier>> cache = new ConcurrentHashMap<>();

    /**
     * 默认对象拷贝
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> target) {
        return copy(source, objenesisStdThreadLocal.get().newInstance(target));
    }

    /**
     * 默认对象拷贝
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, T target) {
        BeanCopier beanCopier = getCacheBeanCopier(source.getClass(), target.getClass());
        beanCopier.copy(source, target, null);
        return target;
    }

    /**
     * 默认拷贝对象列表
     *
     * @param sources
     * @param target
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> sources, Class<T> target) {
        if (sources.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<T> list = new ArrayList<>(sources.size());
        ObjenesisStd objenesisStd = objenesisStdThreadLocal.get();
        for (Object source : sources) {
            if (source == null) {
                throw new RuntimeException("cglib转化对象错误");
            }
            T newInstance = objenesisStd.newInstance(target);
            BeanCopier beanCopier = getCacheBeanCopier(source.getClass(), target);
            beanCopier.copy(source, newInstance, null);
            list.add(newInstance);
        }
        return list;
    }




    public static <T> T mapToBean(Map<?, ?> source, Class<T> target) {
        T bean = objenesisStdThreadLocal.get().newInstance(target);
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(source);
        return bean;
    }

    public static <T> Map<?, ?> beanToMap(T source) {
        return BeanMap.create(source);
    }

    /**
     * 获取默认转换器
     *
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    private static <S, T> BeanCopier getCacheBeanCopier(Class<S> source, Class<T> target) {
        ConcurrentHashMap<Class<?>, BeanCopier> copierConcurrentHashMap = cache.computeIfAbsent(source, aClass -> new ConcurrentHashMap<>(16));
        return copierConcurrentHashMap.computeIfAbsent(target, aClass -> BeanCopier.create(source, target, false));
    }

}
