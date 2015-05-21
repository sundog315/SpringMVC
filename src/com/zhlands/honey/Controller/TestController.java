package com.zhlands.honey.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhlands.honey.auth.*;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	@AuthPassport
	@RequestMapping(value = "/index.do")
	public String index(
    		HttpServletRequest request,
    		HttpServletResponse response) throws Exception
    		{
				return "main.jsp";
    		}
}
