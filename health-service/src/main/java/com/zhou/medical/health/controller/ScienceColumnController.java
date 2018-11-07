package com.zhou.medical.health.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.health.ScienceColumn;
import com.zhou.medical.health.service.IScienceColumnService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scienceColumn")
public class ScienceColumnController {


    @Resource
    IScienceColumnService scienceColumnService;

    @RequestMapping(value = "getList")
    List<ScienceColumn> getList(@RequestParam("mapperId") String mapperId, @RequestBody ScienceColumn scienceColumn) {
        return scienceColumnService.getList(mapperId, scienceColumn);
    }


    @RequestMapping(value = "getPager")
    Pager<ScienceColumn> getPager(@RequestParam("mapperId") String mapperId,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                  @RequestBody Map<String, Object> map) {
        return scienceColumnService.getList(mapperId, page, rows, map);
    }

    @RequestMapping(value = "selectKeyWordBySys")
    Pager<ScienceColumn> selectKeyWordBySys(@RequestParam("mapperId") String mapperId,
                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                            @RequestBody ScienceColumn scienceColumn) {
        return scienceColumnService.getList(mapperId, page, rows, scienceColumn);
    }


    @RequestMapping(value = "findById")
    ScienceColumn findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id) {
        return scienceColumnService.findById(mapperId, id);
    }


    @RequestMapping(value = "findByParam")
    ScienceColumn findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("title") String title) {
        return scienceColumnService.findByParam(mapperId, title);
    }


    @RequestMapping(value = "insert")
    Integer insert(@RequestBody ScienceColumn scienceColumn) {
        return scienceColumnService.insert(scienceColumn);
    }

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody ScienceColumn scienceColumn){
        return scienceColumnService.update(mapperId,scienceColumn);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody ScienceColumn scienceColumn){
        return scienceColumnService.delete(mapperId,scienceColumn);
    }
}
