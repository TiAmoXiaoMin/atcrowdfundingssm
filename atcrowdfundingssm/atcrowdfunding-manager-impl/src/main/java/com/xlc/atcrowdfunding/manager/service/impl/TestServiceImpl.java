package com.xlc.atcrowdfunding.manager.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlc.atcrowdfunding.manager.dao.TestDao;
import com.xlc.atcrowdfunding.manager.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDao testDao;
	
	public void insert() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "赵今麦");
		testDao.insert(map);
		
	}

}
