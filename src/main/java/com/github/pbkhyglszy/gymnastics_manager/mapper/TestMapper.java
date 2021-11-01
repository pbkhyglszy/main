package com.github.pbkhyglszy.gymnastics_manager.mapper;

import com.github.pbkhyglszy.gymnastics_manager.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Test> getAll();
}
