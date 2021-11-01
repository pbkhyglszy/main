package com.github.pbkhyglszy.gymnastics_manager.vo;

import lombok.Data;

@Data
public class R<T> {
    int code;
    String msg;
    T data;
}
