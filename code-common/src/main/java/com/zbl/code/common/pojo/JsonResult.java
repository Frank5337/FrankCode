package com.zbl.code.common.pojo;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.common.util.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Author: zbl
 * @Date: Created in 16:45 2019/8/20
 * @Description: json返回数据
 * @Version: $
 */
@Data
@ApiModel("返回体")
public class JsonResult<T> {
    // 成功码
    public static final Integer SUCCESS_CODE = 0;
    // 成功信息
    public static final String SUCCESS_MESSAGE = "操作成功";

    @ApiModelProperty("返回码")
    private Integer code = SUCCESS_CODE;
    @ApiModelProperty("返回信息")
    private String msg = SUCCESS_MESSAGE;
    @ApiModelProperty("返回数据")
    private T data;

    private JsonResult() {

    }

    private JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok() {
        return ok("null");
    }

    public static <T> JsonResult<T> ok(T data) {
        return ok(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> JsonResult<T> ok(Integer code, String msg, T data) {
        return new JsonResult<>(code, msg, data);
    }

    public static <T> JsonResult<PageData<T>> ok(Integer pageNum, Integer pageSize, ISelect iSelect) {
        return ok(getPage(pageNum, pageSize, iSelect));
    }

    public static <T, V> JsonResult<ExtraPageData<T, V>> ok(
            Integer pageNum, Integer pageSize, ISelect iSelect, V v) {
        return ok(new ExtraPageData<>(PageHelper.startPage(pageNum, pageSize).doSelectPage(iSelect), v));
    }

    public static <T, V> JsonResult<PageData<V>> ok(
            Integer pageNum, Integer pageSize, ISelect iSelect, Function<T, V> function) {
        return ok(getLevelPage(pageNum, pageSize, iSelect, function));
    }

    public static <T, U, V> JsonResult<ExtraPageData<U, V>> ok(
            Integer pageNum, Integer pageSize, ISelect iSelect, Function<T, U> function, V v) {

        return ok(new ExtraPageData<>(getLevelPage(pageNum, pageSize, iSelect, function), v));
    }

    public static <T> PageData<T> getPage(Integer pageNum, Integer pageSize, ISelect iSelect) {
        return new PageData<>(PageHelper.startPage(pageNum, pageSize).doSelectPage(iSelect));
    }

    public static <T, V> PageData<V> getLevelPage(
            Integer pageNum, Integer pageSize, ISelect iSelect, Function<T, V> function) {
        PageData<T> data = getPage(pageNum, pageSize, iSelect);
        PageData<V> rslt = new PageData<>();
        rslt.setTotal(data.getTotal());
        rslt.setPageNum(data.getPageNum());
        rslt.setPageSize(data.getPageSize());
        List<V> list = new ArrayList<>();
        for (T t : data.getPageList()) {
            list.add(function.apply(t));
        }
        rslt.setPageList(list);
        return rslt;
    }

    public static JsonResult failure() {
        return failure(ErrorCodeEnum.GL_DEFAULT);
    }

    public static JsonResult failure(String msg) {
        return new JsonResult(ErrorCodeEnum.GL_DEFAULT.getCode(), msg, null);
    }

    public static JsonResult failure(ErrorCodeEnum code) {
        return failure(code, null, null);
    }

    public static JsonResult failure(ErrorCodeEnum code, String msg) {
        return failure(code, msg, null);
    }

    public static <T> JsonResult<T> failure(ErrorCodeEnum code, String msg, T data) {
        return new JsonResult(code.getCode(), StringUtil.isBlank(msg) ? code.getMsg() : msg, data);
    }

    public static <T> JsonResult<T> failure(Integer code) {
        return failure(code, null, null);
    }

    public static <T> JsonResult<T> failure(Integer code, String msg) {
        return failure(code, msg, null);
    }

    public static <T> JsonResult<T> failure(Integer code, String msg, T data) {
        return new JsonResult(code, StringUtil.isBlank(msg) ? ErrorCodeEnum.getMsg(code) : msg, data);
    }
}