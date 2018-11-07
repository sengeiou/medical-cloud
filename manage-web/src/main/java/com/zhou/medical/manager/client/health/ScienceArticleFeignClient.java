package com.zhou.medical.manager.client.health;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.health.ScienceArticle;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "HEALTH-SERVICE")
@RequestMapping("/scienceArticle")
public interface ScienceArticleFeignClient {


    @RequestMapping(value = "getByColumnId")
    List<ScienceArticle> getByColumnId(@RequestParam("mapperId") String mapperId, @RequestParam("columnId") Integer columnId);

    @RequestMapping(value = "getPager")
    Pager<ScienceArticle> getPager(@RequestParam("mapperId") String mapperId,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                  @RequestParam("columnId") Integer columnId);



    @RequestMapping(value = "selectKeyWordBySys")
    Pager<ScienceArticle> selectKeyWordBySys(@RequestParam("mapperId") String mapperId,
                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                             @RequestParam("keyword") String keyword);

    @RequestMapping(value = "findById")
    ScienceArticle findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "findByParam")
    ScienceArticle findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("title") String title);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody ScienceArticle scienceArticle);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody ScienceArticle scienceArticle);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody ScienceArticle scienceArticle);


}
