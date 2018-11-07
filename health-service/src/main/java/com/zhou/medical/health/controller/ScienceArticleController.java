package com.zhou.medical.health.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.health.ScienceArticle;
import com.zhou.medical.health.service.IScienceArticleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/scienceArticle")
public class ScienceArticleController {

    @Resource
    IScienceArticleService scienceArticleService;

    @RequestMapping(value = "getByColumnId")
    List<ScienceArticle> getByColumnId(@RequestParam("mapperId") String mapperId, @RequestParam("columnId") Integer columnId) {
        return scienceArticleService.getList(mapperId, columnId);
    }

    @RequestMapping(value = "getPager")
    Pager<ScienceArticle> getPager(@RequestParam("mapperId") String mapperId,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                   @RequestParam("columnId") Integer columnId) {
        System.out.println("page:"+page+"     rows:"+rows);
        return scienceArticleService.getList(mapperId, page.intValue(), rows.intValue(), columnId);
    }


    @RequestMapping(value = "selectKeyWordBySys")
    Pager<ScienceArticle> selectKeyWordBySys(@RequestParam("mapperId") String mapperId,
                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                             @RequestParam("keyword") String keyword) {
        return scienceArticleService.getList(mapperId, page, rows, keyword);
    }

    @RequestMapping(value = "findById")
    ScienceArticle findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id) {
        return scienceArticleService.findById(mapperId, id);
    }

    @RequestMapping(value = "findByParam")
    ScienceArticle findByParam(@RequestParam("mapperId") String mapperId, @RequestParam("title") String title) {
        return scienceArticleService.findByParam(mapperId, title);
    }

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody ScienceArticle scienceArticle) {
        return scienceArticleService.insert(scienceArticle);
    }

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody ScienceArticle scienceArticle) {
        return scienceArticleService.update(mapperId, scienceArticle);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody ScienceArticle scienceArticle) {
        return scienceArticleService.delete(mapperId, scienceArticle);
    }


}
