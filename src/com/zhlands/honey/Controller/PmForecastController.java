package com.zhlands.honey.Controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhlands.honey.Service.PmForecastService;
import com.zhlands.honey.Entity.PmForecast;

@Controller
@RequestMapping(value = "/forecast")
public class PmForecastController {
	@Autowired
	private PmForecastService pmService;
	
	@RequestMapping(value = "/getall.do")
	@ResponseBody
    public List<PmForecast> getAll(HttpServletRequest request, HttpServletResponse response)
    {
    	return pmService.selectAll();
    }
}

