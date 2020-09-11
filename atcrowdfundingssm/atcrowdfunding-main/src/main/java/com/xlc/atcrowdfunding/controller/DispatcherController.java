package com.xlc.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlc.atcrowdfunding.bean.User;
import com.xlc.atcrowdfunding.manager.service.UserService;
import com.xlc.atcrowdfunding.utils.AjaxResult;
import com.xlc.atcrowdfunding.utils.Const;
import com.xlc.atcrowdfunding.utils.MD5Util;

@Controller
public class DispatcherController {
	
	
	@Autowired
	private UserService userService;
	

	/**
	 * 跳转主页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		
		return "index";
	}
	
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}

	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping("/reg")
	public String reg() {
		
		return "reg";
	}
	
	
	/**
	 * 	跳转后台页面
	 * @return
	 */
	@RequestMapping("/main")
	public String main() {
		
		return "main";
	}
	
	
	/**
	 * 	注销 重定向到主页 (使用redirect防止表单重复提交)
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();  //销毁session对象或清理session域
		
		return "redirect:/index.htm";
	}
	
	//异步请求登录
	@RequestMapping("/doLogin")
	@ResponseBody
	public Object doLogin(String loginacct,String userpswd,String type,HttpSession session) {
		
		AjaxResult result  = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginacct", loginacct);
			map.put("userpswd", MD5Util.digest(userpswd));
			map.put("type", type);
			User user = userService.queryUserLogin(map);
			session.setAttribute(Const.LOGIN_USER, user);
			result.setSuccess(true);//使用responseBody转化为Json格式 {"success":true}
			
		} catch (Exception e) {
			result.setSuccess(false); //{"success":false,"message":"登录失败"}
			result.setMessage("登录失败"); 
			System.out.println(result.getMessage());
			e.printStackTrace();
		
		}
			
		return result;
	}
	
	
	
	
	//同步请求登录
	/*@RequestMapping("/doLogin")
	public String doLogin(String loginacct,String userpswd,String type,HttpSession session) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginacct", loginacct);
		map.put("userpswd", userpswd);
		map.put("type", type);
		
		User user = userService.queryUserLogin(map);
		
		session.setAttribute(Const.LOGIN_USER, user);
		
		return "redirect:/main.htm";
	}*/
}
