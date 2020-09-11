package com.xlc.atcrowdfunding.manager.service;

import java.util.Map;

import com.xlc.atcrowdfunding.bean.User;
import com.xlc.atcrowdfunding.utils.Page;

public interface UserService {

	//登录功能
	User queryUserLogin(Map<String, Object> map);

	//查询分页
	// Page queryPage(Integer pageno, Integer pagesize);

	//添加用户
	int saveUser(User user);

	//查询分页和模糊查询
	Page queryPage(Map<String, Object> map);

	//根据id查询用户
	User findByUserId(Integer id);

	//修改用户信息
	int updateUser(User user);

	//根据id删除用户
	int removeUser(Integer id);

	//批量删除用户
	int removeBatchUser(Integer[] ids);





	

}