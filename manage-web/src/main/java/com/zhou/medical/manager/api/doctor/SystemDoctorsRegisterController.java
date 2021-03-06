package com.zhou.medical.manager.api.doctor;

import com.alibaba.fastjson.JSONObject;
import com.zhou.medical.common.config.Constant;
import com.zhou.medical.common.controller.BaseController;
import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.Results;
import com.zhou.medical.common.entity.account.*;
import com.zhou.medical.common.exception.CoderException;
import com.zhou.medical.common.util.DES;
import com.zhou.medical.common.util.MD5;
import com.zhou.medical.common.util.ShuyuanUtils;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.manager.client.account.*;
import easemob.server.api.impl.EasemobIMUsers;
import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生档案管理
 * 
 * @author wsm
 *
 */
@Controller
@RequestMapping("/sys/doctorRegister")
public class SystemDoctorsRegisterController extends BaseController {

	// 输出日志
	private static Logger log = Logger.getLogger(SystemDoctorsRegisterController.class);

	/**
	 * doctorsUserService
	 */
	@Resource
	private DoctorsUserFeignClient doctorsUserFeignClient;

	@Resource
	private DoctorsTeamFeignClient doctorsTeamFeignClient;

	@Resource
	private DoctorsTeamUserLinkFeignClient doctorsTeamUserLinkFeignClient;


	@Resource
	private ContractedHospitalsFeignClient contractedHospitalsFeignClient;

	@Resource
	private ContractedHospitalsDoctorsLinkFeignClient contractedHospitalsDoctorsLinkFeignClient;

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	@SystemControllerLog( "跳转到医生注册审核管理页面")
	public String manager() {
		return "/doctor/doctorRegister";
	}

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/findUserPage")
	@ResponseBody
	@SystemControllerLog( "查找医生注册审核信息")	
	public Map<String, Object> findUserPage(HttpServletResponse response, Integer status, String keyword, String hospitalId2,
                                            @RequestParam(value = "page", defaultValue = "1") Long page,
                                            @RequestParam(value = "rows", defaultValue = "10") Long rows) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (StringUtils.isNotBlank(keyword)) {
				//java : 字符解码 
				keyword = java.net.URLDecoder.decode(keyword,"UTF-8"); 
				System.out.println("1111111111111:: "+keyword);
				map.put("keyword", keyword);
				Pager<DoctorsUser> systemUserPage = new Pager<DoctorsUser>();
				if (StringUtils.isNotBlank(hospitalId2)&&!"null".equalsIgnoreCase(hospitalId2)) {
					map.put("hospitalId", hospitalId2);
					systemUserPage = doctorsUserFeignClient.findPage("selectKeyWordBySys2", getPageIndex(page),
							getPageSize(rows), map);
				}else {
					systemUserPage = doctorsUserFeignClient.findPage("selectKeyWordBySys", getPageIndex(page),
							getPageSize(rows), map);
				}
				
				map.put("total", systemUserPage.getTotalCount());
				map.put("rows", systemUserPage.getList());
			} else {
				map.put("status", status);
				Pager<DoctorsUser> systemUserPage = new Pager<DoctorsUser>();
				if (StringUtils.isNotBlank(hospitalId2)&&!"null".equalsIgnoreCase(hospitalId2)) {
					map.put("hospitalId", hospitalId2);
					systemUserPage = doctorsUserFeignClient.findPage("selectStatusBySys2", getPageIndex(page),
							getPageSize(rows), map);
				}else {
					systemUserPage = doctorsUserFeignClient.findPage("selectStatusBySys", getPageIndex(page),
							getPageSize(rows), map);
				}
				
				map.put("total", systemUserPage.getTotalCount());
				map.put("rows", systemUserPage.getList());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("total", 0);
			map.put("rows", "");
		}

		return map;
	}

	/**
	 * 详情医生注册审批页
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryPage")
	@SystemControllerLog( "跳转到医生注册审核详情页面")	
	public String queryPage(int id, Model model) {
		String[] certificateUrls=null;
		try {
			DoctorsUser doctorUser = doctorsUserFeignClient.findById("selectByPrimaryKey", id);
			model.addAttribute("doctorRecord", doctorUser);
			String certUrl = doctorUser.getCertificateUrl();
			if (StringUtils.isNotBlank(certUrl)&&certUrl.contains(",")) {
				certificateUrls = certUrl.split(",");
			}else if (StringUtils.isNotBlank(certUrl)&&!certUrl.contains(",")) {
				certificateUrls =new String[]{certUrl};
			}
			model.addAttribute("certificateUrls", certificateUrls);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/doctor/doctorRegisterQuery";
	}

	/**
	 * 编辑医生档案页
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	@SystemControllerLog( "跳转到医生注册审核管理编辑页面")	
	public String editPage(int id, Model model) {
		try {
			DoctorsUser doctorUser = doctorsUserFeignClient.findById("selectByPrimaryKey", id);
			model.addAttribute("doctorRecord", doctorUser);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/doctor/doctorRecordEdit";
	}

	/**
	 * 编辑医生档案
	 * @param doctorsUser_
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	@SystemControllerLog( "医生注册审核管理编辑")	
	public Results<Map<String, Object>> edit(DoctorsUser doctorsUser_, HttpServletRequest request) {
		Results<Map<String, Object>> result = new Results<Map<String, Object>>();
		try {
			DoctorsUser doctorsUser = new DoctorsUser();
			doctorsUser.setId(doctorsUser_.getId());
			doctorsUser.setName(doctorsUser_.getName());
			try {
				doctorsUser.setPassword(DES.encryptDESBeas64(Constant.password));
			} catch (CoderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doctorsUser.setPhone(doctorsUser_.getPhone());
			doctorsUser.setSex(doctorsUser_.getSex());
			doctorsUser.setTitle(doctorsUser_.getTitle());
			doctorsUser.setHospital(doctorsUser_.getHospital());
			doctorsUser.setHeadUrl(doctorsUser_.getHeadUrl());
			doctorsUser.setCertificateUrl(doctorsUser_.getCertificateUrl());
			doctorsUser.setDepartment(doctorsUser_.getDepartment());
			doctorsUser.setDepartmentTelephone(doctorsUser_.getDepartmentTelephone());
			doctorsUser.setIntroduction(doctorsUser_.getIntroduction());
			doctorsUser.setProvince(doctorsUser_.getProvince());
			doctorsUser.setCityName(doctorsUser_.getCityName());
			doctorsUser.setArea(doctorsUser_.getArea());
			// doctorsUser.setStartWorkingTime(doctorsUser_.getStartWorkingTime());
			doctorsUser.setRecommended(doctorsUser_.getRecommended());
			// doctorsUser.setGraphicConsulting(doctorsUser_.getGraphicConsulting());
			doctorsUser.setUpdatetime(new Date(System.currentTimeMillis()));
			doctorsUser.setIsVideoConsultation(doctorsUser_.getIsVideoConsultation());
			doctorsUser.setEasemobUuid(doctorsUser_.getEasemobUuid());
			System.out.println("---------------成功------");

			result.setCode(MessageCode.CODE_200);
			result.setMessage("成功");
			return result;
		} catch (RuntimeException e) {
			log.error("修改医生档案失败：{}", e);
			result.setCode(MessageCode.CODE_404);
			result.setMessage(e.getMessage());
			return result;
		}
	}

	/**
	 * 拒绝医生注册
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/reject")
	@ResponseBody
	@SystemControllerLog( "医生注册审核管理拒绝通过")
	public Results<Map<String, Object>> reject(int id) {
		Results<Map<String, Object>> result = new Results<Map<String, Object>>();
		try {
			DoctorsUser doctorUser = doctorsUserFeignClient.findById("selectByPrimaryKey", id);
			doctorUser.setStatus(2);
			doctorsUserFeignClient.update("updateByPrimaryKey", doctorUser);
			ShuyuanUtils.sendRigsterMsg(doctorUser.getName(), doctorUser.getPhone(), 2);
			System.out.println("成功拒绝: " + doctorUser.getName());
			result.setCode(MessageCode.CODE_200);
			result.setMessage("成功");
			return result;
		} catch (Exception e) {
			log.error("失败：{}", e);
			result.setCode(MessageCode.CODE_404);
			result.setMessage(e.getMessage());
			return result;
		}
	}

	/**
	 * 
	 * @Title: pass @Description: TODO @param @param id @param @return
	 *         设定文件 @return Results<Map<String,Object>> 返回类型 @throws
	 */
	@RequestMapping("/pass")
	@ResponseBody
	@SystemControllerLog( "医生注册审核管理通过审核")
	public Results<Map<String, Object>> pass(int id, Model model) {
		Results<Map<String, Object>> result = new Results<Map<String, Object>>();
		try {
			DoctorsUser doctorUser = doctorsUserFeignClient.findById("selectByPrimaryKey", id);

			/**
			 * 先创建环信用户
			 */
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			RegisterUsers users = new RegisterUsers();
			User user = new User().username(doctorUser.getLoginId()).password(Constant.password);// 环信测试帐号
			users.add(user);
			Object resultEasemob = easemobIMUsers.createNewIMUserSingle(users);
			if (resultEasemob == null) {// 环信用户创建失败
				result.setCode(MessageCode.CODE_500);
				result.setMessage("环信用户创建失败");
				return result;
			}

			// 解释result
			JSONObject resultObject = JSONObject.parseObject(resultEasemob.toString());
			JSONArray entities = JSONArray.fromObject(resultObject.get("entities"));
			String entity = entities.get(0).toString();
			JSONObject entityObject = JSONObject.parseObject(entity);
			String uuid = (String) entityObject.getString("uuid");
			Nickname nickName = new Nickname();
			nickName.setNickname(doctorUser.getName());
			easemobIMUsers.modifyIMUserNickNameWithAdminToken(doctorUser.getLoginId(), nickName); // 添加昵称

			doctorUser.setStatus(1);
			doctorUser.setEasemobUuid(uuid);
			doctorUser.setPassword(Constant.password);
			doctorUser.setUpdatetime(new java.util.Date());
			doctorsUserFeignClient.update("updateByPrimaryKey", doctorUser);

			// 维护团队表
			Integer doctorsTeamId = null;
			DoctorsTeam doctorsTeam_ = doctorsTeamFeignClient.findByParam("selectByHospitalName",
					doctorUser.getHospital());
			if (null == doctorsTeam_) {
				DoctorsTeam doctorsTeam = new DoctorsTeam();
				doctorsTeam.setName(doctorUser.getHospital());
				doctorsTeam.setCityName(doctorUser.getCityName());
				doctorsTeam.setArea(doctorUser.getArea());
				doctorsTeam.setHospitalPhone(null);
				doctorsTeam.setHospitalAddr(null);
				doctorsTeam.setCreatetime(new Date());
				doctorsTeamFeignClient.insert(doctorsTeam);
				doctorsTeamId = doctorsTeam.getId();
			} else {
				doctorsTeamId = doctorsTeam_.getId();
			}

			// 维护团队成员关系表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctors_phone", doctorUser.getPhone());
			paramMap.put("team_id", doctorsTeamId);
			List<DoctorsTeamUserLink> doctorsTeamUserLinkList = doctorsTeamUserLinkFeignClient.getListByParamMap("selectByTeamidAndPhone", paramMap);
			if (null == doctorsTeamUserLinkList || doctorsTeamUserLinkList.size() <= 0) {
				DoctorsTeamUserLink doctorsTeamUserLink = new DoctorsTeamUserLink();
				doctorsTeamUserLink.setDoctorsLoginId(doctorUser.getLoginId());
				doctorsTeamUserLink.setDoctorsName(doctorUser.getName());
				doctorsTeamUserLink.setDoctorsPhone(doctorUser.getPhone());
				doctorsTeamUserLink.setTeamName(doctorUser.getHospital());
				doctorsTeamUserLink.setIsleader(0);
				doctorsTeamUserLink.setStatus(0);
				doctorsTeamUserLink.setTeamId(doctorsTeamId);
				doctorsTeamUserLink.setCreatetime(new Date());
				doctorsTeamUserLinkFeignClient.insert(doctorsTeamUserLink);
			}

			// 如果是家庭医生，则维护contracted_hospitals表和contracted_hospitals_doctors_link表
			if (2 == doctorUser.getDoctortype()) {
				// 维护就职医院表contracted_hospitals
				ContractedHospitals contractedHospitals_ = contractedHospitalsFeignClient.findByParam("selectByHospitalName", doctorUser.getHospital());
				if (null == contractedHospitals_) {
					ContractedHospitals contractedHospitals = new ContractedHospitals();
					contractedHospitals.setHospitalName(doctorUser.getHospital());
					contractedHospitals.setCityName(doctorUser.getCityName());
					contractedHospitals.setArea(doctorUser.getArea());
					contractedHospitals.setHospitalPhone(null);
					contractedHospitals.setHospitalAddr(null);
					contractedHospitals.setCreatetime(new Date());
					contractedHospitalsFeignClient.insert(contractedHospitals);
				}

				// 维护医院与医生关联表contracted_hospitals_doctors_link
				ContractedHospitals contractedHospitals_2 = contractedHospitalsFeignClient.findByParam("selectByHospitalName", doctorUser.getHospital());
				if (contractedHospitals_2 != null) {
					Map<String, Object> paramMap2 = new HashMap<String, Object>();
					paramMap2.put("hospitalId", contractedHospitals_2.getId());
					paramMap2.put("doctorPhone", doctorUser.getPhone());
					ContractedHospitalsDoctorsLink contractedHospitalsDoctorsLink_ = contractedHospitalsDoctorsLinkFeignClient
							.findByParam("selectByHospitalIdAndDoctorPhone", paramMap2);
					if (null == contractedHospitalsDoctorsLink_) {
						ContractedHospitalsDoctorsLink contractedHospitalsDoctorsLink = new ContractedHospitalsDoctorsLink();
						contractedHospitalsDoctorsLink.setHospitalId(contractedHospitals_2.getId());
						contractedHospitalsDoctorsLink.setDoctorPhone(doctorUser.getHospital());
						contractedHospitalsDoctorsLink.setCreatetime(new Date());
						contractedHospitalsDoctorsLinkFeignClient.insert(contractedHospitalsDoctorsLink);
					}
				}
			}

			ShuyuanUtils.sendRigsterMsg(doctorUser.getName(), doctorUser.getPhone(), 1);
			System.out.println("成功同意: " + doctorUser.getName());
			result.setCode(MessageCode.CODE_200);
			result.setMessage("成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(MessageCode.CODE_500);
			result.setMessage(e.getMessage());
			return result;
		}
	}

	/**
	 *
	 * @Title: rePass @Description: TODO @param @param id @param @return
	 *         设定文件 @return Results<Map<String,Object>> 返回类型 @throws
	 */
	@RequestMapping("/rePass")
	@ResponseBody
	@SystemControllerLog( "医生注册审核管理重新通过审核")
	public Results<Map<String, Object>> rePass(int id, Model model) {
		Results<Map<String, Object>> result = new Results<Map<String, Object>>();
		try {
			DoctorsUser doctorUser = doctorsUserFeignClient.findById("selectByPrimaryKey", id);

			/**
			 * 先创建环信用户
			 */
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			RegisterUsers users = new RegisterUsers();
			User user = new User().username(doctorUser.getLoginId()).password(Constant.password);// 环信测试帐号
			users.add(user);
			Object resultEasemob = easemobIMUsers.createNewIMUserSingle(users);
			if (resultEasemob == null) {// 环信用户创建失败
				result.setCode(MessageCode.CODE_500);
				result.setMessage("环信用户创建失败");
				return result;
			}

			// 解释result
			JSONObject resultObject = JSONObject.parseObject(resultEasemob.toString());
			JSONArray entities = JSONArray.fromObject(resultObject.get("entities"));
			String entity = entities.get(0).toString();
			JSONObject entityObject = JSONObject.parseObject(entity);
			String uuid = (String) entityObject.getString("uuid");
			Nickname nickName = new Nickname();
			nickName.setNickname(doctorUser.getName());
			easemobIMUsers.modifyIMUserNickNameWithAdminToken(doctorUser.getLoginId(), nickName); // 添加昵称

			doctorUser.setStatus(1);
			doctorUser.setEasemobUuid(uuid);
			doctorUser.setPassword(MD5.encode(Constant.password, "UTF-8"));
			doctorUser.setUpdatetime(new java.util.Date());
			doctorsUserFeignClient.update("updateByPrimaryKey", doctorUser);

			// 维护团队表
			Integer doctorsTeamId = null;
			DoctorsTeam doctorsTeam_ = doctorsTeamFeignClient.findByParam("selectByHospitalName",
					doctorUser.getHospital());
			if (null == doctorsTeam_) {
				DoctorsTeam doctorsTeam = new DoctorsTeam();
				doctorsTeam.setName(doctorUser.getHospital());
				doctorsTeam.setCityName(doctorUser.getCityName());
				doctorsTeam.setArea(doctorUser.getArea());
				doctorsTeam.setHospitalPhone(null);
				doctorsTeam.setHospitalAddr(null);
				doctorsTeam.setCreatetime(new Date());
				doctorsTeamFeignClient.insert(doctorsTeam);
				doctorsTeamId = doctorsTeam.getId();
			} else {
				doctorsTeamId = doctorsTeam_.getId();
			}

			// 维护团队成员关系表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctors_phone", doctorUser.getPhone());
			paramMap.put("team_id", doctorsTeamId);
			List<DoctorsTeamUserLink> doctorsTeamUserLinkList = doctorsTeamUserLinkFeignClient.getListByParamMap("selectByTeamidAndPhone", paramMap);
			if (null == doctorsTeamUserLinkList || doctorsTeamUserLinkList.size() <= 0) {
				DoctorsTeamUserLink doctorsTeamUserLink = new DoctorsTeamUserLink();
				doctorsTeamUserLink.setDoctorsLoginId(doctorUser.getLoginId());
				doctorsTeamUserLink.setDoctorsName(doctorUser.getName());
				doctorsTeamUserLink.setDoctorsPhone(doctorUser.getPhone());
				doctorsTeamUserLink.setTeamName(doctorUser.getHospital());
				doctorsTeamUserLink.setIsleader(0);
				doctorsTeamUserLink.setStatus(0);
				doctorsTeamUserLink.setTeamId(doctorsTeamId);
				doctorsTeamUserLink.setCreatetime(new Date());
				doctorsTeamUserLinkFeignClient.insert(doctorsTeamUserLink);
			}

			// 如果是家庭医生，则维护contracted_hospitals表和contracted_hospitals_doctors_link表
			if (2 == doctorUser.getDoctortype()) {
				// 维护就职医院表contracted_hospitals
				ContractedHospitals contractedHospitals_ = contractedHospitalsFeignClient.findByParam("selectByHospitalName", doctorUser.getHospital());
				if (null == contractedHospitals_) {
					ContractedHospitals contractedHospitals = new ContractedHospitals();
					contractedHospitals.setHospitalName(doctorUser.getHospital());
					contractedHospitals.setCityName(doctorUser.getCityName());
					contractedHospitals.setArea(doctorUser.getArea());
					contractedHospitals.setHospitalPhone(null);
					contractedHospitals.setHospitalAddr(null);
					contractedHospitals.setCreatetime(new Date());
					contractedHospitalsFeignClient.insert(contractedHospitals);
				}

				// 维护医院与医生关联表contracted_hospitals_doctors_link
				ContractedHospitals contractedHospitals_2 = contractedHospitalsFeignClient
						.findByParam("selectByHospitalName", doctorUser.getHospital());
				if (contractedHospitals_2 != null) {
					Map<String, Object> paramMap2 = new HashMap<String, Object>();
					paramMap2.put("hospitalId", contractedHospitals_2.getId());
					paramMap2.put("doctorPhone", doctorUser.getPhone());
					ContractedHospitalsDoctorsLink contractedHospitalsDoctorsLink_ = contractedHospitalsDoctorsLinkFeignClient
							.findByParam("selectByHospitalIdAndDoctorPhone", paramMap2);
					if (null == contractedHospitalsDoctorsLink_) {
						ContractedHospitalsDoctorsLink contractedHospitalsDoctorsLink = new ContractedHospitalsDoctorsLink();
						contractedHospitalsDoctorsLink.setHospitalId(contractedHospitals_2.getId());
						contractedHospitalsDoctorsLink.setDoctorPhone(doctorUser.getHospital());
						contractedHospitalsDoctorsLink.setCreatetime(new Date());
						contractedHospitalsDoctorsLinkFeignClient.insert(contractedHospitalsDoctorsLink);
					}
				}
			}

			ShuyuanUtils.sendRigsterMsg(doctorUser.getName(), doctorUser.getPhone(), 1);
			System.out.println("成功同意: " + doctorUser.getName());
			result.setCode(MessageCode.CODE_200);
			result.setMessage("成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(MessageCode.CODE_500);
			result.setMessage(e.getMessage());
			return result;
		}
	}
}
