package com.github.pbkhyglszy.gymnastics_manager.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PermissionType {
    //TODO:types:
    @JsonProperty("root")
    ROOT(0),
    @JsonProperty("admin")
    ADMIN(1),
    @JsonProperty("headReferee")
    HEADREFEREE(2),
    @JsonProperty("referee")
    REFEREE(3),
    @JsonProperty("coach")
    COACH(4),
    @JsonProperty("athlete")
    ATHLETE(5),
    @JsonProperty("guest")
    GUEST(6);

    private static final PermissionType[] VALUES = values();
    private final int value;
    PermissionType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static PermissionType fromValue(int value) {
        return VALUES[value];
    }
}
