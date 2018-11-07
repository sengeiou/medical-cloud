package com.zhou.medical.health.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.health.HealthyProducts;
import com.zhou.medical.health.service.IHealthyProductsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/healthyProducts")
public class HealthyProductsController{

    @Resource
    IHealthyProductsService healthyProductsService;

    @RequestMapping(value = "findPage")
    Pager<HealthyProducts> findPage(@RequestParam("mapperId") String mapperId,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                    @RequestBody HealthyProducts healthyProducts){
        return healthyProductsService.getList(mapperId,page,rows,healthyProducts);
    }

    @RequestMapping(value = "findById")
    HealthyProducts findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id){
        return healthyProductsService.findById(mapperId,id);
    }

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody HealthyProducts healthyProducts){
        return healthyProductsService.insert(healthyProducts);
    }

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody HealthyProducts healthyProducts){
        return healthyProductsService.update(mapperId,healthyProducts);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody HealthyProducts healthyProducts){
        return healthyProductsService.delete(mapperId,healthyProducts);
    }

}
