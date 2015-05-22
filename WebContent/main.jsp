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
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">west content</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
	<table id="dg" class="easyui-datagrid" title="" style="width:auto;height:auto" fit="true" pagination="true" pageSize="10" pageList="[5,10,15]" 
	        idField="fdId" 
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '/forecast/getall.do',
				method: 'post',
				onClickRow: onClickRow
			">
		<thead>
			<tr>
			    <th data-options="field:'fdId',width:100">记录时间</th>
				<th data-options="field:'fdTime',width:100,editor:'textbox'">记录时间</th>
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
				<th data-options="field:'fdCityId',width:80,editor:'textbox'">城市代码</th>
				<th data-options="field:'fdCityName',width:80,editor:'textbox'">城市名称</th>
				<th data-options="field:'fdType',width:80,align:'right',editor:'textbox'">数据种类</th>
				<th data-options="field:'fdLevel',width:50,editor:'numberbox'">等级</th>
				<th data-options="field:'properity',width:60,align:'center',editor:'textbox'">其他属性</th>
			</tr>
		</thead>
	</table>
 
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a> -->
	</div>
	
	<script type="text/javascript">
		var editIndex = undefined;
		
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

                    /*
                    $.post("/forecast/commit.do", JSON.stringify(effectRow), function(rsp) {
                        if(rsp.status){
                            $.messager.alert("提示", "提交成功！");
                            $('#dg').datagrid('acceptChanges');
                        }
                    }, "JSON").error(function() {
                        $.messager.alert("提示", "提交错误了！");
                    });
                    */
                }
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		/*function getChanges(){
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length+' rows are changed!');
		}*/
		
		//设置分页控件  
	    var p = $('#dg').datagrid('getPager');
	    p.pagination({
	        pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [10, 20, 50],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    }); 
	</script>
	</div>
</body>
</html>