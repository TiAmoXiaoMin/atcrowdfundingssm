package com.xlc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xlc.atcrowdfunding.bean.User;
import com.xlc.atcrowdfunding.manager.service.UserService;
import com.xlc.atcrowdfunding.utils.MD5Util;

public class Test {

	public static void main(String[] args) throws Exception {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring*.xml");
		
		UserService userService = ioc.getBean(UserService.class);
		
		
		for (int i = 1; i <= 100; i++) {
			User user = new User();
			user.setLoginacct("test"+i);
			user.setUserpswd(MD5Util.digest("123"));
			user.setUsername("test"+i);
			user.setEmail("TiAmoXiaoMin"+"@xlc.com");
			user.setCreatetime("2017-09-23 14:17:00");
			userService.saveUser(user);
		}
		
	}

}
