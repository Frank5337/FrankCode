package com.zbl.code.system.service.impl;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.base.BaseService;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.common.util.ExcelUtil;
import com.zbl.code.system.dao.OperateLogMapper;
import com.zbl.code.system.pojo.po.OperateLog;
import com.zbl.code.system.pojo.po.Permission;
import com.zbl.code.system.pojo.vo.OperateLogVO;
import com.zbl.code.system.service.OperateLogService;
import com.zbl.code.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by zbl on 2019-08-22
 * <p>
 * 操作日志 服务实现类
 */
@Slf4j
@Service
public class OperateLogServiceImpl extends BaseService implements OperateLogService {
    @Resource
    private OperateLogMapper operateLogMapper;
    @Resource
    private PermissionService permissionService;

    @Override
    public void save(
            Long operatorId, String host, String ctrl, String method, String url, String params, Integer code,
            String msg) {
        if (operatorId == null) {
            return;
        }

        Permission permission = permissionService.getByCtrlAndMethod(ctrl, method);
        if (permission == null) {
            throw new BaseException(ErrorCodeEnum.SYS_PERMISSION_NOT_EXIST);
        }

        OperateLog operateLog = new OperateLog();
        operateLog.setOperatorId(operatorId);
        operateLog.setOperateIp(host);
        operateLog.setPermissionId(permission.getId());
        operateLog.setReqUrl(url);
        operateLog.setReqParams(params);
        operateLog.setRespCode(code);
        operateLog.setRespMessage(msg);
        operateLogMapper.insertSelective(operateLog);
    }

    @Override
    public List<OperateLogVO> list(
            String operateName, String module, String submodule, String operate, Date start, Date end) {
        return operateLogMapper.list(operateName, module, submodule, operate, start, end);
    }

    @Override
    public void export(
            HttpServletResponse resp, String operateName, String module, String submodule, String operate, Date start,
            Date end) {
        Map<String, String> titles = new LinkedHashMap<>();
        titles.put("name", "操作人姓名");
        titles.put("module", "主模块");
        titles.put("submodule", "子模块名称");
        titles.put("operate", "操作");
        titles.put("operateIp", "IP地址");
        titles.put("createTime", "时间");
        titles.put("respMessage", "结果");
        ExcelUtil.exportData(list(operateName, module, submodule, operate, start, end), titles, "操作日志", resp);

    }

}