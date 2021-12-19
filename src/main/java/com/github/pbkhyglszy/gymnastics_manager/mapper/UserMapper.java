package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
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

    /**
     * 从多个表里查询User的职业
     * @param id 用户ID
     * @return 用户类型
     */
    MemberType getProfession(int id);

    /**
     * 从多个表中查询用户名字 若用户为管理员则返回“管理员”
     * @param id 用户ID
     * @return 用户名字
     */
    String getName(int id);
}
