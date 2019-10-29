package com.zbl.code.common.util;

import com.zbl.code.common.enums.SexTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


/**
 * @Author: zbl
 * @Date: Created in 16:51 2019/8/20
 * @Description: 身份证工具类
 * @Version: $
 */
public class IdCardUtil {
    private static final Logger logger = LoggerFactory.getLogger(IdCardUtil.class);

    private static final String FILE_PATH = "/.properties";

    private static Properties props = new Properties();

    // 每位加权因子
    private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    // 第18位校检码
    private static final String[] VERIFY_CODE = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    static {
        try {
            props.load(new InputStreamReader(IdCardUtil.class.getResourceAsStream("/idCardAddress.properties"),
                    StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private IdCardUtil() {

    }

    // 验证身份证是否合法
    public static Boolean isIdCard(String idCardNo) {
        if (StringUtil.isBlank(idCardNo)) {
            return false;
        }

        if (idCardNo.length() != 18) {
            return false;
        }

        String idCardInfo = idCardNo.substring(0, 17);
        String verify = idCardNo.substring(17, 18);
        if (!StringUtil.isNumeric(idCardInfo)) {
            return false;
        }

        // 校验年月日
        Integer year = getYear(idCardNo);
        if (year == null || year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;
        }
        Integer month = getMonth(idCardNo);
        if (month == null || month < 1 || month > 12) {
            return false;
        }
        Integer day = getDay(idCardNo);
        if (day == null || day < 1 || day > 31) {
            return false;
        }

        // 校验性别
        if (SexTypeEnum.UNKNOWN.equals(getSex(idCardNo))) {
            return false;
        }

        // 校验地址
        if (StringUtil.isBlank(getProvince(idCardNo))) { // if (StringUtil.isHaveBlank(getProvince(idCardNo), getCity(idCardNo), getDistrict(idCardNo))) {
            return false;
        }

        // 校验最后校验位
        int iSum = 0;
        char[] cArr = idCardInfo.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            iSum += Character.getNumericValue(cArr[i]) * POWER[i];
        }
        return VERIFY_CODE[iSum % 11].equals(verify);
    }

    public static Integer getYear(String idCardNo) {
        return getInt(idCardNo, 6, 10);
    }

    public static Integer getMonth(String idCardNo) {
        return getInt(idCardNo, 10, 12);
    }

    public static Integer getDay(String idCardNo) {
        return getInt(idCardNo, 12, 14);
    }

    public static Date getBirth(String idCardNo) {
        //noinspection deprecation
        return new Date(getYear(idCardNo) - 1900, getMonth(idCardNo) - 1, getDay(idCardNo));
    }

    public static SexTypeEnum getSex(String idCardNo) {
        Integer sex = getInt(idCardNo, 16, 17);
        return sex == null ? SexTypeEnum.UNKNOWN : (sex % 2 == 0 ? SexTypeEnum.FEMALE : SexTypeEnum.MALE);
    }

    // 获取省份
    public static String getProvince(String idCardNo) {
        return props.getProperty(idCardNo.substring(0, 2) + "0000");
    }

    // 获取城市
    public static String getCity(String idCardNo) {
        return props.getProperty(idCardNo.substring(0, 4) + "00");
    }

    // 获取区域
    public static String getDistrict(String idCardNo) {
        return props.getProperty(idCardNo.substring(0, 6));
    }

    private static Integer getInt(String text, int start, int end) {
        try {
            return Integer.valueOf(text.substring(start, end));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }
}
