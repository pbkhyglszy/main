package com.github.pbkhyglszy.gymnastics_manager.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CompetitionType {
    @JsonProperty("qualification")
    QUALIFICATION(0),
    @JsonProperty("final")
    FINAL(1),
    ;


    private static final CompetitionType[] VALUES = values();
    private final int value;
    CompetitionType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static CompetitionType fromValue(int value) {
        return VALUES[value];
    }

}
