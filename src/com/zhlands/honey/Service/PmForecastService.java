package com.zhlands.honey.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhlands.honey.Dao.PmForecastMapper;
import com.zhlands.honey.Entity.PmForecast;

@Component
@Transactional(readOnly = false)
public class PmForecastService {
    @Autowired
    private PmForecastMapper pmForecast;
    
    public int deleteByPrimaryKey(Long fdId)
    {
        return pmForecast.deleteByPrimaryKey(fdId);
    }
    
    public int insert(PmForecast record) 
    {
        return pmForecast.insert(record);
    }
    
    public PmForecast selectByPrimaryKey(Long fdId)
    {
    	return pmForecast.selectByPrimaryKey(fdId);
    }
    
    public List<PmForecast> selectAll()
    {
    	return pmForecast.selectAll();
    }
    
    public int updateByPrimaryKey(PmForecast record)
    {
    	return pmForecast.updateByPrimaryKey(record);
    }
}
