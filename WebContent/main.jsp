<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Full Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css">
	<script type="text/javascript" src="/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'控制区'" style="width:150px;padding:10px;">
		<ul id="treeMenu" class="easyui-tree" ></ul>
	</div>
	<!-- 
	<div data-options="region:'east',split:true,collapsed:true,title:'east'" style="width:100px;padding:10px;"></div>
	 -->
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div id="center" data-options="region:'center',title:'Center'" style="width:100%;height:100%;" >
		<div id="tt" class="easyui-tabs" data-options="fit:true" style="width:100%;height:100%;">
			<!-- <div title="Home" closable="false"></div> -->
		</div>
	</div>
	
	<script type="text/javascript">
		$('#treeMenu').tree({     
	        url:'/menu/getall.do',  
	        lines : true,
	        animate:true,
	        onClick : function (node) {  
	                 if (node.url) {  
	                	 addTab(node.text, node.url);
	                 }
	              }  
	    });   
		
		function addTab(title, url){
			if ($('#tt').tabs('exists', title)){
				$('#tt').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				$('#tt').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		}
	</script>
</body>
</html>