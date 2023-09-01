package com.shore.distribution.dao;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.shore.distribution.entity.Users;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsersMapper {
    int insertUser(@Param("users") Users users);
    Users getUserInfo(@Param("users") Users users);
    int updateUserById(@Param("users") Users users);
    int register(@Param("users") Users users);
}
