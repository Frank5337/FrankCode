package com.zbl.code.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zb util
 */
public class ImportUtils {

    public static final Integer USE_FREQUENCY = 13; //使用频次默认值

    public static final Integer PREX_USE_FREQUENCY = 10; //使用频次转换为两位数

    /**
     * 获取单元格值
     *
     * @param cell
     * @return 单元格值
     */
    public static Object getCellValue(Cell cell) {
        Object val = "";
        try {
            if (cell != null) {
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    val = cell.getNumericCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    val = cell.getCellFormula();
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                    val = cell.getErrorCellValue();
                }
            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }

    public static String getStringValue(Cell cell) {
        DecimalFormat df = new DecimalFormat("0");
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return df.format(cell.getNumericCellValue());
        }
        if (getCellValue(cell) != null) {
            return String.valueOf(getCellValue(cell)).trim();
        }
        return "";
    }

    public static boolean getBooleanValue(Cell cell, String flag) {
        boolean val = false;
        if (getStringValue(cell) != null && getStringValue(cell).equalsIgnoreCase(flag)) {
            return true;
        }
        return val;
    }

    public static BigDecimal getBigDecimalValue(HSSFCell cell) throws ParseException {
        String value = String.valueOf(getCellValue(cell));
        DecimalFormat df = new DecimalFormat("0.0000");
        if (StringUtils.isNoneBlank(value)) {
            BigDecimal valueBD = new BigDecimal(df.format(Double.valueOf(value)));
            return valueBD;
        }
        return BigDecimal.ZERO;
    }

    /**
     * 通用日期格式解析
     *
     * @param cell
     * @return
     * @throws ParseException
     */
    public static Date getDateTimeValue(HSSFCell cell) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                return date;
            }
        }
        if (cell != null && StringUtils.isNoneBlank(cell.getStringCellValue())) {
            String value = cell.getStringCellValue().replaceAll("/", "-");
            return sdf.parse(value);
        }
        return null;
    }

    /**
     * 通用日期格式解析
     *
     * @param cell
     * @return
     * @throws ParseException
     */
    public static Date getDateValue(HSSFCell cell) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                return date;
            }
        }
        if (cell != null && StringUtils.isNoneBlank(cell.getStringCellValue())) {
            String value = cell.getStringCellValue().replaceAll("/", "-");
            return sdf.parse(value);
        }
        return null;
    }

    /**
     * 博信日期格式解析
     *
     * @param cell
     * @return
     * @throws ParseException
     */
    public static Date parseDateValue(HSSFCell cell) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (cell != null && StringUtils.isNoneBlank(cell.getStringCellValue())) {
            String value = cell.getStringCellValue();
            return sdf.parse(value);
        }
        return null;
    }

    /**
     * 维商市医保获取使用频次值
     *
     * @param cell
     * @return 使用频次值
     */
    public static Integer getUseFrequenValue(Cell cell) {
        Integer useFrequenValue = USE_FREQUENCY;
        if (cell != null) {
            String value = String.valueOf(getCellValue(cell));
            if (StringUtils.isNoneBlank(value)) {
                if (5 < new BigDecimal(value).intValue() && new BigDecimal(value).intValue() < 10) {
                    useFrequenValue = 13;
                } else if (new BigDecimal(value).intValue() <= 5) {
                    useFrequenValue = PREX_USE_FREQUENCY + new BigDecimal(value).intValue();
                } else {
                    useFrequenValue = new BigDecimal(value).intValue();
                }
            }

        }
        return useFrequenValue;
    }

    /**
     * 采购商品日期格式解析
     *
     * @param cell
     * @return
     */
    public static String parseToString(Cell cell) {
        DecimalFormat df = new DecimalFormat("0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                return sdf.format(date);
            } else {
                return df.format(cell.getNumericCellValue());
            }
        }
        if (getCellValue(cell) != null) {
            return String.valueOf(getCellValue(cell));
        }
        return "";
    }
}
