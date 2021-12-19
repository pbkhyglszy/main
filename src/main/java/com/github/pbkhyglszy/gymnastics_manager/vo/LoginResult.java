package com.github.pbkhyglszy.gymnastics_manager.vo;

import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResult {
    String token;
    String userName;
    String name;
    MemberType userType;
}
