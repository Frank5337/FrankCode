//package com.zbl.test.validate;
//
//import com.sun.istack.internal.NotNull;
////看包名就知道这个是hibernate附加的constraints
//import org.hibernate.validator.constraints.Length;
////javax.validation.constraints包下所支持的一些约束类型
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
//import javax.validation.constraints.Pattern;
//import java.util.Date;
//
///**
// * @Author: zbl
// * @Date: Created in 2020/9/17
// * @Description:
// * @Version: $
// */
//public class Employee {
//
//    @NotNull(message = "姓名必填!")
//    @Length(max = 20, message = "姓名过长!")
//    private String name;
//
//    @NotNull(message = "工牌必填!")
//    @Pattern(regexp = "^[0-9]\\d{10}",message = "请输入10位数字工牌!")//长度10，0-9
//    private String badgeCode;
//
//    @Pattern(regexp = "^[1-2]",message = "性别参数错误!")
//    @NotNull(message = "性别必填!")
//    private String gender;
//
//    @Past(message = "无效的出生日期!")
//    private Date birthDate;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getBadgeCode() {
//        return badgeCode;
//    }
//
//    public void setBadgeCode(String badgeCode) {
//        this.badgeCode = badgeCode;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public Date getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    @Override
//    public String toString() {
//        return "Employee{" +
//                "name='" + name + '\'' +
//                ", badgeCode='" + badgeCode + '\'' +
//                ", gender=" + gender +
//                ", birthDate=" + birthDate +
//                '}';
//    }
//}
