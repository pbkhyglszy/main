package com.github.pbkhyglszy.gymnastics_manager.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("male")
    MALE(0),
    @JsonProperty("female")
    FEMALE(1),
    ;


    private static final Gender[] VALUES = values();
    private final int value;
    Gender(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static Gender fromValue(int value) {
        return VALUES[value];
    }

}
