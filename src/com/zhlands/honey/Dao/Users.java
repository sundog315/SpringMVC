package com.zhlands.honey.Dao;

import com.zhlands.honey.Dao.MyBatisRepository;
import com.zhlands.honey.Module.User;

@MyBatisRepository
public interface Users {
	User getByMobile(String fd_mobile);
	User userLogin(User user);
	void save(User user);
}
