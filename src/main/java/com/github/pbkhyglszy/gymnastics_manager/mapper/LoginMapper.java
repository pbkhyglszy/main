package com.github.pbkhyglszy.gymnastics_manager.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    void setPassword(int id, String password);

    String getPassword(int id);
}
