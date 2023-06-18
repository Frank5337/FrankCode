package com.zbl.code.manager.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: Frank
 * @date: Created in 2023/6/18
 */
@Data
public class UserExcelData {

    @ExcelProperty("userName")
    private String userName;

    @ExcelProperty("password")
    private String password;

    @ExcelProperty("address")
    private String address;

    @ExcelProperty("age")
    private String age;
}
