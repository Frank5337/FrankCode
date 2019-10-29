package com.zbl.code.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linhy
 * @version CreateTime：2015年1月22日 上午10:46:57
 */
public class PrimaryGenerator {
    private static PrimaryGenerator primaryGenerator = null;

    private PrimaryGenerator() {
    }

    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static PrimaryGenerator getInstance() {
        if (primaryGenerator == null) {
            synchronized (PrimaryGenerator.class) {
                if (primaryGenerator == null) {
                    primaryGenerator = new PrimaryGenerator();
                }
            }
        }
        return primaryGenerator;
    }

    /**
     * 生成下一个编号
     *
     * @param sno  最后的流水号
     * @param code 前缀
     */
    public synchronized String generaterNextNumberWithMediCode(String prefix, String sno) {
        String id = null;
        if (StringUtils.isBlank(sno)) {
            id = prefix + "00001";
        } else {
            DecimalFormat df = new DecimalFormat("00000");
            id = prefix + df.format(1 + Integer.parseInt(sno.substring(3, 8)));
        }

        return id;
    }

    /**
     * 生成下一个编号
     *
     * @param sno  最后的流水号
     * @param code 前缀
     */
    public synchronized String generaterNextNumber(String sno, String code) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (StringUtils.isBlank(sno)) {
            id = formatter.format(date) + "000001";
        } else {
            DecimalFormat df = new DecimalFormat("000000");
            id = formatter.format(date) + df.format(1 + Integer.parseInt(sno.substring(10, 16)));
        }
        if (code == null) {
            return id;
        }
        return code + id;
    }

    /**
     * 生成下一个编号
     *
     * @param sno  最后的流水号
     * @param code 前缀
     */
    public synchronized String generateNextCode(String sno, String code) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (StringUtils.isBlank(sno)) {
            id = formatter.format(date) + "000001";
        } else {
            DecimalFormat df = new DecimalFormat("000000");
            id = formatter.format(date) + df.format(1 + Integer.parseInt(sno.substring(sno.length() - 6)));
        }
        if (code == null) {
            return id;
        }
        return code + id;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().generaterNextNumber("PD20150305000000", "PD"));
        System.out.println(getInstance().generaterNextNumberWithMediCode("101", "10100003"));
    }
}
