package com.willing.base.conv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @className: DefaultConvert
 * @description: 默认转换器
 * @author: Szg
 * @date: 2021/7/24
 **/
@Component
public class DefaultConvert implements Converter {
    private static final Logger logger = LoggerFactory.getLogger(DefaultConvert.class);

    @Override
    public Object convert(Object value, Class target, Object context) {
        logger.debug("属性拷贝 value:{},target:{},context:{}", value, target, context);
        if (value == null || target == null || context == null) {
            return null;
        }
        /*if (value instanceof UByte) {
            return convertUByte(value, target);
        }
        if (value instanceof ULong) {
            return convertULong(value, target);
        }
        if (value instanceof UInteger) {
            return convertUInteger(value, target);
        }
        if (value instanceof Byte) {
            return convertByte(value, target);
        }
        if (value instanceof String){
            return convertString(value,target);
        }
        if (value instanceof BigDecimal){
            return convertBigDecimal(value,target);
        }
        if (value.getClass().equals(Integer.class) && target.equals(Long.class)) {
            return ((Integer) value).longValue();
        }
        *//*Long类型转换Integer类型*//*
        if (value.getClass().equals(Long.class) && target.equals(Integer.class)) {
            return NumberUtils.castLongToInteger((Long) value);
        }
        *//*Integer类型转String类型*//*
        if (isNumberClass(value) && target.equals(String.class)) {
            return String.valueOf(value);
        }
        if (value.getClass().equals(LocalDateTime.class) && target.equals(Date.class)) {
            return asDate((LocalDateTime) value);
        }
        if (value.getClass().equals(Date.class) && target.equals(LocalDateTime.class)) {
            return asLocalTime((Date) value);
        }*/
        return value;
    }
/*
    private Object convertString(Object value, Class target) {
        if (target.equals(Integer.class)) {
            return Integer.parseInt((String) value);
        } else if (target.equals(Long.class)) {
            return Long.parseLong((String) value);
        } else if (target.equals(BigDecimal.class)) {
            return new BigDecimal((String) value);
        }
        return value;
    }

    private Object convertBigDecimal(Object value, Class target) {
        if (target.equals(String.class)) {
            return String.valueOf((BigDecimal) value);
        } else if (target.equals(Double.class)) {
            return ((BigDecimal) value).doubleValue();
        } else {
            return value;
        }
    }

    private Object convertUByte(Object value, Class target) {
        if (target.equals(Integer.class)) {
            return ((UByte) value).intValue();
        } else if (target.equals(Long.class)) {
            return ((UByte) value).longValue();
        } else if (target.equals(String.class)) {
            return String.valueOf((UByte) value);
        }
        return value;
    }

    private Object convertULong(Object value, Class target) {
        if (target.equals(Integer.class)) {
            return ((ULong) value).intValue();
        } else if (target.equals(Long.class)) {
            return ((ULong) value).longValue();
        } else if (target.equals(String.class)) {
            return String.valueOf((ULong) value);
        }
        return value;
    }

    private Object convertUInteger(Object value, Class target) {
        if (target.equals(Integer.class)) {
            return ((UInteger) value).intValue();
        } else if (target.equals(Long.class)) {
            return ((UInteger) value).longValue();
        } else if (target.equals(String.class)) {
            return String.valueOf((UInteger) value);
        }
        return value;
    }

    private Object convertByte(Object value, Class target) {
        if (target.equals(Integer.class)) {
            return Byte.toUnsignedInt((Byte) value);
        } else if (target.equals(Long.class)) {
            return Byte.toUnsignedLong((Byte) value);
        } else if (target.equals(String.class)) {
            return String.valueOf((Byte) value);
        }
        return value;
    }

    private Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private LocalDateTime asLocalTime(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        if (instant == null) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }*/

    /**
     * 是否是数字类型
     *
     * @param value
     * @return
     */
    private boolean isNumberClass(Object value) {
        Class<?> aClass = value.getClass();
        return aClass.equals(Integer.class) || aClass.equals(Long.class);
    }
}