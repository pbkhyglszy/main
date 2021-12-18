package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.entity.Coach;
import com.github.pbkhyglszy.gymnastics_manager.entity.TeamMember;
import com.github.pbkhyglszy.gymnastics_manager.enums.PermissionType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    PermissionType getPermission(int id);
    String getProfession(int id);
}
