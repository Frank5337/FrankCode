package com.zbl.mapper;

import com.zbl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: zbl
 * @Date: 17:28 2020/3/1
 * @Description:
 */
@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);
}
