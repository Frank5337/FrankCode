package com.zbl.code.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @Author: zbl
 * @Date: Created in 17:12 2019/8/20
 * @Description: json工具类
 * @Version: $
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    private static ObjectMapper defaultMapper;
    private static ObjectMapper formattedMapper;

    static {
        // 默认的ObjectMapper
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        defaultMapper = new ObjectMapper();
        defaultMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        // 所有日期格式都统一为固定格式
        formattedMapper = new ObjectMapper();
        formattedMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        formattedMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        formattedMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * 将对象转化为json数据
     */
    public static String toJson(Object obj) throws IOException {
        return defaultMapper.writeValueAsString(obj);
    }

    /**
     * json数据转化为对象(Class)
     */
    public static <T> T parseJson(String jsonText, Class<T> cls) throws IOException {
        return defaultMapper.readValue(jsonText, cls);
    }

    /**
     * 将对象转化为json数据(时间转换格式： "yyyy-MM-dd HH:mm:ss")
     */
    public static String toJsonWithFormat(Object obj) throws IOException {
        return formattedMapper.writeValueAsString(obj);
    }

    /**
     * json数据转化为对象(时间转换格式： "yyyy-MM-dd HH:mm:ss")
     */
    public static <T> T parseJsonWithFormat(String jsonText, Class<T> cls) throws IOException {
        if (StringUtil.isBlank(jsonText)) {
            return null;
        }

        return formattedMapper.readValue(jsonText, cls);
    }
}
