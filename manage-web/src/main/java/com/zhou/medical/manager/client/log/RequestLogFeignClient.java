package com.zhou.medical.manager.client.log;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.log.entity.RequestLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "LOG-SERVICE")
@RequestMapping("/requestLog")
public interface RequestLogFeignClient {

    @RequestMapping(value = "findSysLogPage")
    Pager<RequestLog> findSysLogPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                     @RequestParam(value = "createTimeBeforeStr", defaultValue = "1970-01-01 00:00:00") String createTimeBeforeStr ,
                                     @RequestParam(value = "createTimeAfterStr", defaultValue = "2025-01-01 00:00:00") String createTimeAfterStr ,
                                     @RequestBody RequestLog requestLog);

}
