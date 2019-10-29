package com.zbl.code.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author linhy
 * @version CreateTime：2015年11月11日 上午9:33:32
 */
public class NumberUtils {

    public static BigDecimal getBigDecimal(String str) {
        if (StringUtils.isBlank(str) || "null".equalsIgnoreCase(str)) {
            return BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public static int getInteger(Object obj) {
        String str = String.valueOf(obj);
        if (StringUtils.isBlank(str) || "null".equalsIgnoreCase(str)) {
            return 0;
        } else {
            return Integer.valueOf(str);
        }
    }

    public static long getLong(Object obj) {
        String str = String.valueOf(obj);
        if (StringUtils.isBlank(str) || "null".equalsIgnoreCase(str)) {
            return 0;
        } else {
            return Long.valueOf(str);
        }
    }

    public static BigDecimal getDecimal(String str) {
        if (StringUtils.isBlank(str) || "null".equalsIgnoreCase(str)) {
            return BigDecimal.ZERO.setScale(4, BigDecimal.ROUND_HALF_UP);
        } else {
            return new BigDecimal(str).setScale(4, BigDecimal.ROUND_HALF_UP);
        }
    }

    // 保留五位小数
    public static BigDecimal getMinDecimal(String str) {
        if (StringUtils.isBlank(str) || "null".equalsIgnoreCase(str)) {
            return BigDecimal.ZERO.setScale(5, BigDecimal.ROUND_HALF_UP);
        } else {
            return new BigDecimal(str).setScale(5, BigDecimal.ROUND_HALF_UP);
        }
    }

    public static BigDecimal getSubtract(BigDecimal number) {
        if (number == null) {
            return null;
        } else {
            return number.multiply(new BigDecimal(-1));
        }
    }

    // 数值比较
    public static boolean notEqual(BigDecimal x, BigDecimal y) {
        if (x == null && y != null) {
            return true;
        } else if (x != null && y == null) {
            return true;
        } else if (x != null && y != null) {
            if (x.compareTo(y) != 0) {
                return true;
            }
        }
        return false;
    }

    // 数字比较
    public static boolean notEqual(Integer x, Integer y) {
        if (x == null && y != null) {
            return true;
        } else if (x != null && y == null) {
            return true;
        } else if (x != null) {
            if (!x.equals(y)) {
                return true;
            }
        }
        return false;
    }

}
