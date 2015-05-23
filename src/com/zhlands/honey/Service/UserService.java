package com.zhlands.honey.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhlands.honey.Dao.Users;
import com.zhlands.honey.Module.User;

@Component
@Transactional(readOnly = false)
public class UserService {
    @Autowired
    private Users userDao;
    
    public User getByMobile(String mobile)
    {
        return userDao.getByMobile(mobile);
    }
    
    public User userLogin(User user)
    {
        return userDao.userLogin(user);
    }
    
    public User save(User user)
    {
    	userDao.save(user);
    	return user;
    }
}
