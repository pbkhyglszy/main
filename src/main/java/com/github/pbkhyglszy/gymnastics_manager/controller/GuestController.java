package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.service.CompetitionService;
import com.github.pbkhyglszy.gymnastics_manager.vo.CompetitionsResult;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GuestController {
    @Autowired
    CompetitionService competitionService;

    @GetMapping("/all-events")
    public R<?> getAllEvents() {
        return R.ok(competitionService.getEvents());
    }

    @GetMapping("/all-age-groups")
    public R<?> getAllAgeGroups() {
        return R.ok(competitionService.getAgeClasses());
    }

    @GetMapping("/all-grouped-competitions")
    public R<?> getAllGroupedCompetitions()
    {
        List<Competition> competitions = competitionService.getCompetitions();
        HashMap<Integer, CompetitionsResult> result= new HashMap<>();
        for (Competition i:competitions) {
            if(!result.containsKey(i.getEventId()))
            {
                result.put(i.getEventId(), new CompetitionsResult(i));
            }
            result.get(i.getEventId()).addGroup(new CompetitionsResult.Entry(i));
        }
        return R.ok(result.values());
    }
}
