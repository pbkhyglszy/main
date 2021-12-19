package com.github.pbkhyglszy.gymnastics_manager.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 账号系统相关
 */
@Mapper
public interface UserMapper {

    /**
     * check user's permission in table user
     * @param id Userid
     * @return Permission of this user
     */
    int getPermission(int id);
    String getProfession(int id);
}
