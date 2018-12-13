package com.zhou.medical.manager.api.doctor;


import com.zhou.medical.common.controller.BaseController;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.order.Orders;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.manager.client.orders.OrdersFeignClient;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 医生服务记录
 * 
 * @author zds
 *
 */
@Controller
@RequestMapping("/sys/doctorsOrders")
public class SystemDoctorsOrdersController extends BaseController {

	// 输出日志
	private static Logger log = Logger.getLogger(SystemDoctorsOrdersController.class);

	@Resource
	private OrdersFeignClient ordersFeignClient;

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	@SystemControllerLog("跳转到医生服务记录页面")
	public String manager() {
		return "/doctor/doctorsOrders";
	}

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/findOrdersPage")
	@ResponseBody
	@SystemControllerLog("查找医生服务记录")
	public Map<String, Object> findOrdersPage(HttpServletResponse response, String hospital, String doctorName,
                                              @RequestParam(value = "page", defaultValue = "1") Long page,
                                              @RequestParam(value = "rows", defaultValue = "10") Long rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			if(StringUtils.isNotBlank(hospital)){
				hospital = java.net.URLDecoder.decode(hospital, "UTF-8");// java : 字符解码
			}
			if(StringUtils.isNotBlank(doctorName)){
				doctorName = java.net.URLDecoder.decode(doctorName, "UTF-8");// java : 字符解码
			}
			
			paramMap.put("hospital", hospital);
			paramMap.put("doctorName", doctorName);
			Pager<Orders> ordersPage = ordersFeignClient.findPage("selectDoctorsOrders", getPageIndex(page),
					getPageSize(rows), paramMap);
			map.put("total", ordersPage.getTotalCount());
			map.put("rows", ordersPage.getList());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("total", 0);
			map.put("rows", "");
		}

		return map;
	}
	
	/**
     * 查看详情
     *
     */
    @RequestMapping("/queryOrdersDetails")
    @SystemControllerLog("跳转到医生服务记录详情页面")
    public String queryOrdersDetails(int id, Model model) {

//    	InquiryMessageLog = patientUserService.findById("getOrdersDetails", id);
//        model.addAttribute("patientRecord", patientUser);
    	
    	model.addAttribute("patientLoginId", id);
        
        return "/doctor/doctorsOrdersQuery";
    }
    
//    public String queryPage(int id, Model model) {
//		try {
//			DoctorsUser doctorUser = doctorsUserService.findById("selectByPrimaryKey", id);
//			model.addAttribute("doctorRecord", doctorUser);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "/doctor/doctorRecordQuery";
//	}
	
}
