package com.shore.distribution.service;

import com.shore.distribution.entity.Users;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UsersService {
    int register(Users users);
    Users login(Users users);
}