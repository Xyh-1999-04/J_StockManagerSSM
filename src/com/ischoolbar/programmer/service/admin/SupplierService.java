package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Supplier;

//		供应商接口
@Service
public interface SupplierService {
	//添加
	public int add(Supplier supplier);
	//编辑 修改
	public int edit(Supplier supplier);
	//搜索
	public List<Supplier> findList(Map<String,Object>queryMap);
	//模糊搜索总条数
	public Integer getToTal(Map<String,Object>queryMap);
	//删除
	public int delete(Long id);
}
