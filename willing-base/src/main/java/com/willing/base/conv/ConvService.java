package com.willing.base.conv;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 *
 */
@Component
public class ConvService {

    @Autowired
    private ApplicationContext ctx;



    /**
     * 拷贝列表
     * @param srcs
     * @param target
     * @param <T>
     * @return
     */
    public <T> List<T> convs(List<Object> srcs, Class<T> target) {
        //获取适配器列表
        Collection<Conv> convAdapters = ctx.getBeansOfType(Conv.class).values();

        for (Conv conv : convAdapters) {
            if (conv.suport(srcs.get(0).getClass(), target)) {
                return conv.convs(srcs);
            }
        }
        return null;
    }

    /**
     * 对象转换
     *
     * @param src
     * @param target
     * @param <T>
     * @return
     */
    public <T> T conv(Object src, Class<T> target) {
        if (src == null) {
            return null;
        }
        //获取url得适配器列表
        Collection<Conv> convAdapters = ctx.getBeansOfType(Conv.class).values();

        for (Conv conv : convAdapters) {
            if (conv.suport(src.getClass(), target)) {
                return (T) conv.conv(src);
            }
        }
        return null;
    }


    /**
     * 转换更新record
     * @param <T>
     * @param src
     * @param target
     * @return
     */
/*    public <T extends Record> UpdateQuery<T> convUpdateRecord(Object src, Class<T> target) {
        if (src == null) {
            return null;
        }
        //获取url得适配器列表
        Collection<UpdateRecordConv> convAdapters = ctx.getBeansOfType(UpdateRecordConv.class).values();

        for (UpdateRecordConv conv : convAdapters) {
            if (conv.suport(src.getClass(), target)) {
                return (UpdateQuery<T>) conv.conv(src);
            }
        }
        return null;
    }*/
}
