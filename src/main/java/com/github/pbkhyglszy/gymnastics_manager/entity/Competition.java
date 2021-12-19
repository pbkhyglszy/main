package com.github.pbkhyglszy.gymnastics_manager.entity;

import com.github.pbkhyglszy.gymnastics_manager.enums.CompetitionType;
import com.github.pbkhyglszy.gymnastics_manager.enums.Gender;
import lombok.Data;

/**
 * id 这一组比赛的id
 * type 初赛/决赛
 * ageClassId 属于哪个年龄组
 * eventId 属于哪种比赛(如 男子单杠)
 */
@Data
public class Competition {
    int id;
    CompetitionType type;
    int ageClassId;
    int eventId;
}
