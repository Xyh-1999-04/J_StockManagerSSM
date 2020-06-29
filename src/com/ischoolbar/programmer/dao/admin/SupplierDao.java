package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Supplier;

//	供应商的dao
@Repository
public interface SupplierDao {
		public int add(Supplier supplier);
		//修改编辑
		public int edit(Supplier supplier);
			//搜索
				public List<Supplier> findList(Map<String,Object>queryMap);
				//模糊搜索总条数
				public Integer getTotal(Map<String,Object>queryMap);
				//删除
				public int delete(Long id);
}
