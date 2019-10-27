package com.zbl.code.system.service;

import com.zbl.code.system.pojo.vo.ConfigVO;

import java.util.List;

/**
 * Created by hook on 2019-04-05
 * <p>
 * 系统配置 服务类
 */
public interface ConfigService {

    List<ConfigVO> list();

    String save(String key, String value, String remark);

    String update(String key, String value, String remark);

    void remove(String key);

    String getByKey(String key);
}