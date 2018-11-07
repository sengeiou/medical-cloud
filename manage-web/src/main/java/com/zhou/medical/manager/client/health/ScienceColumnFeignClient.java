package com.zhou.medical.manager.client.health;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.health.ScienceColumn;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "HEALTH-SERVICE")
@RequestMapping("/scienceColumn")
public interface ScienceColumnFeignClient {

    @RequestMapping(value = "getList")
    List<ScienceColumn> getList(@RequestParam("mapperId") String mapperId, @RequestBody ScienceColumn scienceColumn);


    @RequestMapping(value = "getPager")
    Pager<ScienceColumn> getPager(@RequestParam("mapperId") String mapperId,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                 @RequestBody Map<String, Object> map);

    @RequestMapping(value = "selectKeyWordBySys")
    Pager<ScienceColumn> selectKeyWordBySys(@RequestParam("mapperId") String mapperId,
                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                            @RequestBody ScienceColumn scienceColumn);


    @RequestMapping(value = "findById")
    ScienceColumn findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);


    @RequestMapping(value = "findByParam")
    ScienceColumn findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("title") String title);



    @RequestMapping(value = "insert")
    Integer insert(@RequestBody ScienceColumn scienceColumn);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody ScienceColumn scienceColumn);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody ScienceColumn scienceColumn);
}
