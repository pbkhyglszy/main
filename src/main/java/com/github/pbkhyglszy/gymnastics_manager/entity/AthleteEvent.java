package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AthleteEvent {
    int id;
    int athleteId;
    int eventId;
}
