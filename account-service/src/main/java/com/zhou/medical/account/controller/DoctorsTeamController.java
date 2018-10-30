package com.zhou.medical.account.controller;


import com.zhou.medical.account.service.IDoctorsTeamService;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.account.DoctorsTeam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/doctorTeam")
public class DoctorsTeamController {

    @Resource
    private IDoctorsTeamService doctorsTeamService;

    @RequestMapping(value = "getList")
    public List<DoctorsTeam> getList(String mapperId,@RequestBody DoctorsTeam doctorsTeam) {
        return doctorsTeamService.getList(mapperId, doctorsTeam);
    }


}
