package com.willing.base.conv;


import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName Conv
 * @Description 转换父类
 **/
public abstract class Conv<Source, Target> {

    public boolean suport(Class sourceObj, Class targetObj) {

        //第一个泛型得类型
        Class clazz1 = getSourceClass();

        //第二个泛型得类型
        Class clazz2 = getTargetClass();

        return clazz1.equals(sourceObj) &&
                clazz2.equals(targetObj);
    }

    public Class<Source> getSourceClass() {
        Class<Source> tClass = (Class<Source>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }


    public Class<Target> getTargetClass() {
        Class<Target> tClass = (Class<Target>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return tClass;
    }

   public abstract Target conv(Source param);

    List<Target> convs(List<Source> params) {
        if (CollectionUtils.isEmpty(params)) {
            return Collections.emptyList();
        }

        List<Target> targets = new ArrayList<>(params.size());
        for (Source param : params) {

            targets.add(this.conv(param));
        }
        return targets;
    }
}
