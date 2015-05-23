<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Full Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
	<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'控制区'" style="width:150px;padding:10px;">
		<ul id="treeMenu" class="easyui-tree" data-options="url:'/menu/getall.do',animate:true" ></ul>
	</div>
	<!-- 
	<div data-options="region:'east',split:true,collapsed:true,title:'east'" style="width:100px;padding:10px;"></div>
	 -->
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
	<table id="dg" class="easyui-datagrid" title="" style="width:auto;height:auto" 
	        fit="true"
	        pagination="true"
	        pageSize="20"
	        pageList="[10, 20, 50]"
	        idField="fdId"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb,#search',
				url: '/forecast/getall.do',
				method: 'post',
				onClickRow: onClickRow
			">
		<thead>
			<tr>
			    <th field='fdId' width=100>记录时间</th>
				<th field='fdTime' width=100 editor="{type:'validatebox',options:{required:true}}">记录时间</th>
				<!-- 
				<th data-options="field:'fdCityId',width:100,
						formatter:function(value,row){
							return row.fdCityName;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'fdCityId',
								textField:'fdCityName',
								method:'post',
								url:'/city/getall.do',
								required:true
							}
						}">Product</th> -->
				<th field='fdCityId' width=80 editor="{type:'validatebox',options:{required:true}}">城市代码</th>
				<th field='fdCityName' width=80 editor="{type:'validatebox',options:{required:true}}">城市名称</th>
				<th field='fdType' width=80 align='right' editor="{type:'combobox',options:{valueField:'id',textField:'name',panelHeight:'auto',data:[{id:'pm25',name:'pm25'},{id:'pm10',name:'pm10'}],required:true,editable:false}}">数据种类</th>
				<th field='fdLevel' width=50 editor="{type:'numberbox',options:{min:1,max:8,precision:0,required:true}}">等级</th>
				<th field='properity' width=60 align='center' editor="{type:'textbox'}">其他属性</th>
			</tr>
		</thead>
	</table>
	<div id="search" style="padding:3px">
		<span>城市名称:</span>
		<input id="fdCityName" style="line-height:26px;border:1px solid #ccc">
		<span>数据种类:</span>
		<input id="fdType" style="line-height:26px;border:1px solid #ccc">
		<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>
	</div>
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a> -->
	</div>
	
	<script type="text/javascript">
		var editIndex = undefined;
		
		function doSearch(){
			$('#dg').datagrid('load',{
				fdCityName: $('#fdCityName').val(),
				fdType: $('#fdType').val()
			});
		}
		
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				//var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'fdId'});
				//var productname = $(ed.target).combobox('getText');
				//$('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function accept(){
			if (endEditing()){
				if ($('#dg').datagrid('getChanges').length) {
                    var inserted = $('#dg').datagrid('getChanges', "inserted");
                    var deleted = $('#dg').datagrid('getChanges', "deleted");
                    var updated = $('#dg').datagrid('getChanges', "updated");
                    
                    var effectRow = new Object();
                    if (inserted.length) {
                        effectRow["inserted"] = inserted;
                    }
                    if (deleted.length) {
                        effectRow["deleted"] = deleted;
                    }
                    if (updated.length) {
                        effectRow["updated"] = updated;
                    }
                    
                    $.ajax({            
                        type:"POST",   //post提交方式默认是get
                        cache: false,
                        url:"/forecast/commit.do",
                        data:JSON.stringify(effectRow),
                        datatype:"json",
                        contentType:"application/json;charset=UTF-8",
                        error:function(request) {      // 设置表单提交出错
                        	$.messager.alert("提示", "提交错误了！");
                        	$.messager.alert(html(request));
                        },
                        success:function(response) {
                        	if(rsp.status){
                                $.messager.alert("提示", "提交成功！");
                                $('#dg').datagrid('acceptChanges');
                            }
                        }
                    });
                }
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
	</script>
	</div>
</body>
</html>