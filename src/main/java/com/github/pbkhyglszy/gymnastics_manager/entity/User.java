package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    int id;
    String userName;
    String password;
    int permission;
    MemberType profession;
    String name;
}
