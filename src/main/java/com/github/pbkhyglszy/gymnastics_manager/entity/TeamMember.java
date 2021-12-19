package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import com.github.pbkhyglszy.gymnastics_manager.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        defaultImpl = TeamMember.class,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Athlete.class, name = "athlete"),
        @JsonSubTypes.Type(value = Coach.class, name = "coach"),
})
@EqualsAndHashCode
public class TeamMember {
    int id;
    int teamId;
    String name;
    String idNumber;
    String phone;
    MemberType type;
    int userId;
    String username;
    String password;
}
