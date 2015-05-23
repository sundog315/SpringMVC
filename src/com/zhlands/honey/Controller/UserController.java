package com.zhlands.honey.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zhlands.honey.Module.User;
import com.zhlands.honey.Service.UserService;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes("userLoginName")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register.do")
    public String save(@RequestParam("fd_name")String fd_name,
    		@RequestParam("fd_email")String fd_email,
    		@RequestParam("fd_loginname")String fd_loginname,
    		@RequestParam("fd_mobile")String fd_mobile,
    		@RequestParam("fd_password")String fd_password,
    		HttpServletRequest request)
    {
    	System.out.println(request);
    	User user = new User();
    	user.setName(fd_name);
    	user.setEmail(fd_email);
    	user.setLoginName(fd_loginname);
    	user.setMobile(fd_mobile);
    	user.setPassword(fd_password);
    	user.setStatus("1");
    	user.setToken("0");
    	user = userService.save(user);
    	
        System.out.println(user.getId());
    	return "/main";
    }
	
	@RequestMapping(value = "/login.do")
	@ResponseBody
    public User userLogin(@RequestParam("fd_loginName")String fd_loginName,
    		@RequestParam("fd_password")String fd_password,
    		Model model,
    		HttpServletRequest request,
    		HttpServletResponse response) throws Exception
    {
    	User user = new User();
    	user.setLoginName(fd_loginName);
    	user.setPassword(fd_password);
    	user = userService.userLogin(user);
    	
    	if (user != null)
    	{
    		System.out.println(user);
    		model.addAttribute("userLoginName", user.getLoginName());
    		return user;
    	}
    	else
    	{
    		System.out.println("login in failed. LoginName is :"+fd_loginName+", Password is :"+fd_password);
    		return null;
    	}
    }
}

