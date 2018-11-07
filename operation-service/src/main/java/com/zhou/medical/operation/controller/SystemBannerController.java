package com.zhou.medical.operation.controller;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.operation.SystemBanner;
import com.zhou.medical.operation.service.ISystemBannerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhouzhou
 */
@RestController
@RequestMapping("/systemBanner")
public class SystemBannerController {

    @Resource
    private ISystemBannerService systemBannerService;


    @RequestMapping(value = "findPage")
    Pager<SystemBanner> findPage(@RequestParam("mapperId") String mapperId,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                 @RequestBody SystemBanner systemBanner){
        return systemBannerService.getList(mapperId,page,rows,systemBanner);

    }

    @RequestMapping(value = "getList")
    List<SystemBanner> getList(@RequestParam("mapperId") String mapperId, @RequestBody SystemBanner systemBanner){
        return systemBannerService.getList(mapperId,systemBanner);
    }

    @RequestMapping(value = "insert")
    Integer insert(@RequestBody SystemBanner systemBanner){
        return systemBannerService.insert(systemBanner);
    }

    @RequestMapping(value = "findById")
    SystemBanner findById(@RequestParam("mapperId") String mapperId, @RequestParam("id") Integer id){
        return systemBannerService.findById(mapperId,id);
    }

    @RequestMapping(value = "update")
    Integer update(@RequestParam("mapperId") String mapperId, @RequestBody SystemBanner systemBanner){
        return systemBannerService.update(mapperId,systemBanner);
    }

    @RequestMapping(value = "delete")
    Integer delete(@RequestParam("mapperId") String mapperId, @RequestBody SystemBanner systemBanner){
        return systemBannerService.delete(mapperId,systemBanner);
    }


}
