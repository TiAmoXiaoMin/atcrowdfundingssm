package com.xlc.atcrowdfunding.manager.service.impl;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlc.atcrowdfunding.bean.User;
import com.xlc.atcrowdfunding.exception.LoginFailException;
import com.xlc.atcrowdfunding.manager.dao.UserMapper;
import com.xlc.atcrowdfunding.manager.service.UserService;
import com.xlc.atcrowdfunding.utils.Const;
import com.xlc.atcrowdfunding.utils.MD5Util;
import com.xlc.atcrowdfunding.utils.Page;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	//查询用户名和密码(登录)
	public User queryUserLogin(Map<String, Object> map) {

		User user = userMapper.queryUserlogin(map);
		if(user == null) {
			throw new LoginFailException("用户账号和密码不正确!");
		}
		return user;
	}

	
	//查询分页
	/*
	 * public Page queryPage(Integer pageno, Integer pagesize) {
	 * 
	 * Page page = new Page(pageno,pagesize);
	 * 
	 * Integer startIndex = page.getStartIndex();
	 * 
	 * List<User> datas= userMapper.queryListPage(startIndex,pagesize);
	 * 
	 * Integer totalsize = userMapper.queryCont();
	 * 
	 * page.setDatas(datas); page.setTotalsize(totalsize);
	 * 
	 * return page; }
	 */


	//添加用户
	public int saveUser(User user) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(new Date());
		user.setCreatetime(strDate);
		user.setUserpswd(MD5Util.digest(Const.PASSWORD));
		return userMapper.insert(user);
		
	}

	
	//分页查询和模糊查询
	public Page queryPage(Map<String, Object> map) {
		Page page = new Page((Integer)map.get("pageno"),(Integer)map.get("pagesize"));
		Integer startIndex = page.getStartIndex();
		map.put("startIndex", startIndex);
		List<User> list = userMapper.queryListPage(map);
		
		Integer totalsize = userMapper.queryCont(map);
		page.setDatas(list);
		page.setTotalsize(totalsize);
		
		return page;
	}


	//根据id查询用户
	public User findByUserId(Integer id) {
		
		return userMapper.selectByPrimaryKey(id);
	}


	//根据id修改用户信息
	public int updateUser(User user) {
		
		return userMapper.updateByPrimaryKey(user);
	}

	//根据id删除用户
	public int removeUser(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}


	//批量删除用户
	public int removeBatchUser(Integer[] ids) {
		int totalCount = 0;
		for (Integer id : ids) {
			int count = userMapper.deleteByPrimaryKey(id);
			totalCount += count;
		}
		if(totalCount != ids.length) {
			throw new RuntimeException("批量删除失败");
		}
		return totalCount;
	}


}
