package com.zhou.medical.manager.client.orders;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.order.Orders;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "ORDERS-SERVICE")
@RequestMapping("/orders")
public interface OrdersFeignClient {

    @RequestMapping(value = "findPage")
    Pager<Orders> findPage(@RequestParam("mapperId") String mapperId,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                           @RequestBody Map<String, Object> map);

    @RequestMapping(value = "findById")
    Orders findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id);

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody Orders orders);

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody Orders orders);

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody Orders orders);

}
