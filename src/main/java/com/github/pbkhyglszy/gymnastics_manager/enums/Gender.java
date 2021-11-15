package com.github.pbkhyglszy.gymnastics_manager.enums;

public enum Gender {
    MALE(0),
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
