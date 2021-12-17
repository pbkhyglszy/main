package com.github.pbkhyglszy.gymnastics_manager.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    /**
     * this password is encoded!
     * @param id id of the user to operate
     * @param password the encoded password, see LoginService.java
     */
    void setPassword(int id, String password);

    String getPassword(int id);

    /**
     *
     * @param username 要查找的用户名
     * @return 用户名对应的Id 或Null 若未查找到对应用户
     */
    Integer getIdByUsername(String username);
}
