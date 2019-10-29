package com.zbl.code.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author linhy
 * @version CreateTime：2014年12月24日 下午4:46:17
 */
public class BeanUtils {

    /**
     * 将json格式的字符串解析成Map对象
     */
    public static Map<String, Object> convertJsonToMap(String json) {
        Map<String, Object> data = new HashMap<>();
        if (StringUtils.isBlank(json)) {
            return data;
        }
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.parseObject(json);
        Iterator it = jsonObject.keySet().iterator();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            data.put(key, jsonObject.get(key));
        }
        return data;
    }

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Map<String, Object> convertBeanToMap(Object bean) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();

            if (!"class".equals(propertyName)) {
                Method readMethod = propertyDescriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (null != result) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将一个map对象转化为JavaBean
     *
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object convertMapToBean(Class<?> type, Map<String, Object> map) throws IntrospectionException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();

            if (map.containsKey(propertyName)) {
                try {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[]{value};
                    propertyDescriptor.getWriteMethod().invoke(obj, args);
                } catch (Exception e) {
                    System.err.println("The failure of " + propertyName + " assignment");
                }

            }

        }
        return obj;
    }
}
