package com.zbl.code.common.convert;


import com.zbl.code.common.base.BaseEnum;
import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.common.util.StringUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Author: zbl
 * @Date: Created in 9:06 2019/8/20
 * @Description: 枚举类型转化器
 * @Version: $
 */
public class EnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter result = converterMap.get(targetType);

        if (result == null) {
            result = new IntegerStrToEnum<>(targetType);
            converterMap.put(targetType, result);
        }

        return result;
    }

    class IntegerStrToEnum<T extends BaseEnum> implements Converter<String, T> {
        private final Class<T> enumType;
        private Map<String, T> enumMap = new HashMap<>();

        IntegerStrToEnum(Class<T> enumType) {
            this.enumType = enumType;
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(e.getCode() + "", e);
            }
        }


        @Override
        public T convert(String source) {
            if (StringUtil.isBlank(source)) {
                return null;
            }

            T t = enumMap.get(source);
            if (t == null) {
                throw new BaseException(ErrorCodeEnum.GL_BAD_PARAM);
            }

            return t;
        }
    }
}
