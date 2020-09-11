package com.xlc.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlc.atcrowdfunding.bean.User;
import com.xlc.atcrowdfunding.manager.service.UserService;
import com.xlc.atcrowdfunding.utils.AjaxResult;
import com.xlc.atcrowdfunding.utils.Page;
import com.xlc.atcrowdfunding.utils.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转至添加页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		
		return "/user/add";
	}
	
	
	/**
	 * 添加功能
	 * @param user
	 * @return
	 */
	@RequestMapping("/doAdd")
	@ResponseBody
	public Object doAdd(User user) {
		
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.saveUser(user);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("添加失败");
			e.printStackTrace();
		}
	
		return result;
	}
	
	/**
	 * 更新功能
	 * 1.跳转修改页面,根据id查询数据
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Map map) {
		
		User user = userService.findByUserId(id);
		map.put("user", user);
		
		return "user/update";
	}
	
	
	/**
	 * 更新
	 * 2.ajax请求进行 根据用户id修改
	 * @param user
	 * @return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Object doUpdate(User user) {
		
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.updateUser(user);
			result.setSuccess(count==1);
			result.setMessage("更新成功");
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("更新数据失败");
			e.printStackTrace();
		}		
		return result;
	}
	
	
	/**
	 * 	异步请求分页查询
	 * 1.跳转至局部刷新的页面
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex() {
	
		
		return "user/index";
	}
	
	
	/**
	 * 异步请求分页查询和模糊查询
	 * @param pageno
	 * @param pagesize
	 * @param queryText
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1")Integer pageno,
			@RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize,String queryText) {
		
		AjaxResult result = new AjaxResult();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pageno", pageno);
		map.put("pagesize", pagesize);
		try {
			if(StringUtil.isNotEmpty(queryText)) {
				if(queryText.contains("%")) {
					queryText = queryText.replaceAll("%", "\\\\%");
				}
				map.put("queryText", queryText); // \%
			}
			Page page = userService.queryPage(map);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("查询失败");	
			e.printStackTrace();
			
		}
		
		return result;
	
	}
	
	
	
	/**
	 * 
	 * 	异步请求分页查询
	 * 	2.ajax请求查询
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	/*@RequestMapping("/index")
	@ResponseBody
	public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1")Integer pageno,
			@RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize) {
		
		AjaxResult result = new AjaxResult();
		
		try {
			Page page = userService.queryPage(pageno,pagesize);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("查询失败");	
			e.printStackTrace();
			
		}
		
		return result;
	
	}*/
	
	
	/**
	 * 同步请求分页查询
	 * @param pageno 当前页
	 * @param pagesize 页面显示个数
	 * @param session
	 * @return
	 */
	/*@RequestMapping("/index")
	public String index(@RequestParam(value = "pageno",required = false,defaultValue = "1")Integer pageno,
			@RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize,Map map) {
		
		Page page = userService.queryPage(pageno,pagesize);
		map.put("page", page);
		
		return "user/index";
	}*/
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeleteUser")
	@ResponseBody
	public Object doDeleteUser(Integer id) {
		
		AjaxResult result = new AjaxResult();
		try {
			int count = userService.removeUser(id);
			result.setSuccess(count==1);
			result.setMessage("删除数据成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("删除数据失败");
			e.printStackTrace();
		}
		
		return result;
	}

	
	/**
	 * 根据id批量删除
	 * @return
	 */
	@RequestMapping("/doDeleteBatch")
	@ResponseBody
	public Object doDeleteBatch(Integer[] id) {
		AjaxResult result = new AjaxResult();
		
		try {
			int count = userService.removeBatchUser(id);
			result.setSuccess(count == id.length);
			result.setMessage("批量删除成功");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("批量删除失败");
			e.printStackTrace();
		}
		
		return result;
	}
	
	}
