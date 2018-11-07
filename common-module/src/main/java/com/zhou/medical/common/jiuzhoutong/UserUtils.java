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
public class UserUtils {

	private final static String findMemberLoginCodeUrl = Config.jiuZhouTongTestUrl + "/out/findMemberLoginCode?";

	/**
	 * 3.24 唯一标识查询信息接口 @Title: findMemberLoginCode @Description:
	 * TODO @param @param appUserId @param @return 设定文件 @return String
	 * 返回类型 @throws
	 */
	public static String findMemberLoginCode(String appUserId) {
		String method = "/out/findMemberLoginCode";

		String timestamp = Toolkit.getyyyyMMddHHmmss();
		String signstr = ThirdInterfaceUtils.getMD5signstr(method, timestamp);

		String param = "appUserId=" + appUserId;

		String url = ThirdInterfaceUtils.getURL(findMemberLoginCodeUrl, timestamp, signstr);
		String jsonStr = JiuzhoutongHTTPUtils.sendPostToJiuzhoutong(url, param, "UTF-8");
		return jsonStr;
	}

	public static void main(String[] args) {
		// System.out.println(MemberUserUtils.findMemberPatient("13432947998"));
		// System.out.println(MemberUserUtils.findMember("13432947998", ""));
		// System.out.println(MemberUserUtils.saveMember("15876587133", "吴思铭",
		// "440902199008150812", "1", "18"));
		// System.out.println(MemberUserUtils.updateMember("15876587133", "David
		// Wu", "15876587133", "440902199008150812", "1", "20"));

	}

}
