package com.github.pbkhyglszy.gymnastics_manager.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MemberType {
    @JsonProperty("admin")
    ADMIN(-1),
    @JsonProperty("athlete")
    ATHLETE(0),
    @JsonProperty("coach")
    COACH(1),
    @JsonProperty("referee")
    REFEREE(2),
    @JsonProperty("team_doctor")
    TEAM_DOCTOR(3),
    @JsonProperty("team_leader")
    TEAM_LEADER(4);

    private static final MemberType[] VALUES = values();
    private final int value;
    MemberType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static MemberType fromValue(int value) {
        return VALUES[value];
    }
}
