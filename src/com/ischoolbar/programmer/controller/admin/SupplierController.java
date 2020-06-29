package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Supplier;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.SupplierService;

//供应商管理控制器
@RequestMapping("/admin/supplier")
@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("supplier/list");
		return model;
	}
	//添加供应商信息
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody  //返回json字符串
	public Map<String,String> add(Supplier supplier){
		Map<String,String> ret=new HashMap<String,String>();
		if(supplier==null) {
			ret.put("type","error");
			ret.put("msg","请正确填写供应商信息");
		}
		if(StringUtils.isEmpty(supplier.getName())) {
			ret.put("type","error");
			ret.put("msg","请填写供应商名称");
			return ret;
		 }
		if(StringUtils.isEmpty(supplier.getTel())) {
			ret.put("type","error");
			ret.put("msg","请填写供应商电话");
			return ret;
		 }
		if(StringUtils.isEmpty(supplier.getContactName())) {
			ret.put("type","error");
			ret.put("msg","请填写供应商联系人");
			return ret;
		 }
		if(StringUtils.isEmpty(supplier.getContactPhone())) {
			ret.put("type","error");
			ret.put("msg","请填写供应商联系人手机号");
			return ret;
	}
		if(StringUtils.isEmpty(supplier.getAddress())) {
			ret.put("type","error");
			ret.put("msg","请填写供应商联系人地址");
			return ret;
	}
		if(supplierService.add(supplier)<=0) {
			ret.put("type","error");
			ret.put("msg","添加失败,请联系管理员");
			return ret;
		}

		ret.put("type","success");
		ret.put("msg","添加成功");
		return ret;

		}
	
	//修改供应商信息
		@RequestMapping(value="edit",method=RequestMethod.POST)
		@ResponseBody  //返回json字符串
		public Map<String,String> edit(Supplier supplier){
			Map<String,String> ret=new HashMap<String,String>();
			if(supplier==null) {
				ret.put("type","error");
				ret.put("msg","请正确填写供应商信息");
			}
			if(StringUtils.isEmpty(supplier.getName())) {
				ret.put("type","error");
				ret.put("msg","请填写供应商名称");
				return ret;
			 }
			if(StringUtils.isEmpty(supplier.getTel())) {
				ret.put("type","error");
				ret.put("msg","请填写供应商电话");
				return ret;
			 }
			if(StringUtils.isEmpty(supplier.getContactName())) {
				ret.put("type","error");
				ret.put("msg","请填写供应商联系人");
				return ret;
			 }
			if(StringUtils.isEmpty(supplier.getContactPhone())) {
				ret.put("type","error");
				ret.put("msg","请填写供应商联系人手机号");
				return ret;
		}
			if(StringUtils.isEmpty(supplier.getAddress())) {
				ret.put("type","error");
				ret.put("msg","请填写供应商联系人地址");
				return ret;
		}
			if(supplierService.edit(supplier)<=0) {
				ret.put("type","error");
				ret.put("msg","编辑失败,请联系管理员");
				return ret;
			}

			ret.put("type","success");
			ret.put("msg","编辑成功");
			return ret;

			}
		//模糊搜索分页获取供应商信息
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object>getList(
		//请求参数绑定倒控制器
			@RequestParam(name="name",
			defaultValue="")String name,Page page ){
	Map<String,Object> ret=new HashMap<String,Object>();
	Map<String,Object>queryMap=new HashMap<String,Object>();
	queryMap.put("name",name);
	queryMap.put("Offset",page.getOffset());
	queryMap.put("pageSize",page.getRows());
	ret.put("total",supplierService.getToTal(queryMap));
	ret.put("rows",supplierService.findList(queryMap));
	return ret;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	  // value带/ 代表绝对路径   不带/ 代表性对路径
	@ResponseBody
	public Map<String,String> delete(Long id){
		Map<String,String> ret=new HashMap<String,String>();
		if(id==null) {
		ret.put("type","error");
		ret.put("msg", "请选择你要删除的供应商");
		return ret;
	}
	try {
		//数据库外键问题
		//供应商下面有商品 数据库同意删除
		//供应商下面的子菜单 找不到父菜单了
		
		if(supplierService.delete(id)<=0) {
			ret.put("type","error");
			ret.put("msg","删除失败请联系管理员");
			return ret;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		ret.put("type","error");
		ret.put("msg","供应商限免存在商品，请先将商品删除");
		return ret;
	}
	ret.put("type","success");
	ret.put("msg","删除成功");
	return ret;
	}
}
