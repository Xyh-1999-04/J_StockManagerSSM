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

import com.ischoolbar.programmer.entity.admin.Product;
import com.ischoolbar.programmer.entity.admin.Supplier;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.ProductService;
import com.ischoolbar.programmer.service.admin.SupplierService;

//商品管理控制器
@RequestMapping("/admin/product")
@Controller
public class ProductController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ProductService productService;
	//商品管理列表
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		Map<String,Object> queryMap=new HashMap<String,Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 9999);//获取全部
		//拿到所有供应商
		model.addObject("supplierList",supplierService.findList(queryMap));
		model.setViewName("product/list");
		return model;
	}
	//添加商品信息
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody  //返回json字符串
	public Map<String,String> add(Product product){
		Map<String,String> ret=new HashMap<String,String>();
		if(product==null) {
			ret.put("type","error");
			ret.put("msg","请正确填写商品信息");
		}
		if(StringUtils.isEmpty(product.getName())) {
			ret.put("type","error");
			ret.put("msg","请填写商品名称");
			return ret;
		 }
		if(product.getSupplierId()==null) {
			ret.put("type","error");
			ret.put("msg","请填写供应商");
			return ret;
		 }
		if(product.getPrice()==null) {  //名称和价格必须有 别的不做验证
			ret.put("type","error");
			ret.put("msg","请填写商品价格");
			return ret;
		 }
		
		if(productService.add(product)<=0) {
			ret.put("type","error");
			ret.put("msg","添加失败,请联系管理员");
			return ret;
		}

		ret.put("type","success");
		ret.put("msg","添加成功");
		return ret;

		}
	
	//修改商品信息
		@RequestMapping(value="edit",method=RequestMethod.POST)
		@ResponseBody  //返回json字符串
		public Map<String,String> edit(Product product){
			Map<String,String> ret=new HashMap<String,String>();
			if(product==null) {
				ret.put("type","error");
				ret.put("msg","请正确填写商品信息");
			}
			if(StringUtils.isEmpty(product.getName())) {
				ret.put("type","error");
				ret.put("msg","请填写商品名称");
				return ret;
			 }
			if(product.getSupplierId()==null) {
				ret.put("type","error");
				ret.put("msg","请选择商品供应商");
				return ret;
			 }
			if(product.getPrice()==null) {
				ret.put("type","error");
				ret.put("msg","请填写商品价格");
				return ret;
			 }
			
			if(productService.edit(product)<=0) {
				ret.put("type","error");
				ret.put("msg","编辑失败,请联系管理员");
				return ret;
			}

			ret.put("type","success");
			ret.put("msg","编辑成功");
			return ret;

			}
		//模糊搜索分页获取商品信息
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object>getList(
		//请求参数绑定倒控制器
			@RequestParam(name="supplierId",required=false)Long supplierId,
			@RequestParam(name="name",
			defaultValue="")String name,Page page ){
	Map<String,Object> ret=new HashMap<String,Object>();
	Map<String,Object>queryMap=new HashMap<String,Object>();
	queryMap.put("name",name);
	if(supplierId!=null) {
		queryMap.put("supplierId", supplierId);
	}
	queryMap.put("Offset",page.getOffset());
	queryMap.put("pageSize",page.getRows());
	ret.put("total",productService.getTotal(queryMap));
	ret.put("rows",productService.findList(queryMap));
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
		
		if(productService.delete(id)<=0) {
			ret.put("type","error");
			ret.put("msg","该商品存在关联");
			return ret;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		ret.put("type","error");
		ret.put("msg","该商品存在挂暗恋入库或者销售的信息"+"请先将商品关联删除");
		return ret;
	}
	ret.put("type","success");
	ret.put("msg","删除成功");
	return ret;
	}
}
