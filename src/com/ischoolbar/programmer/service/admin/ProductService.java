package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Product;
@Service
//shang'p ��service �ӿ�  �ṩ��ʵ����
public interface ProductService {
	public int add(Product product);
	public int edit(Product product);
	public List<Product> findList(Map<String,Object>queryMap);
	public Integer getTotal(Map<String,Object>queryMap);
	public int delete(Long id);

}
