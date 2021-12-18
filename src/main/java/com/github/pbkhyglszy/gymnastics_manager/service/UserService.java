package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.enums.PermissionType;
import com.github.pbkhyglszy.gymnastics_manager.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Mapper
    UserMapper userMapper;
    public PermissionType getPermission(int id)
    {
        return userMapper.getPermission(id);
    }
    public String getProfession(int id)
    {
        return userMapper.getProfession(id);
    }
}
