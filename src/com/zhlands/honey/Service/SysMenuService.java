package com.zhlands.honey.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhlands.honey.Dao.SysMenuMapper;
import com.zhlands.honey.Module.SysMenu;

@Component
@Transactional(readOnly = false)
public class SysMenuService {
	@Autowired
	private SysMenuMapper sysMenu;
	
    public List<SysMenu> selectAll()
    {
    	return sysMenu.selectAll();
    }
}