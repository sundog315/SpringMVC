package com.zhlands.honey.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhlands.honey.Util.TreeNode;
import com.zhlands.honey.Module.SysMenu;
import com.zhlands.honey.Service.SysMenuService;
import com.zhlands.honey.auth.AuthPassport;

@Controller
@RequestMapping(value = "/menu")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	
	@AuthPassport
	@RequestMapping(value = "/getall.do")
	@ResponseBody
    public List<TreeNode> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		List<TreeNode> tree = new ArrayList<TreeNode>();
        List<SysMenu> list = sysMenuService.selectAll();
        
        for (SysMenu menu: list)
        {
        	if (menu.getParentField()==0)
        	{
        		TreeNode tn = new TreeNode();
        		tn.setId(menu.getFdId());
        		tn.setText(menu.getText());
        		List<TreeNode> sub = SetSubTree(list,menu.getFdId());
        		if (!sub.isEmpty())
        			tn.setChildren(sub);
        		
        		tree.add(tn);
        	}
        }
        
        return tree;
    }
	
	public List<TreeNode> SetSubTree(List<SysMenu> list, Integer parentField)
	{
		List<TreeNode> ltn = new ArrayList<TreeNode>(); 
		
		for (SysMenu menu: list)
        {
			if (menu.getParentField()==parentField)
			{
				TreeNode tn = new TreeNode();
				tn.setId(menu.getFdId());
				tn.setText(menu.getText());
				List<TreeNode> sub = SetSubTree(list,menu.getFdId());
				if (!sub.isEmpty())
					tn.setChildren(sub);
				
				ltn.add(tn);
			}
        }
		
		return ltn;
	}
}
