package com.github.pbkhyglszy.gymnastics_manager;

import com.github.pbkhyglszy.gymnastics_manager.entity.JwtUtils;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;

import java.util.Map;
import java.util.function.Supplier;

public class LoginUtils {
    public static R<?> validatePermission(String token, int permission, Supplier<R<?>> action) {
        if (!token.startsWith("Bearer ")) {
            return R.error("Token不合法", 401);
        }
        token = token.substring("Bearer ".length());

        int result =  JwtUtils.verifyToken(token);
        if (result == 1) return R.error("Token过期", 401);
        if (result != 0) return R.error("错误的Token", 401);
        Map<String, Object> parse = JwtUtils.parseToken(token);
        if ((int) parse.get("permission") > permission) return R.error("无权限！", 70);

        return action.get();
    }
}
