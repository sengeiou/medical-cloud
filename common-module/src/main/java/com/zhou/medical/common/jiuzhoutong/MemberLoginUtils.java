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
public class MemberLoginUtils {

//	private final static String menberLoginUrl =Config.jiuZhouTongTestUrl+"/out/memberLogin?";
	private final static String menberLoginUrl =Config.jiuZhouTongTestUrl+"/out/memberLogin?";

	private final static String isMemberCode = Config.jiuZhouTongTestUrl+"/out/isMemberCode?";
	
	/**
	 * 会员短信发送接口
	* @Title: memberLogin 
	* @Description: TODO 
	* @param @param phone
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String memberLogin(String phone) {
		String method = "/out/memberLogin";
		
		String timestamp = Toolkit.getyyyyMMddHHmmss();
		String signstr= ThirdInterfaceUtils.getMD5signstr(method, timestamp);
		
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("phone", phone);
		String param = "phone="+phone;
		
		String url = ThirdInterfaceUtils.getURL(menberLoginUrl, timestamp, signstr);
		String jsonStr = JiuzhoutongHTTPUtils.sendPostToJiuzhoutong(url, param,"UTF-8");
		return jsonStr;
	}
	
	public static void main(String[] args) {
//		System.out.println(MemberLoginUtils.memberLogin("15876587133"));
		System.out.println(MemberLoginUtils.isMemberCode("15876587133","443185"));
	}


	/**
	 * 会员登陆验证接口
	* @Title: isMemberCode 
	* @Description: TODO 
	* @param @param phone
	* @param @param code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String isMemberCode(String phone, String code) {
		String method = "/out/isMemberCode";
		
		String timestamp = Toolkit.getyyyyMMddHHmmss();
		String signstr= ThirdInterfaceUtils.getMD5signstr(method, timestamp);
		
		String param = "phone="+phone+"&code="+code;
		
		String url = ThirdInterfaceUtils.getURL(isMemberCode, timestamp, signstr);
		String jsonStr = JiuzhoutongHTTPUtils.sendPostToJiuzhoutong(url, param,"UTF-8");
		return jsonStr;
	}

}
