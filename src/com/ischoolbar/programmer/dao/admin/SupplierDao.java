package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Supplier;

//	��Ӧ�̵�dao
@Repository
public interface SupplierDao {
		public int add(Supplier supplier);
		//�޸ı༭
		public int edit(Supplier supplier);
			//����
				public List<Supplier> findList(Map<String,Object>queryMap);
				//ģ������������
				public Integer getTotal(Map<String,Object>queryMap);
				//ɾ��
				public int delete(Long id);
}
