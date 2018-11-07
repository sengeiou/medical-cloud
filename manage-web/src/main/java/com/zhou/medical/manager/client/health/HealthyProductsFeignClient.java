package com.zhou.medical.manager.client.health;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.health.HealthyProducts;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(name = "HEALTH-SERVICE")
@RequestMapping("/healthyProducts")
public interface HealthyProductsFeignClient {

    @RequestMapping(value = "findPage")
    Pager<HealthyProducts> findPage(@RequestParam("mapperId") String mapperId,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                    @RequestBody HealthyProducts healthyProducts);

    @RequestMapping(value = "findById")
    HealthyProducts findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody HealthyProducts healthyProducts);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId,@RequestBody HealthyProducts healthyProducts);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId,@RequestBody HealthyProducts healthyProducts);

}
