package com.zhou.medical.manager.client.operation;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SensitiveWord;
import com.zhou.medical.manager.client.hystrix.SensitiveWordFeignClientFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "OPERATION-SERVICE",fallbackFactory = SensitiveWordFeignClientFallbackFactory.class,path = "/sensitiveWord")
//@RequestMapping("/sensitiveWord")
public interface SensitiveWordFeignClient {


    @RequestMapping(value = "findPage")
    Pager<SensitiveWord> findPage(@RequestParam("mapperId") String mapperId,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                  @RequestBody Map<String, Object> map);

    @RequestMapping(value = "getList")
    List<SensitiveWord> getList(@RequestParam("mapperId") String mapperId, @RequestBody SensitiveWord sensitiveWord);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody SensitiveWord sensitiveWord);

    @RequestMapping(value = "findById")
    SensitiveWord findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody SensitiveWord sensitiveWord);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SensitiveWord sensitiveWord);

}
