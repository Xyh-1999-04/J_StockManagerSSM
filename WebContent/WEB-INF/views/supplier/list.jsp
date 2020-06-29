<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>供应商名称:</label><input id="search-name" class="wu-text" style="width:100px">
            
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="80" align="right">供应商名称:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写供应商名称'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">供应商电话:</td>
                <td><input type="text" name="tel" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写供应商电话'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">联系人:</td>
                <td><input type="text" name="contactName" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写联系人'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">联系人电话:</td>
                <td><input type="text" name="contactPhone" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写联系人电话'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">供应商地址:</td>
                <td><input type="text" name="address" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写供应商地址'" /></td>
            </tr>
            
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden"  name="id"  id="edit-id">
        <table>
            <tr>
                <td width="80" align="right">供应商名称:</td>
                <td><input type="text"  id= "edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写供应商名称'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">供应商电话:</td>
                <td><input type="text"  id="edit-tel" name="tel" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写供应商电话'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">联系人:</td>
                <td><input type="text"  id="edit-contactName"  name="contactName" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写联系人'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">联系人电话:</td>
                <td><input type="text"  id="edit-contactPhone"  name="contactPhone" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写联系人电话'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">供应商地址:</td>
                <td><input type="text"  id="edit-address"   name="address" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写供应商地址'" /></td>
            </tr>
        </table>
    </form>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	
	
	/**
	*  添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					//	$("#add-content").val('');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	/**
	*  修改记录
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你修改的数据!","warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','编辑成功！','info');
					//	$("#add-content").val('');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}

	
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要删除的数据！','info');
					return;
				}
				$.ajax({
					url:'delete',
					dataType:'json',
					type:'post',
					data:{id:item.id},
					success:function(data){
						if(data.type == 'success'){
							$('#data-datagrid').datagrid('clearChecked')=='none'
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加供应商信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//$("#add-form input").val('');
            }
        });
	}
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		//$('#add-form').form('clear');
		var item=$('#data-datagrid').datagrid('getSelected');
		if(item==null || item.length==0){
			$.messager.alert('信息提示','请选择要修改的数据!','info');
			return;
		}//相当于没选择数据
		
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "编辑供应商信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//打开弹窗之前需要做什么事
            	//$("#add-form input").val('');
            	$("#edit-id").val(item.id);
            	$("#edit-name").val(item.name);
            	$("#edit-tel").val(item.tel);
            	$("#edit-address").val(item.address);
            	$("#edit-contactName").val(item.contactName);
            	$("edit-contactPhone").val(item.contactPhone);
            }
        });
	}
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {name:$("#search-name").val()};
		$('#data-datagrid').datagrid('reload',option);
	});
	

	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'name',title:'供应商名称',width:100,sortable:true},
			{ field:'tel',title:'电话',width:100,sortable:true},
			{ field:'contactName',title:'联系人',width:100,sortable:true},
			{ field:'contactPhone',title:'联系人电话',width:100,sortable:true},
			{ field:'address',title:'地址',width:100,sortable:true}
			
		]]
	});
</script>