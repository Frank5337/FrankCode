package com.zbl.mapper;

import com.zbl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zbl
 * @Date: 17:28 2020/3/1
 * @Description:
 */
@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);

    List<User> findAll();

    void updateIsSystem(@Param("userId") Long userId,
                @Param("isSystem") boolean isSystem
                );

    User findById(@Param("userId") Long userId);
}
