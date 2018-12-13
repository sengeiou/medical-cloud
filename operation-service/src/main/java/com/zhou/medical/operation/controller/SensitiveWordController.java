package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SensitiveWord;
import com.zhou.medical.log.annotation.SystemServiceLog;
import com.zhou.medical.operation.service.ISensitiveWordService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/sensitiveWord")
@RestController
public class SensitiveWordController {

    @Resource
    ISensitiveWordService sensitiveWordService;

    @RequestMapping(value = "findPage")
    @SystemServiceLog( "sensitiveWord-findPage")
    Pager<SensitiveWord> findPage(@RequestParam("mapperId") String mapperId,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                  @RequestBody Map<String, Object> map){
       return sensitiveWordService.getList(mapperId,page,rows,map);
    }

    @RequestMapping(value = "getList")
    @SystemServiceLog("sensitiveWord-getList")
    List<SensitiveWord> getList(@RequestParam("mapperId") String mapperId, @RequestBody SensitiveWord sensitiveWord){
        return sensitiveWordService.getList(mapperId,sensitiveWord);
    }

    @RequestMapping(value = "insert")
    @SystemServiceLog("sensitiveWord-insert")
    Integer insert(@RequestBody SensitiveWord sensitiveWord){
        return sensitiveWordService.insert(sensitiveWord);
    }

    @RequestMapping(value = "findById")
    @SystemServiceLog("sensitiveWord-findById")
    SensitiveWord findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id){
        return sensitiveWordService.findById(mapperId,id);
    }

    @RequestMapping(value = "update")
    @SystemServiceLog("sensitiveWord-update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody SensitiveWord sensitiveWord){
        return sensitiveWordService.update(mapperId,sensitiveWord);
    }

    @RequestMapping(value = "delete")
    @SystemServiceLog("patientApk-delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SensitiveWord sensitiveWord){
        return sensitiveWordService.delete(mapperId,sensitiveWord);
    }

}
