package com.github.pbkhyglszy.gymnastics_manager.entity;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
public class FinalScore {
    int id;
    double score;
    int athleteId;
    int groupId;
}
