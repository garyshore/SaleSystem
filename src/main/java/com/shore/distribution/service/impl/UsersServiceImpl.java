package com.shore.distribution.service.impl;
import com.shore.distribution.dao.UsersMapper;
import com.shore.distribution.service.UsersService;
import com.shore.distribution.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shore.distribution.entity.Users;

import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public int register(Users users){

        //2.判断用户名是否存在，存在则返回已注册，不存在则继续
        if(usersMapper.getUserInfo(users)!=null){ return 0; }

        //3.将Users.pwd进行MD5加密
        String pwd = users.getUserPwd();
        pwd = MD5Util.getMD5(pwd,true,64);
        users.setUserPwd(pwd);

        //4.插入users表
        Date dNow = new Date();
        users.setCreateTime(dNow);
        int reg = usersMapper.insertUser(users);

        //5.获取参数Users.fissionPathway   重新获取自己的userId
        Users u = usersMapper.getUserInfo(users);
        int uId = u.getId();

        //6.判断fissionPathway是否为空，不为空说明有邀请人
        int inviterId = 0;
        String fissionPathway = "";

        if(users.getFissionPathway() != null){
            inviterId = Integer.parseInt(users.getFissionPathway());
            Users inviterUser = new Users();
            inviterUser.setId(inviterId);
            //7.fissionPathway不为空，则查询users表，判断fissionPathway是否正确
            Users inviter = usersMapper.getUserInfo(inviterUser);

            //8.如果fissionPathway不正确，回填自己的id
            if(inviter == null){
                fissionPathway = uId + "";
            }
            else{
                //9.如果fissionPathway正确，回填fission_pathway字段，内容为fissionPathway+"_"+user.id
                fissionPathway = inviter.getFissionPathway();
                fissionPathway = fissionPathway + "_" + uId;
            }
        }
        else{
            //为空说明没有邀请人，直接回填自己的id
            fissionPathway = uId + "";
        }
        users.setId(uId);
        users.setFissionPathway(fissionPathway);
        usersMapper.updateUserById(users);
        //10.返回成功-
        return 0;
    }
    public Users login(Users users)
    {
        String pwd = users.getUserPwd();
        pwd = MD5Util.getMD5(pwd,true,64);
        users.setUserPwd(pwd);
        Users u = usersMapper.getUserInfo(users);
        if(u==null)
            return null;
        return u;
    }

}