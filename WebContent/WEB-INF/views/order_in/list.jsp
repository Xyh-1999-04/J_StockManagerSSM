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
            <label>商品名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>所属供应商:</label><select id="search-supplier" class="easyui-combobox" 
            	panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            		<c:forEach items="${supplierList}" var="supplier">
            			<option value="${supplier.id}">${supplier.name}</option>
            		</c:forEach>
            	</select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:650px; padding:10px;">
	   <table id="selected-product-datagrid" class="easyui-datagrid" 
	   style="width:620px;height:355px"
		toolbar="#select-product-btn">
	<thead>
		<tr>
			<th field="id" width="155px">商品 ID</th>
			<th field="name" width="155px">商品名称</th>
			<th field="price" width="155px" >商品价格</th>
			<th field="productNum" width="155px" editor="{type:'numberbox',options:{min:1,precision:0}}">商品数量</th>
			
			
		</tr>
	</thead>
</table>
<div id="select-product-btn">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="selectProduct()">添加商品</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeProduct()">删除</a>
	
</div>
</div>
<!-- 选择窗口弹窗 -->
   <div id="show-product-dialog" class="easyui-dialog"
      data-options="closed:true,iconCls:'icon-save'"
      style="width:650px;padding:10px">
      <table id="show-product-datagrid" 
      class="easyui-datagrid" style="width:600px;height:350px"></table>
      </div>
      
<!--       添加第二窗口 -->
    <div id="add-2-dialog" class="easyui-dialog"
    data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
    <form id="add-form" method="post">
    <table >
    <tr>
       <td width="60" align="right">支付方式</td>
       <td>
         <select name="payType" id="add-payType"
         class="easyui-combobox" panelHeight="auto"
         style="width:260px" data-options="required:true,
         missingMessage:'请选择支付方式'">
            <option value="0" selected="selected">现金</option>
            <option value="1">银行转账</option>
            <option value="2">支付宝</option>
            <option value="3">微信</option>
            <option value="4">支票</option>
            <option value="5">其他</option>
         </select>
       </td>
    </tr>
    <tr>
      <td width="60" align="right">状态</td>
      <td>
         <select name="status" id="add-status"
         class="easyui-combobox" panelHeight="auto"
         style="width:260px" data-options="required:true,
         missingMessage:'请选择状态'">
            <option value="0" selected="selected">未支付</option>
            <option value="1">已支付</option>         
         </select>
       </td>
    </tr>
    
    <tr>
      <td align="right">备注</td>
     <td>
       <textarea name="remark" id="add-remark"
       rows="6" class="wu-testarea" style="width:260px"></textarea>
     </td>
    
    </tr>
    </table>
   
    </form>
    </div>


<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
        	<tr>
                <td width="60" align="right">商品名称:</td>
                <td><input type="text" id="edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写商品名称'" /></td>
            </tr> 
            <tr>
            	<td width="60" align="right">所属供应商:</td>
            	<td>
            		<select name="supplierId" id="edit-supplierId" class="easyui-combobox" panelHeight="auto" style="width:120px" data-options="required:true,missingMessage:'请选择所属供应商'">
            			<option value="-1"></option>
            			<c:forEach items="${supplierList }" var="supplier">
            				<option value="${supplier.id }">${supplier.name }</option>
            			</c:forEach>
            		</select>
            	</td>
            </tr>
            <tr>
                <td width="60" align="right">商品产地:</td>
                <td><input type="text" id="edit-place" name="place" class="wu-text" /></td>
            </tr>  
            <tr>
                <td width="60" align="right">商品规格:</td>
                <td><input type="text" id="edit-spec" name="spec" class="wu-text" /></td>
            </tr>  
            <tr>
                <td width="60" align="right">商品包装:</td>
                <td><input type="text" id="edit-pk" name="pk" class="wu-text" /></td>
            </tr>  
            <tr>
                <td width="60" align="right">商品单位:</td>
                <td><input type="text" id="edit-unit" name="unit" class="wu-text" /></td>
            </tr>   
            <tr>
                <td width="60" align="right">商品价格:</td>
                <td><input type="text" id="edit-price" name="price" class="wu-text easyui-numberbox easyui-validatebox" data-options="required:true, min:0, precision:2, missingMessage:'请填写商品价格'" /></td>
            </tr>   
            <tr>
                <td width="60" align="right">商品描述:</td>
                <td><textarea name="remark" id="edit-remark" roxs="6" class="wu-text" style="width:260px"></textarea></td>
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
		var selectedProducts=
			$("#selected-product-datagrid").datagrid('getData').rows;
		if(selectedProducts.length<=0){
			$.messager.alert('信息提示','请至少选择一个商品进行入库','warning');
		return;
		}
		for(var i=0;i<selectedProducts.length;i++){
			$("#selected-product-datagrid").datagrid('endEdit',i);
			
		}
	  //弹出选择支付方式和状态及备注的框
	  $("add-2-dialog").dialog({
		   closed:false,
		   motal:true,
		   title:"添加备注信息",
		   buttons:[{
			   text:'确定',
			   iconCls:'icon-ok',
			   handler:function(){
				   $.ajax({
					   url:'add',
					   dataType:'json',
					   type:'post',
					   data:{productList:JSON.stringify(
							   selectedProducts),
							   remark:$("#add-remark").val(),
							   payType:$("#add-payType").combobox('getValue'),
							   status:$("#add-status").combobox('getValue')},
							   success:function(data){
								   if(data.type=='success'){
									   $.message.alert('信息提示','添加成功','info');
									   $('#add-2-dialog').dialog('close');
									   $('#add-dialog').dialog('close');
									   $('#data-datagrid').datagrid('reload')
								   }else{
									   $.messager.alert('信息提示',data.msg,'warning');
								   }
							   }
				   });
			   }
			   
		   },{
			   text:'取消',
			   iconCls:'icon-canel',
			   handler:function(){
			      $('#add-2-dialog').dialog('close');
			   }
		   }]
		  
		  
	  });
	}
//		var data = $("#add-form").serialize();
//		$.ajax({
//			url:'add',
//			dataType:'json',
//			type:'post',
//			data:data,
//			success:function(data){
//				if(data.type == 'success'){
//					$.messager.alert('信息提示','添加成功！','info');
//					$("#add-content").val('');
//					$('#add-dialog').dialog('close');
//					$('#data-datagrid').datagrid('reload');
//				}else{
//					$.messager.alert('信息提示',data.msg,'warning');
//				}
//			}
//		});
//	}
	
	
	/**
	*  修改记录
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
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
//					$("#add-content").val('');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
		$("#selected-product-datagrid").datagrid({
		onClickCell:function(rowIndex,field,value){
			if(field=='productNum'){
				$("#selected-product-datagrid").datagrid('beginEdit',rowIndex);
				return;
			}
			$("#selected-product-datagrid").datagrid('endEdit',rowIndex);
		}
	});
	
	
	//选择商品弹窗
	function selectProduct(){
		$("#show-product-dialog").dialog({
			closed: false,
			modal:true,
            title: "选择商品",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler:function(){
                	var item=$('#show-product-datagrid').datagrid('getSelections');
                	if(item==null || item.length==0){
                		$.messager.alert('信息提示',"请至少选择一个商品信息!",'info');
                		return;
                	}
                	for(var i=0;i<item.length;i++){
                		var selectedProducts=$("#selected-product-datagrid").datagrid("getData").rows;
                		var index=-1;
                		for(var j=0;j<selectedProducts.length;j++){
                			if(selectedProducts[j].id==item[i].id){
                				index=j;
                				break;
                				
                			}
                		}
                		if(index>-1){
                			//说明 已经存在
                			$("#selected-product-datagrid").datagrid('updateRow',{
                				index:j,
                				row:{
                					id:item[i].id,
                					name:item[i].name,
                					price:item[i].price,
                					productNum:parseInt(selectedProducts[j].productNum)+1
                				}
                			});
		                          continue;
                		}
                		//追加新的
                		$("#selected-product-datagrid").datagrid('appendRow',{
                			id:item[i].id,
                			name:item[i].name,
                			price:item[i].price,
                			productNum:1
                		})
                	}
                	$('#show-product-dialog').dialog('close');
                }
            },{
            	text:'取消',
            	iconCls:'icon-cancel',
            	handler:function(){
            		$('#show-product-dialog').dialog('close');
            	}
        
			}],
			onBeforeOpen:function(){
            	$('#show-product-datagrid').datagrid({
            		url:'../product/list',
            		rownumbers:true,
            		singleSelect:false,
            		pageSize:20,
            		pagination:true,
            		multiSort:true,
            		idField:'id',
            		fitColumns:true,
            		
            		columns:[[
            			{ field:'chk',checkbox:true},
            			{ field:'name',title:'商品名称',width:100,sortable:true},
            			{ field:'place',title:'产地',width:100,sortable:true},
            			{ field:'spec',title:'规格',width:100,sortable:true},
            			{ field:'pk',title:'包装',width:100,sortable:true},
            			{ field:'unit',title:'单位',width:100,sortable:true},
            			{ field:'price',title:'价格',width:100,sortable:true},
            			{ field:'remark',title:'商品描述',width:100,sortable:true}
            		]]
            	});
            }
			
			
		});
		
	}
	
	
	/**
	* 删除已选的商品
	*/
	function removeProduct(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#selected-product-datagrid').datagrid('getSelections');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要删除的数据！','info');
					return;
				}
		for(var i=0;i<item.length;i++){
			$('#selected-product-datagrid').datagrid('deleteRow',
					$('#selected-product-datagrid').datagrid('getRowIndex',item[i]));
			
		}		
				
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
            title: "添加商品信息",
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
            title: "编辑商品信息",
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
            	$('#edit-id').val(item.id);
            	$('#edit-supplierId').combobox('setValue',item.supplierId);
            	$('#edit-name').val(item.name);
            	$('#edit-place').val(item.place);
            	$('#edit-spec').val(item.spec);
            	$('#edit-pk').val(item.pk);
            	$('#edit-unit').val(item.unit);
            	$('#edit-price').numberbox('setValue',item.price);
            	$('#edit-remark').val(item.remark);
            }
        });
	}
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {name:$("#search-name").val()};
		var supplierId = $("#search-supplier").combobox('getValue');
		if(supplierId!=-1){
			option.supplierId=supplierId;
		}
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
			{ field:'name',title:'商品名称',width:100,sortable:true},
			{ field:'supplierId',title:'所属供应商',width:100,
				formatter:function(value,index,row){
					var supplierList = $("#search-supplier").combobox('getData');
					for(var i=0;i<supplierList.length;i++){
						if(supplierList[i].value==value)return supplierList[i].text;
					}
					return value;
				}},
			{ field:'place',title:'产地',width:100,sortable:true},
			{ field:'spec',title:'规格',width:100,sortable:true},
			{ field:'pk',title:'包装',width:100,sortable:true},
			{ field:'unit',title:'单位',width:100,sortable:true},
			{ field:'price',title:'价格',width:100,sortable:true},
			{ field:'remark',title:'商品描述',width:100,sortable:true}
		]]
	});
</script>
