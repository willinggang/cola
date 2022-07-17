package com.willing.base.conv;

/**
 * @ClassName Conv
 * @Description 转换父类
 **/
/*
public abstract class UpdateRecordConv<Source, Target extends Record> {

    public boolean suport(Class sourceObj, Class<Target> targetObj) {

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

   public abstract UpdateQuery<Target> conv(Source param);
*/

/*    List<Target> convs(List<Source> params) {
        if (CollectionUtils.isEmpty(params)) {
            return Collections.emptyList();
        }

        List<Target> targets = new ArrayList<>(params.size());
        for (Source param : params) {
            targets.add(this.conv(param));
        }
        return targets;
    }
}*/
