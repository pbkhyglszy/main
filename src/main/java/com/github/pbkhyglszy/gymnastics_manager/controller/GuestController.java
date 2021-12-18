package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.service.CompetitionService;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
