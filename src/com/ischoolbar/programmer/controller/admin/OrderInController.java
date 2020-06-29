package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.OrderIn;
import com.ischoolbar.programmer.entity.admin.OrderInDetail;
import com.ischoolbar.programmer.entity.admin.Product;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.OrderInService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//进货单管理控制器
	@RequestMapping("/admin/order_in")
	@Controller
public class OrderInController {
		@Autowired
		private OrderInService orderInService;
		//进货单管理列表页面
		@RequestMapping(value="/list",method=RequestMethod.GET)
		public ModelAndView list(ModelAndView model) {
			Map<String,Object> queryMap=new HashMap<String,Object>();
			model.setViewName("order_in/list");
			return model;
		}
		//添加进货单信息
		@RequestMapping(value="add",method=RequestMethod.POST)
		@ResponseBody  //返回json字符串
		
		public Map<String,String> add(String productList,OrderIn orderIn,
					HttpServletRequest request){
			//创建返回值变量
			Map<String,String> ret=new HashMap<String,String>();
			if(StringUtils.isEmpty(productList)) {
				ret.put("type","error");
				ret.put("msg","请至少选择一个商品信息");
				return ret;
			}
			
			if(orderIn==null) {
				ret.put("type","error");
				ret.put("msg","请正确填写进货单信息");
				return ret;
			}
			//转换JSON
			JSONArray productArray=JSONArray.fromObject(productList);
			float money=0;//计数
			int num=0;
			for(int i=0;i<productArray.size();i++) {
				JSONObject jsonObject=productArray.getJSONObject(i);
				OrderInDetail orderInDetail=new OrderInDetail();
				orderInDetail.setProductName(jsonObject.getString("name"));
				orderInDetail.setProductNum(jsonObject.getInt("productNum"));
				orderInDetail.setPrice(Float.valueOf(getString("price")));
				orderInDetail.setMoney(orderInDetail.getPrice()*
						orderInDetail.getProductNum());
				orderIn.getOrderInDetailList().add(orderInDetail);
				money+=orderInDetail.getMoney();
				num+=orderInDetail.getProductNum();
				
				
			}
			orderIn.setMoney(money);
			orderIn.setProductNum(num);
			
			
			//说明用户已经登录
			User admin=(User) request.getSession().getAttribute("admin");
			orderIn.setOperator(admin.getUsername());
		
			
			if(orderInService.add(orderIn)<=0) {
				ret.put("type","error");
				ret.put("msg","添加失败,请联系管理员");
				return ret;
			}

			ret.put("type","success");
			ret.put("msg","添加成功");
			return ret;

			}
		private String getString(String string) {
			// TODO Auto-generated method stub
			return null;
		}
		//修改商品信息
				@RequestMapping(value="edit",method=RequestMethod.POST)
				@ResponseBody  //返回json字符串
				public Map<String,String> edit(OrderIn orderIn){
					Map<String,String> ret=new HashMap<String,String>();
					if(orderIn==null) {
						ret.put("type","error");
						ret.put("msg","请正确填写商品信息");
						return ret;
					}
					orderIn.setStatus(1);
					if(orderInService.edit(orderIn)<=0) {
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
						@RequestParam(name="payType",required=false)Integer payType,
						@RequestParam(name="status",required=false)Integer status,
						@RequestParam(name="minMoney",required=false)Float minMoney,
						@RequestParam(name="maxMoney",required=false)Float maxMoney,
						@RequestParam(name="operator",
						defaultValue="")String operator,Page page ){
				Map<String,Object> ret=new HashMap<String,Object>();
				Map<String,Object>queryMap=new HashMap<String,Object>();
				queryMap.put("operator",operator);
				if(payType!=null) {
					queryMap.put("payType", payType);
				}
				if(status!=null) {
					queryMap.put("status", status);
				}
				if(minMoney!=null) {
					queryMap.put("minMoney", minMoney);
				}
				if(maxMoney!=null) {
					queryMap.put("maxMoney", maxMoney);
				}
				queryMap.put("Offset",page.getOffset());
				queryMap.put("pageSize",page.getRows());
				ret.put("total",orderInService.getTotal(queryMap));
				ret.put("rows",orderInService.findList(queryMap));
				return ret;
				}
				//删除
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
					
					if(orderInService.delete(id)<=0) {
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

