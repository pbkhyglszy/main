package com.github.pbkhyglszy.gymnastics_manager.controller;

import com.github.pbkhyglszy.gymnastics_manager.LoginUtils;
import com.github.pbkhyglszy.gymnastics_manager.entity.Competition;
import com.github.pbkhyglszy.gymnastics_manager.service.CompetitionService;
import com.github.pbkhyglszy.gymnastics_manager.service.SystemStatusService;
import com.github.pbkhyglszy.gymnastics_manager.service.TeamService;
import com.github.pbkhyglszy.gymnastics_manager.vo.CompetitionsResult;
import com.github.pbkhyglszy.gymnastics_manager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class RefereeController {
    @Autowired
    CompetitionService competitionService;

    @Autowired
    TeamService teamService;

    @Autowired
    SystemStatusService systemStatusService;
    @PostMapping("/qaq2")
    public R<?> testFunc(@RequestHeader("Authorization") String token)
    {
        return LoginUtils.validatePermission(token, 3, () -> {
            try {

            } catch (Exception e) {
                e.printStackTrace();
                return R.error(e.getMessage(), 1);
            }
            return R.ok();
        });
    }
}
