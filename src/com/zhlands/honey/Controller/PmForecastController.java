package com.zhlands.honey.Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhlands.honey.Service.PmForecastService;
import com.zhlands.honey.auth.AuthPassport;
import com.zhlands.honey.Entity.PmForecast;

@Controller
@RequestMapping(value = "/forecast")
public class PmForecastController {
	@Autowired
	private PmForecastService pmService;
	
	@AuthPassport
	@RequestMapping(value = "/getall.do")
	@ResponseBody
    public Map<String, Object> getAll(HttpServletRequest request, HttpServletResponse response,  
            @RequestParam(required = false, defaultValue = "1") Integer page, //第几页  
            @RequestParam(required = false, defaultValue = "10") Integer rows,
            @RequestParam(required = false) String fdCityName,
            @RequestParam(required = false) String fdType) throws IOException
    {
    	Map<String, Object> pm = new HashMap<String, Object>();
        pm.put("pageSize", rows);
        pm.put("pageIndex", (page-1)*rows);
        if ((fdCityName != null) && (!fdCityName.equals("")))
        	pm.put("fdCityName", fdCityName);
        if ((fdType != null) && (!fdType.equals("")))
        	pm.put("fdType", fdType);
        
        List<PmForecast> list = pmService.selectAll(pm);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", pmService.getCount(pm));
        result.put("rows", list);
        
        return result;
    }
	
	@AuthPassport
	@RequestMapping(value = "/commit.do", method= RequestMethod.POST)
	public void commit(@RequestBody Map<String, PmForecast[]> effectRow,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		PmForecast[] inserted = effectRow.get("inserted");
		PmForecast[] updated = effectRow.get("updated");
		PmForecast[] deleted = effectRow.get("deleted");
		
		pmService.batchExecute(inserted, updated, deleted);
	}
}

