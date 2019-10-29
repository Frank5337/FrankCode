package com.zbl.code.common.util;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.base.Global;
import com.zbl.code.common.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: zbl
 * @Date: Created in 17:11 2019/8/20
 * @Description: Excel工具类
 * @Version: $
 */
@Slf4j
public class ExcelUtil {
    // 表头行开始位置
    private static final int HEAD_START_POSITION = 0;
    // 文本行开始位置
    private static final int CONTENT_START_POSITION = 1;
    // excel文件格式
    private static final String[] EXCEL_TYPES = {"xls", "et"};

    /**
     * 读取excel文件数据
     */
    public static List<ArrayList<String>> readExcel(MultipartFile file) {
        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            throw new BaseException(ErrorCodeEnum.GL_BAD_EXCEL);
        }
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        if (!isExcl(fileExt)) {
            throw new BaseException(ErrorCodeEnum.GL_BAD_EXCEL);
        }
        // IO流读取文件
        List<ArrayList<String>> data = new ArrayList<>();
        InputStream input = null;
        HSSFWorkbook readWorkbook;
        ArrayList<String> rowList;
        try {
            input = file.getInputStream();
            // 创建文档
            readWorkbook = new HSSFWorkbook(input);
            //读取sheet(页)
            for (int numSheet = 0; numSheet < readWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet readSheet = readWorkbook.getSheetAt(numSheet);
                if (readSheet == null) {
                    continue;
                }
                //读取Row,从第二行开始
                for (int rowNum = 1; rowNum <= readSheet.getLastRowNum(); rowNum++) {
                    HSSFRow readRow = readSheet.getRow(rowNum);
                    if (readRow != null) {
                        rowList = new ArrayList<>();
                        //读取列，从第一列开始
                        for (int columnNum = 0; columnNum <= readRow.getLastCellNum() + 1; columnNum++) {
                            HSSFCell cell = readRow.getCell(columnNum);
                            rowList.add(getCellContent(cell));
                        }
                        data.add(rowList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }


    public static void exportMould(List<String> titles, String fileName, HttpServletResponse resp) {
        exportExcel(null, null, titles, fileName, resp);
    }

    /**
     * 导出数据到excel
     *
     * @param dataList 数据
     * @param titles   表头
     * @param fileName 文件名字
     * @param resp     返回
     */
    public static void exportData(
            List<?> dataList, Map<String, String> titles, String fileName, HttpServletResponse resp) {
        exportExcel(dataList, titles, null, fileName, resp);
    }

    private static void exportExcel(
            List<?> dataList, Map<String, String> titleMap, List<String> titleList, String fileName,
            HttpServletResponse resp) {
        // 初始化workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);
        HSSFRow headRow = sheet.createRow(HEAD_START_POSITION);

        // 创建表头
        int rowIndex = 0;
        if (titleList == null) {
            for (Map.Entry<String, String> m : titleMap.entrySet()) {
                HSSFCell headCell = headRow.createCell(rowIndex);
                headCell.setCellValue(m.getValue());
                rowIndex++;
            }
        } else {
            for (String title : titleList) {
                HSSFCell headCell = headRow.createCell(rowIndex);
                headCell.setCellValue(title);
                rowIndex++;
            }
        }

        // 文本行
        try {
            rowIndex = 0;
            if (dataList != null) {
                for (Object data : dataList) {
                    HSSFRow textRow = sheet.createRow(CONTENT_START_POSITION + rowIndex);
                    int index = 0;
                    for (String entry : titleMap.keySet()) {
                        String method = "get" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
                        Object val = data.getClass().getMethod(method).invoke(data);
                        HSSFCell textCell = textRow.createCell(index);
                        textCell.setCellValue(val == null ? "" : val.toString());
                        index++;
                    }
                    rowIndex++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 写入处理结果
        ByteArrayOutputStream out;
        try {
            out = new ByteArrayOutputStream();
            String fileDisplay = fileName + ".xls";
            File file = new File(FileSystemView.getFileSystemView().getHomeDirectory().toString() + "\\" + fileDisplay);
            if (file.exists()) {
                fileDisplay = fileName + (TimeUtil.date2String(new Date(), Global.DEF_TIME_FORMAT)) + ".xls";
            }
            workbook.write(out);
            out.flush();
            out.close();
            byte[] bytes = out.toByteArray();
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=" + new String(fileDisplay.getBytes("gb2312"), "ISO8859-1"));
            resp.setContentLength(bytes.length);
            resp.getOutputStream().write(bytes);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static boolean isExcl(String fileExt) {
        if (StringUtil.isBlank(fileExt)) {
            return false;
        }

        for (String excelType : EXCEL_TYPES) {
            if (fileExt.equals(excelType)) {
                return true;
            }
        }

        return false;
    }

    private static String getCellContent(HSSFCell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getRichStringCellValue().getString().trim();
            case NUMERIC:
                DataFormatter dataFormatter = new DataFormatter();
                dataFormatter.addFormat("###########", null);
                return dataFormatter.formatCellValue(cell);
            default:
                throw new BaseException(ErrorCodeEnum.GL_BAD_EXCEL);
        }
    }

    public static void main(String[] args) {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            //加载图片
            bufferImg = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\微信图片编辑_20190718193208.jpg"));
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1 = wb.createSheet("sheet1");
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
            /**
             dx1 - the x coordinate within the first cell.//定义了图片在第一个cell内的偏移x坐标，既左上角所在cell的偏移x坐标，一般可设0
             dy1 - the y coordinate within the first cell.//定义了图片在第一个cell的偏移y坐标，既左上角所在cell的偏移y坐标，一般可设0
             dx2 - the x coordinate within the second cell.//定义了图片在第二个cell的偏移x坐标，既右下角所在cell的偏移x坐标，一般可设0
             dy2 - the y coordinate within the second cell.//定义了图片在第二个cell的偏移y坐标，既右下角所在cell的偏移y坐标，一般可设0
             col1 - the column (0 based) of the first cell.//第一个cell所在列，既图片左上角所在列
             row1 - the row (0 based) of the first cell.//图片左上角所在行
             col2 - the column (0 based) of the second cell.//图片右下角所在列
             row2 - the row (0 based) of the second cell.//图片右下角所在行
             */
            HSSFClientAnchor anchor = new HSSFClientAnchor(100, 0, 0, 0, (short) 2, 2, (short) 5, 8);
            //插入图片
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            fileOut = new FileOutputStream("e:/excel.xls");
            // 输出文件
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
