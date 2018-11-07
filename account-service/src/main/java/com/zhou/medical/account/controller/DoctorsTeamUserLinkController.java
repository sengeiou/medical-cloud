package com.zhou.medical.account.controller;

import com.zhou.medical.account.service.IDoctorsTeamUserLinkService;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.account.DoctorsTeamUserLink;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctorsTeamUserLink")
public class DoctorsTeamUserLinkController {

    @Resource
    IDoctorsTeamUserLinkService doctorsTeamUserLinkService;

    @RequestMapping(value = "getList")
    List<DoctorsTeamUserLink> getList(@RequestParam("mapperId") String mapperId, @RequestBody DoctorsTeamUserLink doctorsTeamUserLink){
        return doctorsTeamUserLinkService.getList(mapperId,doctorsTeamUserLink);
    }


    @RequestMapping(value = "getListByParamMap")
    List<DoctorsTeamUserLink> getListByParamMap(@RequestParam("mapperId") String mapperId, @RequestBody Map<String, Object> paramMap){
        return doctorsTeamUserLinkService.getList(mapperId,paramMap);
    }


    @RequestMapping(value = "findPage")
    Pager<DoctorsTeamUserLink> findPage(@RequestParam("mapperId") String mapperId,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                        @RequestBody Map<String, Object> map){
        return doctorsTeamUserLinkService.getList(mapperId,page,rows,map);
    }


    @RequestMapping(value = "findById")
    DoctorsTeamUserLink findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id){
        return doctorsTeamUserLinkService.findById(mapperId,id);
    }

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody DoctorsTeamUserLink doctorsTeamUserLink){
        return doctorsTeamUserLinkService.insert(doctorsTeamUserLink);
    }

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId,@RequestBody DoctorsTeamUserLink doctorsTeamUserLink){
        return doctorsTeamUserLinkService.update(mapperId,doctorsTeamUserLink);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId,@RequestBody DoctorsTeamUserLink doctorsTeamUserLink){
        return doctorsTeamUserLinkService.delete(mapperId,doctorsTeamUserLink);
    }

}
