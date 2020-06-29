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

//��Ӧ�̹��������
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
	//��ӹ�Ӧ����Ϣ
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody  //����json�ַ���
	public Map<String,String> add(Supplier supplier){
		Map<String,String> ret=new HashMap<String,String>();
		if(supplier==null) {
			ret.put("type","error");
			ret.put("msg","����ȷ��д��Ӧ����Ϣ");
		}
		if(StringUtils.isEmpty(supplier.getName())) {
			ret.put("type","error");
			ret.put("msg","����д��Ӧ������");
			return ret;
		 }
		if(StringUtils.isEmpty(supplier.getTel())) {
			ret.put("type","error");
			ret.put("msg","����д��Ӧ�̵绰");
			return ret;
		 }
		if(StringUtils.isEmpty(supplier.getContactName())) {
			ret.put("type","error");
			ret.put("msg","����д��Ӧ����ϵ��");
			return ret;
		 }
		if(StringUtils.isEmpty(supplier.getContactPhone())) {
			ret.put("type","error");
			ret.put("msg","����д��Ӧ����ϵ���ֻ���");
			return ret;
	}
		if(StringUtils.isEmpty(supplier.getAddress())) {
			ret.put("type","error");
			ret.put("msg","����д��Ӧ����ϵ�˵�ַ");
			return ret;
	}
		if(supplierService.add(supplier)<=0) {
			ret.put("type","error");
			ret.put("msg","���ʧ��,����ϵ����Ա");
			return ret;
		}

		ret.put("type","success");
		ret.put("msg","��ӳɹ�");
		return ret;

		}
	
	//�޸Ĺ�Ӧ����Ϣ
		@RequestMapping(value="edit",method=RequestMethod.POST)
		@ResponseBody  //����json�ַ���
		public Map<String,String> edit(Supplier supplier){
			Map<String,String> ret=new HashMap<String,String>();
			if(supplier==null) {
				ret.put("type","error");
				ret.put("msg","����ȷ��д��Ӧ����Ϣ");
			}
			if(StringUtils.isEmpty(supplier.getName())) {
				ret.put("type","error");
				ret.put("msg","����д��Ӧ������");
				return ret;
			 }
			if(StringUtils.isEmpty(supplier.getTel())) {
				ret.put("type","error");
				ret.put("msg","����д��Ӧ�̵绰");
				return ret;
			 }
			if(StringUtils.isEmpty(supplier.getContactName())) {
				ret.put("type","error");
				ret.put("msg","����д��Ӧ����ϵ��");
				return ret;
			 }
			if(StringUtils.isEmpty(supplier.getContactPhone())) {
				ret.put("type","error");
				ret.put("msg","����д��Ӧ����ϵ���ֻ���");
				return ret;
		}
			if(StringUtils.isEmpty(supplier.getAddress())) {
				ret.put("type","error");
				ret.put("msg","����д��Ӧ����ϵ�˵�ַ");
				return ret;
		}
			if(supplierService.edit(supplier)<=0) {
				ret.put("type","error");
				ret.put("msg","�༭ʧ��,����ϵ����Ա");
				return ret;
			}

			ret.put("type","success");
			ret.put("msg","�༭�ɹ�");
			return ret;

			}
		//ģ��������ҳ��ȡ��Ӧ����Ϣ
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object>getList(
		//��������󶨵�������
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
	  // value��/ �������·��   ����/ �����Զ�·��
	@ResponseBody
	public Map<String,String> delete(Long id){
		Map<String,String> ret=new HashMap<String,String>();
		if(id==null) {
		ret.put("type","error");
		ret.put("msg", "��ѡ����Ҫɾ���Ĺ�Ӧ��");
		return ret;
	}
	try {
		//���ݿ��������
		//��Ӧ����������Ʒ ���ݿ�ͬ��ɾ��
		//��Ӧ��������Ӳ˵� �Ҳ������˵���
		
		if(supplierService.delete(id)<=0) {
			ret.put("type","error");
			ret.put("msg","ɾ��ʧ������ϵ����Ա");
			return ret;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		ret.put("type","error");
		ret.put("msg","��Ӧ�����������Ʒ�����Ƚ���Ʒɾ��");
		return ret;
	}
	ret.put("type","success");
	ret.put("msg","ɾ���ɹ�");
	return ret;
	}
}
