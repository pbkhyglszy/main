package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.github.pbkhyglszy.gymnastics_manager.enums.PermissionType;
import lombok.Data;

@Data
public class User {
    String username;
    String password;
    PermissionType permission;
    String profession;
}
