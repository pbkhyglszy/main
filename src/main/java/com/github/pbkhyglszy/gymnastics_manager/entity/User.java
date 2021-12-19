package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import lombok.Data;

@Data
public class User {
    String username;
    String password;
    int permission;
    MemberType profession;
    String name;
}
