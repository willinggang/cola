package com.willing.base.util;


import com.willing.base.conv.BaseConvert;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @className: WillingBeanUtils
 * @description: cglib-属性拷贝
 * @author: Szg
 * @date: 2021/8/19
 **/
public class WillingBeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(WillingBeanUtils.class);
    private static ThreadLocal<ObjenesisStd> objenesisStdThreadLocal = ThreadLocal.withInitial(ObjenesisStd::new);
    /**
     * 默认缓存
     */
    private static ConcurrentHashMap<Class<?>, ConcurrentHashMap<Class<?>, BeanCopier>> cache = new ConcurrentHashMap<>();

    public static <T> List<T> copyList(List<?> sources, Class<T> target) {
        if (sources.isEmpty()) {
            return Collections.emptyList();
        }
        List<?> filterSources = sources.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(filterSources)) {
            return new ArrayList<>();
        }

        ArrayList<T> list = new ArrayList<>(filterSources.size());
        ObjenesisStd objenesisStd = objenesisStdThreadLocal.get();
        Object sourceObject = filterSources.get(0);
        Converter converter = adaptConvert(sourceObject, target.getClass());
        boolean useConverter = converter != null;
        BeanCopier beanCopier = getCacheBeanCopier(sourceObject.getClass(), target.getClass(), useConverter);
        for (Object source : filterSources) {
            T newInstance = objenesisStd.newInstance(target);
            beanCopier.copy(source, newInstance, converter);
            list.add(newInstance);
        }
        return list;
    }

    public static <T> T copy(Object source, T target) {
        if (source == null || target == null) {
            return null;
        }
        return innerCopy(source, target);
    }

    /**
     * 默认对象拷贝
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
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
    public static <T> T innerCopy(Object source, T target) {
        try {
            Converter converter = adaptConvert(source, target.getClass());
            boolean useConverter = converter != null;
            BeanCopier beanCopier = getCacheBeanCopier(source.getClass(), target.getClass(), useConverter);
            beanCopier.copy(source, target, useConverter ? converter : null);
            return target;
        } catch (Exception e) {
            logger.error("属性拷贝错误:{}", ExceptionUtils.getStackTrace(e));
        }
        return null;
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
    private static <S, T> BeanCopier getCacheBeanCopier(Class<S> source, Class<T> target, boolean useConverter) {
        ConcurrentHashMap<Class<?>, BeanCopier> copierConcurrentHashMap = cache.computeIfAbsent(source, aClass -> new ConcurrentHashMap<>(16));

        return copierConcurrentHashMap.computeIfAbsent(target, aClass -> BeanCopier.create(source, target, useConverter));
    }


    /**
     * 查找适配的转换器
     *
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    private static <S, T> Converter adaptConvert(Object source, Class<T> target) {
        Collection<BaseConvert> convAdapters = SpringContextUtil.getApplicationContext().getBeansOfType(BaseConvert.class).values();
        for (BaseConvert convert : convAdapters) {
            if (convert.suport(source.getClass(), target)) {
                return convert;
            }
        }
        return (Converter) SpringContextUtil.getBean("defaultConvert");
    }
}
