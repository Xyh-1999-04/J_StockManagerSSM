package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component  	//关键字注解  方便容器加载
public class Supplier {
	private Long id;
	private String name;				//供应商名称
	private String tel;  					//电话
	private String address;			//供应商地址
	private String contactName;	//联系人
	private String contactPhone;	//联系电话
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
