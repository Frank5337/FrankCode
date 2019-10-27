package com.zbl.code.system.service.impl;

import com.zbl.code.common.base.BaseException;
import com.zbl.code.common.base.BaseService;
import com.zbl.code.common.enums.ErrorCodeEnum;
import com.zbl.code.system.dao.ConfigMapper;
import com.zbl.code.system.pojo.po.Config;
import com.zbl.code.system.pojo.vo.ConfigVO;
import com.zbl.code.system.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hook on 2019-04-05
 * <p>
 * 系统配置 服务实现类
 */
@Slf4j
@Service
public class ConfigServiceImpl extends BaseService implements ConfigService {
    @Resource
    private ConfigMapper configMapper;

    @Override
    public List<ConfigVO> list() {
        return configMapper.list();
    }

    @Override
    @CachePut(cacheNames = "config", key = "#key")
    public String save(String key, String value, String remark) {
        Config search = new Config();
        search.setSysKey(key);
        if (configMapper.selectOne(search) != null) {
            throw new BaseException(ErrorCodeEnum.SYS_CONFIG_KEY_EXIST);
        }

        Config config = new Config();
        config.setSysKey(key);
        config.setSysValue(value);
        config.setRemark(remark);
        configMapper.insertSelective(config);
        return value;
    }

    @Override
    @CachePut(cacheNames = "config", key = "#key")
    public String update(String key, String value, String remark) {
        Config search = new Config();
        search.setSysKey(key);
        Config config = configMapper.selectOne(search);
        config.setSysValue(value);
        config.setRemark(remark);
        configMapper.updateByPrimaryKeySelective(config);
        return value;
    }

    @Override
    @CacheEvict(cacheNames = "config", key = "#key")
    public void remove(String key) {
        Config search = new Config();
        search.setSysKey(key);
        configMapper.delete(search);
    }

    @Override
    @Cacheable(cacheNames = "config", key = "#key")
    public String getByKey(String key) {
        log.error("哈哈哈哈");
        Config search = new Config();
        search.setSysKey(key);
        Config config = configMapper.selectOne(search);
        if (config == null) {
            throw new BaseException(ErrorCodeEnum.SYS_CONFIG_KEY_NOT_EXIST);
        }
        return config.getSysValue();
    }
}