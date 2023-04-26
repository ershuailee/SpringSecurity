package com.example.springsecurity.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 复制工具类
 */
public class ConvertUtils {

    /**
     * 将一个对象转换为另一个类的对象
     *
     * @param source      要转换的对象
     * @param targetClass 要转换成的类
     * @return 转换后的对象，如果转换失败则返回 null
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            // 创建 targetClass 的实例
            Constructor<T> constructor = targetClass.getDeclaredConstructor();
            T target = constructor.newInstance();
            // 使用 BeanUtils 复制属性
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个 List 转换为另一个类型的 List
     *
     * @param source 原始 List
     * @param clazz  目标 List 中元素的类型
     * @return 转换后的 List，如果原始 List 为空，则返回一个空 List
     */
    public static <T> List<T> convert(List<?> source, Class<T> clazz) {
        ArrayList<T> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(source)) {
            return result;
        }
        for (Object s : source) {
            result.add(convert(s, clazz));
        }
        return result;
    }
}