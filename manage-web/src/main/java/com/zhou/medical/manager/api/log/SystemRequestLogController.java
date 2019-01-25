package com.zhou.medical.manager.api.log;

import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.log.entity.RequestLog;
import com.zhou.medical.manager.client.log.RequestLogFeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * 
 * @author zds
 *
 */
@Controller
@RequestMapping("/sys/systemRequestLog")
public class SystemRequestLogController {
	
	@Resource
	private RequestLogFeignClient requestLogFeignClient;
	
	/**
	 * 跳转管理页面
	 * @return
	 */
	@SystemControllerLog("跳转管理页面")
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
    	 return "/sys/admin/syslog";
    }

	/**
	 * 获取请求日志list
	 * @param requestLog
	 * @param createTimeBeforeStr
	 * @param createTimeAfterStr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "findSysLogPage", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog("获取请求日志list")
	public Map<String, Object> findSysLogPage(RequestLog requestLog,
											  @RequestParam(value = "createTimeBeforeStr", defaultValue = "1970-01-01 00:00:00") String createTimeBeforeStr ,
											  @RequestParam(value = "createTimeAfterStr", defaultValue = "2025-01-01 00:00:00") String createTimeAfterStr ,
                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "rows", defaultValue = "10") int rows){
		Map<String, Object> map = new HashMap<String, Object>();
		try{

			Pager<RequestLog> pagers = requestLogFeignClient.findSysLogPage(page,rows,createTimeBeforeStr,createTimeAfterStr,requestLog);
			map.put("total", pagers.getTotalCount());
			map.put("rows", pagers.getList());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("total", 0);
			map.put("rows", "");
		} 
		
		return map;
	}
	
	
}
