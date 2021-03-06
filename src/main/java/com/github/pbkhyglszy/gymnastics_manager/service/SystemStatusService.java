package com.github.pbkhyglszy.gymnastics_manager.service;

import com.github.pbkhyglszy.gymnastics_manager.entity.Athlete;
import com.github.pbkhyglszy.gymnastics_manager.mapper.RegistrationMapper;
import com.github.pbkhyglszy.gymnastics_manager.mapper.SystemStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemStatusService {
    @Autowired
    SystemStatusMapper systemStatusMapper;
    @Autowired
    RegistrationMapper registrationMapper;

    public boolean openRegistration() {
        if (systemStatusMapper.get("system_status") == 0) {
            systemStatusMapper.update("system_status", 1);
            return true;
        } else return false;
    }

    public boolean closeRegistration() {
        if (systemStatusMapper.get("system_status") == 1) {
            systemStatusMapper.update("system_status", 2);
            updateAthleteId();
            return true;
        } else return false;
    }
    public int getStatus(String key)
    {
        return systemStatusMapper.get(key);
    }
    public void setStatus(String key,int status)
    {
        systemStatusMapper.update(key,status);
    }
    private void updateAthleteId() {
        List<Integer> maleAthleteIds = systemStatusMapper.getMaleAthleteIds();
        List<Integer> femaleAthleteIds = systemStatusMapper.getFemaleAthleteIds();
        int male = 1, female = 0;
        for (int id : maleAthleteIds
        ) {
            systemStatusMapper.updateAthleteId(id, male);
            male += 2;
        }
        for (int id : femaleAthleteIds
        ) {
            systemStatusMapper.updateAthleteId(id, female);
            female += 2;
        }
    }

}
