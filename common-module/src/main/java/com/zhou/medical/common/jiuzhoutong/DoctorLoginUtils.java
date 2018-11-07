package com.zhou.medical.common.jiuzhoutong;


import com.zhou.medical.common.config.Config;
import com.zhou.medical.common.util.ThirdInterfaceUtils;
import com.zhou.medical.common.util.Toolkit;

/**
 * @ClassName:
 * @Description: TODO
 * @author adminstrator
 * @date 2017年4月10日 上午10:05:41
 * 
 */
public class DoctorLoginUtils {

	private final static String doctorLoginUrl = Config.jiuZhouTongTestUrl+"/out/doctorLogin?";

//	private final static String doctorLoginUrl = Constant.URL+"/out/doctorLogin?";
	
	private final static String isDoctorCode = Config.jiuZhouTongTestUrl+"/out/isDoctorCode?";

//	private final static String isDoctorCode = Constant.URL+"/out/isDoctorCode?";
	
	/**
	 * 医生短信发送接口
	* @Title: doctorLogin 
	* @Description: TODO 
	* @param @param phone
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String doctorLogin(String phone) {
		String method = "/out/doctorLogin";
		
		String timestamp = Toolkit.getyyyyMMddHHmmss();
		String signstr= ThirdInterfaceUtils.getMD5signstr(method, timestamp);
		
		String param = "phone="+phone;
		
		String url = ThirdInterfaceUtils.getURL(doctorLoginUrl, timestamp, signstr);
		String jsonStr = JiuzhoutongHTTPUtils.sendPostToJiuzhoutong(url, param,"UTF-8");
		return jsonStr;
	}

	
	public static void main(String[] args) {
		System.out.println(DoctorLoginUtils.doctorLogin("15876587133"));
//		System.out.println(DoctorLoginUtils.isDoctorCode("15876587133","886560"));
	}
	/**
	 * 医生登陆验证接口
	* @Title: isDoctorCode 
	* @Description: TODO 
	* @param @param phone
	* @param @param code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String isDoctorCode(String phone, String code) {
		String method = "/out/isDoctorCode";
		
		String timestamp = Toolkit.getyyyyMMddHHmmss();
		String signstr= ThirdInterfaceUtils.getMD5signstr(method, timestamp);
		
		String param = "phone="+phone+"&code="+code;
		
		String url = ThirdInterfaceUtils.getURL(isDoctorCode, timestamp, signstr);
		String jsonStr = JiuzhoutongHTTPUtils.sendPostToJiuzhoutong(url, param,"UTF-8");
		return jsonStr;
	}

}
