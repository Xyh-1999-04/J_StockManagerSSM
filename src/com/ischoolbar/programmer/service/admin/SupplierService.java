package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Supplier;

//		��Ӧ�̽ӿ�
@Service
public interface SupplierService {
	//���
	public int add(Supplier supplier);
	//�༭ �޸�
	public int edit(Supplier supplier);
	//����
	public List<Supplier> findList(Map<String,Object>queryMap);
	//ģ������������
	public Integer getToTal(Map<String,Object>queryMap);
	//ɾ��
	public int delete(Long id);
}
