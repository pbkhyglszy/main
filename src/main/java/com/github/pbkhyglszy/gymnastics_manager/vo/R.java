package com.github.pbkhyglszy.gymnastics_manager.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class R<T> {
    int code;
    String msg;
    T data;

    public R(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "请求成功！";
    }

    public R(String msg, int code) {
        this.data = null;
        this.code = code;
        this.msg = msg;
    }


    public static <T> R<T> ok(T data) {
        return new R<>(data);
    }
    public static <T> R<T> ok() {
        return new R<>(null);
    }

    public static <T> R<T> error(String msg, int code) {
        return new R<>(msg, code);
    }

    public static <T> R<T> error(Exception e, int code) {
        return new R<>(e.getMessage(), code);
    }
}
