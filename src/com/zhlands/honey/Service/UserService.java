package com.zhlands.honey.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhlands.honey.Dao.Users;
import com.zhlands.honey.Module.User;

@Component
@Transactional(readOnly = false)
public class UserService {
    @Autowired
    private Users userDao;
    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;
    
    public User getById(Integer fd_id)
    {
        return userDao.getById(fd_id);
    }
    
    public User getByMobile(String mobile)
    {
        return userDao.getByMobile(mobile);
    }
    
    @SuppressWarnings("unchecked")
	public User userLogin(User user)
    {
    	User chkUser = new User();
    	
		ValueOperations<String, User> valueops = redisTemplate.opsForValue();
		chkUser = valueops.get(user.getLoginName());
		
		if (chkUser!=null)
		{
			if (chkUser.getPassword().equals(user.getPassword()))
			{
				return chkUser;
			}
		}
		
	    chkUser = userDao.userLogin(user);
	    
	    if (chkUser!=null)
	    {
	        ValueOperations<String, User> valueops1 = redisTemplate.opsForValue();
	        valueops1.set(user.getLoginName(), chkUser);
	    }
        
        return chkUser;
    }
    
    @SuppressWarnings("unchecked")
	public User save(User user) throws Exception
    {
    	userDao.save(user);
    	
        ValueOperations<String, User> valueops = redisTemplate.opsForValue();
        valueops.set(user.getName(), user);
        
    	return user;
    }
}
