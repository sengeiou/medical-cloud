package com.zhou.medical.manager.web;

import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description：登录退出
 * @author：wncheng
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/sys")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public String index(@PathVariable("id") Integer id){
        System.out.println("get"+id);
        return "redirect:/sys/index.action";
    }
    
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "",method=RequestMethod.GET)
    public String index(HttpServletRequest request) {
    	Object obj = request.getSession().getAttribute("systemUser");
    	if(obj!=null){
    		return "redirect:/sys/index.action";
    	}else {
    		return "redirect:/sys/login.action";
    	}
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "/sys/main";
    }

    /**
     * GET 登录
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        return "sys/login";
    }
    
    /**
     * 登出
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Results<String> logout(Model model, HttpServletRequest request) {
    	request.getSession().removeAttribute("systemUser");
    	Results<String> results = new Results<String>();
    	results.setCode(MessageCode.CODE_200);
    	results.setMessage(MessageCode.MESSAGE_200);
        return results;
    }

}
