package com.zhou.medical.manager.api.system;

import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.Results;
import com.zhou.medical.common.entity.account.DoctorsTeam;
import com.zhou.medical.common.entity.operation.SystemRole;
import com.zhou.medical.common.entity.operation.SystemUser;
import com.zhou.medical.common.util.SecurityUtils;
import com.zhou.medical.common.util.Toolkit;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.manager.client.account.DoctorsTeamFeignClient;
import com.zhou.medical.manager.client.operation.SystemRoleFeignClient;
import com.zhou.medical.manager.client.operation.SystemUserFeignClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 后台客户系统
 *
 * @author luoyizhou
 *
 */
@Controller
@RequestMapping("/sys/systemUser")
public class SystemUserController {

	// 输出日志
	private static Logger log = Logger.getLogger(SystemUserController.class);
	private final static String PWD = "yuejiankang123";

	/**
	 * systemUserService
	 */
	@Resource
	private SystemUserFeignClient systemUserFeignClient;

	@Resource
	private DoctorsTeamFeignClient doctorsTeamFeignClient;

	@Resource
	private SystemRoleFeignClient systemRoleFeignClient;

	/**
	 * 登陆
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param userName
	 * @param userPassword
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	@SystemControllerLog("login")
	public Results<String> login(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response,
								 String userName, String userPassword, String verifyCode) {
		Results<String> results = new Results<String>();
		Integer count = (Integer) request.getSession().getAttribute(userName);
		if (count == null) {
			count = 0;
		}
		if (count >= 3) {
			results.setCode(MessageCode.CODE_501);
			results.setMessage("frozen");
			return results;
		}
		String imgVcode = (String) request.getSession().getAttribute("imgVcode");

		// System.out.println("userName=" + userName);
		// System.out.println("userPassword=" + userPassword);
		if (userName == null || userPassword == null || verifyCode == null) {
			results.setCode(MessageCode.CODE_201);
			results.setMessage("请输入正确用户、密码、验证码");
			return results;
		}

		if (userName.length() >20) {
			results.setCode(MessageCode.CODE_206);
			results.setMessage("用户名长度不能大于20位！");
			return results;
		}
		System.out.println("userName1111111111111111111111:::::"+userName);
		SystemUser systemUser = systemUserFeignClient.findByParamFromSelectByUserName(userName);
		if (systemUser == null) {
			request.getSession().setAttribute(userName, count + 1);
			results.setCode(MessageCode.CODE_204);
			results.setMessage("输入正确用户、密码、验证码");
			return results;
		}
		Date last_editpwd_time = systemUser.getLastEditpwdTime() == null ? systemUser.getCreateTime()
				: systemUser.getLastEditpwdTime();
		long time_dif = (System.currentTimeMillis() - last_editpwd_time.getTime()) / 1000;
		long time_limit = 90 * 24 * 60 * 60;
		if (time_dif > time_limit) {
			results.setCode(MessageCode.CODE_400);
			results.setMessage("密码过期");
			return results;
		}

		try {
			if (systemUser.getUserPassword().equals(userPassword)
					&& imgVcode.equalsIgnoreCase(verifyCode)) {
//				request.getSession().setAttribute("systemUser", systemUser);
				results.setCode(MessageCode.CODE_200);
				results.setMessage("登录成功");
				//shiro认证
				UsernamePasswordToken token = new UsernamePasswordToken(systemUser.getUserName(), userPassword);
				token.setRememberMe(true);
				Subject subject = org.apache.shiro.SecurityUtils.getSubject();
				subject.login(token);
				subject.getSession().setAttribute("systemUser", systemUser);
			} else {
				request.getSession().setAttribute(userName, count + 1);
				results.setCode(MessageCode.CODE_204);
				results.setMessage("输入正确用户、密码、验证码");
			}
		} catch (Exception e) {
			e.printStackTrace();
			results.setCode(MessageCode.CODE_204);
			results.setMessage("登录失败");
		}
		return results;

	}

	/**
	 * 注销登录
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "logout")
	@SystemControllerLog("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("systemUser");
		response.sendRedirect(request.getContextPath() + "/manage/login.jsp");
		return null;
	}

	/**
	 * 登陆后跳转到消息推送list 页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "userGoto")
	@SystemControllerLog("userGoto")
	public String userGoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/msg/list2.htm");
		// return setTilesView("pages/systemPush/list",LAYOUT_STANDARD);
		return null;
	}

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	@SystemControllerLog("addPage")
	public String addPage(Model model) {
		List<DoctorsTeam> doctorsTeamList = doctorsTeamFeignClient.getList("selectAll", new DoctorsTeam());
		model.addAttribute("doctorsTeamList", doctorsTeamList);

		List<SystemRole> systemRoleList = systemRoleFeignClient.getList("selectAll", new SystemRole());
		model.addAttribute("systemRoleList", systemRoleList);

		return "/sys/admin/systemuserAdd";
	}

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	@SystemControllerLog("manager")
	public String manager() {
		return "/sys/admin/systemuser";
	}

	/**
	 * 平台新增系统用户
	 *
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	@SystemControllerLog("add")
	@HystrixCommand(fallbackMethod = "processHystrix_Add")
	public Results<String> add(HttpServletRequest request, SystemUser systemUser, String userName) {
		Results<String> results = new Results<String>();
		if (systemUser.getUserName().length() > 20 || systemUser.getUserName().length() < 4) {
			results.setCode(MessageCode.CODE_501);
			results.setMessage("用户名长度只能大于4位且小于20位！");
			return results;
		}

		SystemUser _systemUser = systemUserFeignClient.findByParamFromSelectByUserName(systemUser.getUserName());
		if (_systemUser != null) {
			results.setMessage("用户名已存在!");
			results.setCode(MessageCode.CODE_405);
			return results;
		}
		try {
			systemUser.setUserPassword(SecurityUtils.encryptMD5(PWD));
			SystemUser user = (SystemUser) request.getSession().getAttribute("systemUser");
			systemUser.setCreateTime(new Date());
			if (systemUser.getUserRole() != 4) {// 医疗机构组织
				systemUser.setHospitalId(null);
				systemUser.setHospitalName(null);
			}
			systemUser.setCreateUser(user.getUserRealname());
			systemUser.setCreateUserid(user.getId());
			systemUser.setIsEnable(1);
			systemUserFeignClient.insert(systemUser);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("添加成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@SystemControllerLog("delete")
	public Results<Map<String, Object>> delete(int id) {
		Results<Map<String, Object>> result = new Results<Map<String, Object>>();
		try {
			SystemUser systemUser = systemUserFeignClient.findByParamFromSelectByPrimaryKey(id);
			if (systemUser != null) {
				systemUserFeignClient.delete("deleteByPrimaryKey", systemUser);
			}

			result.setCode(MessageCode.CODE_200);
			result.setMessage("成功");
		} catch (Exception e) {
			result.setCode(MessageCode.CODE_404);
			result.setMessage(e.getMessage());
			return result;
		}
		return result;
	}

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/assignRolePage", method = RequestMethod.GET)
	@SystemControllerLog("assignRolePage")
	public String assignRolePage(Integer id, Model model) {
		SystemUser userVo = systemUserFeignClient.findByParamFromSelectByPrimaryKey(id);
		model.addAttribute("user", userVo);

		List<SystemRole> systemRoleList = systemRoleFeignClient.getList("selectAll", new SystemRole());
		model.addAttribute("systemRoleList", systemRoleList);

		return "/sys/admin/systemuserAssignRole";
	}

	/**
	 * 角色授权理页
	 *
	 * @return
	 */
	@RequestMapping(value = "/assignRole", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog("assignRole")
	public Results<String> assignRole(SystemUser systemUser) {
		Results<String> results = new Results<String>();
		try {
			systemUserFeignClient.update("updateByPrimaryKeySelective", systemUser);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("添加成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}

	/**
	 * 禁止用户
	 *
	 * @return
	 */
	@RequestMapping(value = "/forbiddenOrEnable", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog("forbiddenOrEnable")
	public Results<String> forbiddenOrEnable(SystemUser systemUser) {
		Results<String> results = new Results<String>();
		try {
			systemUserFeignClient.update("updateByPrimaryKeySelective", systemUser);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("修改成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}

	/**
	 * 重置密码
	 *
	 * @return
	 */
	@RequestMapping(value = "/resetPwdFun", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog("resetPwdFun")
	public Results<String> resetPwdFun(SystemUser systemUser) {
		Results<String> results = new Results<String>();
		try {
			systemUser.setUserPassword(SecurityUtils.encryptMD5(PWD));
			systemUser.setLastEditpwdTime(new Date());
			systemUserFeignClient.update("updateByPrimaryKeySelective", systemUser);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("修改成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}

	/**
	 * 用户查询
	 *
	 * @return
	 */
	@RequestMapping(value = "/findUserPage", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog("findUserPage")
	public Map<String, Object> findUserPage(SystemUser systemUser,
											@RequestParam(value = "page", defaultValue = "1") int page,
											@RequestParam(value = "rows", defaultValue = "10") int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println("systemUser.getUserName():"+systemUser.getUserName());
			Pager<SystemUser> systemUserPage = systemUserFeignClient.getList("findUserPage", page, rows, systemUser);
			map.put("total", systemUserPage.getTotalCount());
			map.put("rows", systemUserPage.getList());
		} catch (Exception e) {
			map.put("total", 0);
			map.put("rows", "");
		}
		return map;
	}

	/**
	 * 修改密码页
	 *
	 * @return
	 */
	@RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
	@SystemControllerLog("editPwdPage")
	public String editPwdPage() {
		return "/sys/admin/userEditPwd";
	}

	/**
	 * 修改用户密码
	 *
	 * @param request
	 * @param oldPwd
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/editUserPwd", method = RequestMethod.POST)
	@ResponseBody
	@SystemControllerLog("editUserPwd")
	public Results<String> editUserPwd(HttpServletRequest request, String userName, String oldPwd, String pwd) {
		Results<String> results = new Results<String>();

		if (pwd.length() < 8) {
			results.setCode(MessageCode.CODE_501);
			results.setMessage("密码长度不能少于8位！");
			return results;
		}

		if (!((Toolkit.isContainNumeric(pwd) && Toolkit.isContainLetter(pwd))
				|| (Toolkit.isContainNumeric(pwd) && Toolkit.isContainSymbol(pwd))
				|| (Toolkit.isContainLetter(pwd) && Toolkit.isContainSymbol(pwd)))) {
			results.setCode(MessageCode.CODE_501);
			results.setMessage("修改失败,密码至少是字母,数字,符号中的2种组合");
			return results;
		}

		SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
		if (systemUser == null) {
			systemUser = systemUserFeignClient.findByParamFromSelectByUserName(userName);
			if (systemUser == null) {
				results.setCode(MessageCode.CODE_501);
				results.setMessage("修改失败,请检查用户名是否正确");
				return results;
			}
		}

		if (!systemUser.getUserPassword().equals(DigestUtils.md5Hex(oldPwd))) {
			results.setCode(MessageCode.CODE_501);
			results.setMessage("修改失败,原密码输入有误");
			return results;
		}

		try {
			systemUser.setUserPassword(DigestUtils.md5Hex(pwd));
			systemUser.setLastEditpwdTime(new Date());
			systemUserFeignClient.update("updateByPrimaryKeySelective", systemUser);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("修改成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}

	/**
	 * 查询角色
	 *
	 * @return
	 */
	@RequestMapping(value="/queryRoleById", method={RequestMethod.POST})
	@ResponseBody
	@SystemControllerLog("queryRoleById")
	public SystemRole queryRoleById(Integer id) throws Exception {
		SystemRole systemRole = systemRoleFeignClient.findById("selectByPrimaryKey", id);
		return systemRole;
	}

}
