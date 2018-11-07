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
public class OrderUtils {

	// private final static String uploadPrescriptionUrl =
	// Config.jiuZhouTongTestUrl + "/out/updateDoctorLoginCode?";

	private final static String findOrderinfoUrl = Config.jiuZhouTongTestUrl + "/out/findOrderinfo?";

	public static String findOrderinfo(String orderinfoId, String memberId, String prescriptionId, String startNumber) {
		String method = "/out/findOrderinfo";

		String timestamp = Toolkit.getyyyyMMddHHmmss();
		String signstr = ThirdInterfaceUtils.getMD5signstr(method, timestamp);

		String param = "orderinfoId=" + orderinfoId + "&memberId=" + memberId + "&prescriptionId=" + prescriptionId
				+ "&startNumber=" + startNumber;
		String url = ThirdInterfaceUtils.getURL(findOrderinfoUrl, timestamp, signstr);
		String jsonStr = JiuzhoutongHTTPUtils.sendPostToJiuzhoutong(url, param, "UTF-8");
		return jsonStr;
	}

	// public static void main(String[] args) {
	// String imgUrl =
	// "http://120.197.89.249:9001/medicalmedia/download/patient_13710240423_20170714113422_1500003262084.jpg";
	// String memberId = "3";
	// String patientId = "3";
	// String doctorId = "4";
	// System.out.println(PaymentUtils.uploadPrescription(memberId,
	// patientId, doctorId, imgUrl));
	// }

}
