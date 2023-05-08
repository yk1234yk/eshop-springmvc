package com.xhxy.eshop.dao;

import com.xhxy.eshop.dao.impl.UserDaoImpl;
import com.xhxy.eshop.entity.User;
import org.junit.Test;

class UserDaoTest {

	@Test
	void addUser() {
		User user = new User();
		user.setUsername("zhangsi");
		user.setPassword("123456");
		user.setEmail("zhangsi@qq.com");
		user.setPhone("1398887");
		UserDao userDao = new UserDaoImpl();
		
	}

}
