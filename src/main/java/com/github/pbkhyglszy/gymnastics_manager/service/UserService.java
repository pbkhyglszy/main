package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import com.github.pbkhyglszy.gymnastics_manager.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
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
    public String getName(int id){return userMapper.getName(id);}
}
