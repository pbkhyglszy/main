package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import com.github.pbkhyglszy.gymnastics_manager.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    @Mapper
    UserMapper userMapper;
    public int getPermission(int id)
    {
        return userMapper.getPermission(id);
    }
    public MemberType getProfession(int id)
    {
        return userMapper.getProfession(id);
    }
    public String getName(int id){String ret= userMapper.getName(id);
        if (ret==null)ret="管理员";
        return ret;
    }
}
