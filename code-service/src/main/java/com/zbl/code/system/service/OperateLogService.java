package com.zbl.code.system.service;

import com.zbl.code.system.pojo.vo.OperateLogVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Author: by zbl on 2019-08-22
 * <p>
 * 操作日志 服务类
 */
public interface OperateLogService {

    // 保存日志
    void save(
            Long operatorId, String host, String ctrl, String method, String url, String params, Integer code,
            String msg);

    List<OperateLogVO> list(String operateName, String module, String submodule, String operate, Date start, Date end);

    void export(
            HttpServletResponse resp, String operateName, String module, String submodule, String operate,
            Date start, Date end);
}