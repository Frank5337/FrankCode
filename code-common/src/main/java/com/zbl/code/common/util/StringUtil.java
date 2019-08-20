package com.zbl.code.common.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.*;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: 字符串工具类
 * @Version: $
 */
public class StringUtil {

    // 字符串是否为空
    public static boolean isBlank(CharSequence cs) {
        Integer strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (Integer i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    // 是否所有的字符串为空
    public static boolean isAllBlank(CharSequence... css) {
        for (CharSequence cs : css) {
            if (!isBlank(cs)) {
                return false;
            }
        }

        return true;
    }

    // 是否有字符串为空
    public static boolean isHaveBlank(CharSequence... css) {
        for (CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }

        return false;
    }

    // 字符串是否是数字
    public static boolean isNumeric(String text) {
        if (text == null) {
            return false;
        } else {
            Integer sz = text.length();

            for (Integer i = 0; i < sz; ++i) {
                if (!Character.isDigit(text.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isPhone(String text) {
        return isNumeric(text) && text.length() == 11;
    }

    // 产生一个随机数
    public static String getRandomNumber(Integer length) {
        return getRandom("0123456789", length);
    }

    // 产生一个随机字符串
    public static String getRandomString(Integer length) {
        return getRandom("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    // 产生一个唯一的随机字符串
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String md5(String text) {
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = text.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) { // i = 0
                byte byte0 = md[i]; // 95
                str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5
                str[k++] = md5String[byte0 & 0xf]; // F
            }

            // 返回经过加密后的字符串
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // MD5加密
    public static String encodeByMD5(String text) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = text.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    // SHA1加密
    public static String encodeBySHA1(String text) {
        if (null == text || 0 == text.length()) {
            return null;
        }

        return DigestUtils.sha1Hex(text);
    }

    public static String setToText(Set<Long> ids) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Long id : ids) {
            if (!first) {
                sb.append(",");
            }
            sb.append(id);
            first = false;
        }

        return sb.toString();
    }

    public static Long getLong(String val) {
        try {
            return Long.valueOf(val);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Integer getInteger(String val) {
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static BigDecimal getDec(String val) {
        try {
            return new BigDecimal(val);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getListStr(List<String> data, Integer index) {
        try {
            return StringUtil.isBlank(data.get(index)) ? null : data.get(index);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getRandom(String base, Integer length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (Integer i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    /**
     * 截取字符串
     *
     * @param fromStr
     * @param startStr
     * @param endStr
     * @return
     */
    public static String splitStr(String fromStr, String startStr, String endStr) {
        String result = null;
        int startIndex = fromStr.indexOf(startStr);
        int endIndex = fromStr.indexOf(endStr, startIndex + startStr.length());
        if (startIndex > -1 && endIndex > -1) {
            result = fromStr.substring(startIndex + startStr.length(), endIndex).trim();
        }
        return result;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) { return true;}
        else if (obj instanceof CharSequence) { return ((CharSequence) obj).length() == 0;}
        else if (obj instanceof Collection) { return ((Collection) obj).isEmpty();}
        else if (obj instanceof Map) { return ((Map) obj).isEmpty();}
        else if (obj.getClass().isArray()) { return Array.getLength(obj) == 0;}

        return false;
    }

    public static BigDecimal convertBigDecimal(String integer) {
        try {
            BigDecimal bigDecimal = new BigDecimal(integer);
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Map<String, String> getAccount(String name) {
        Map<String, String> account = new HashMap<>();
        if (isPhone(name)) {
            account.put("phone", name);
        } else if (IdCardUtil.isIdCard(name)) {
            account.put("idCardNo", name);
        } else {
            account.put("name", name);
        }

        return account;
    }
}