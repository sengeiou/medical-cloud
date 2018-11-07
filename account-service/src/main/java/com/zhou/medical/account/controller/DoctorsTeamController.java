package com.zhou.medical.account.controller;


import com.zhou.medical.account.service.IDoctorsTeamService;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.account.DoctorsTeam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctorTeam")
public class DoctorsTeamController {

    @Resource
    private IDoctorsTeamService doctorsTeamService;

    @RequestMapping(value = "getList")
    public List<DoctorsTeam> getList(String mapperId, @RequestBody DoctorsTeam doctorsTeam) {
        return doctorsTeamService.getList(mapperId, doctorsTeam);
    }

    @RequestMapping(value = "selectByHospitalName")
    List<DoctorsTeam> selectByHospitalName(@RequestParam("mapperId") String mapperId, @RequestParam("hospitalName") String hospitalName) {
        return doctorsTeamService.getList(mapperId, hospitalName);
    }

    @RequestMapping(value = "selectKeyWordBySys")
    Pager<DoctorsTeam> selectKeyWordBySys(@RequestParam("mapperId") String mapperId,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                          @RequestBody Map<String, Object> map) {
        return doctorsTeamService.getList("selectKeyWordBySys", page, rows, map);
    }


    @RequestMapping(value = "findById")
    DoctorsTeam findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id) {
        return doctorsTeamService.findById(mapperId, id);
    }

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody DoctorsTeam doctorsTeam) {
        return doctorsTeamService.insert(doctorsTeam);
    }

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody DoctorsTeam doctorsTeam) {
        return doctorsTeamService.update(mapperId, doctorsTeam);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody DoctorsTeam doctorsTeam) {
        return doctorsTeamService.delete(mapperId, doctorsTeam);
    }

    @RequestMapping(value = "findByParam")
    DoctorsTeam findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("hospitalName") String hospitalName){
        return doctorsTeamService.findByParam(mapperId,hospitalName);
    }

}
