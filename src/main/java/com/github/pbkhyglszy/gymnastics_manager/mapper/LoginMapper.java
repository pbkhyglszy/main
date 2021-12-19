package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.User;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    /**
     * this password is encoded!
     * @param id id of the user to operate
     * @param password the encoded password, see LoginService.java
     */
    int setPassword(int id, String password);

    String getPassword(int id);

    /**
     *
     * @param username 要查找的用户名
     * @return 用户名对应的Id 或Null 若未查找到对应用户
     */
    Integer getIdByUsername(String username);

    /**
     * 将信息直接插入 User表
     */
    //TODO:Mapper
    int createUser(User user);

    int deleteUser(int userId);

    int updateUser(User user);

    int updateUserPassword(User user);
}
