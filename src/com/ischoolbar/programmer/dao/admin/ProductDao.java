package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Product;
@Repository
public interface ProductDao {
	public int add(Product product);
	public int edit(Product product);
	public List<Product> findList(Map<String,Object>queryMap);
	public Integer getTotal(Map<String,Object>queryMap);
	public int delete(Long id);

}
