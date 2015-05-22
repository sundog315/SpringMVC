package com.zhlands.honey.Service;

import java.util.List;
import java.util.Map;

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
    
    public List<PmForecast> selectAll(Map<String, Object> pm)
    {
    	return pmForecast.selectAll(pm);
    }
    
    public int updateByPrimaryKey(PmForecast record)
    {
    	return pmForecast.updateByPrimaryKey(record);
    }
    
    public int getCount(Map<String, Object> pm)
    {
    	return pmForecast.getCount(pm);
    }
    
    public void batchExecute(PmForecast[] insert,PmForecast[] update,PmForecast[] delete)
    {
    	if (insert!=null)
    		for (PmForecast x: insert)
    			insert(x);
    	
    	if (update!=null)
    		for (PmForecast x: update)
    			updateByPrimaryKey(x);
    	
    	if (delete!=null)
    		for (PmForecast x: delete)
    			deleteByPrimaryKey(x.getFdId());
    }
}
