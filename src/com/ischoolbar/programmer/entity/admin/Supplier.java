package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component  	//�ؼ���ע��  ������������
public class Supplier {
	private Long id;
	private String name;				//��Ӧ������
	private String tel;  					//�绰
	private String address;			//��Ӧ�̵�ַ
	private String contactName;	//��ϵ��
	private String contactPhone;	//��ϵ�绰
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactName() {
		return contactName;
	}
	public void setcontactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
}
