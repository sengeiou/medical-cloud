package com.zhou.medical.orders.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.order.Orders;
import com.zhou.medical.orders.service.IOrdersService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/orders")
@RestController
public class OrdersController {

    @Resource
    IOrdersService ordersService;

    @RequestMapping(value = "findPage")
    Pager<Orders> findPage(@RequestParam("mapperId") String mapperId,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                           @RequestBody Map<String, Object> map) {
        return ordersService.getList(mapperId, page, rows, map);
    }

    @RequestMapping(value = "findById")
    Orders findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id) {
        return ordersService.findById(mapperId, id);
    }

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody Orders orders) {
        return ordersService.insert(orders);
    }

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody Orders orders) {
        return ordersService.update(mapperId, orders);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody Orders orders) {
        return ordersService.delete(mapperId, orders);
    }

}
