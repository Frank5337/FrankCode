package com.zbl.code.common.base;

import com.zbl.code.common.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: zbl
 * @Date: Created in 16:08 2019/8/20
 * @Description: 业务异常
 * @Version: $
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException{
    private Integer code;
    private String msg;

    public BaseException(String msg) {
        super(msg);
        this.code = ErrorCodeEnum.GL_DEFAULT.getCode();
        this.msg = msg;
    }

    public BaseException(ErrorCodeEnum code) {
        super(code.getMsg());
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public BaseException(ErrorCodeEnum code, String msg) {
        super(msg);
        this.code = code.getCode();
        this.msg = msg;
    }

    public BaseException(Integer code) {
        super(ErrorCodeEnum.getMsg(code));
        this.code = code;
        this.msg = ErrorCodeEnum.getMsg(code);
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
