package com.zhou.medical.manager.client.operation;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemBanner;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "OPERATION-SERVICE")
@RequestMapping("/systemBanner")
public interface SystemBannerFeignClient {


    @RequestMapping(value = "findPage")
    Pager<SystemBanner> findPage(@RequestParam("mapperId") String mapperId,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                 @RequestBody SystemBanner systemBanner);

    @RequestMapping(value = "getList")
    List<SystemBanner> getList(@RequestParam("mapperId") String mapperId, @RequestBody SystemBanner systemBanner);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody SystemBanner systemBanner);

    @RequestMapping(value = "findById")
    SystemBanner findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody SystemBanner systemBanner);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SystemBanner systemBanner);

}
