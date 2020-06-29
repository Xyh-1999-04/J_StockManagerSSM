package com.ischoolbar.programmer.entity.admin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//杩涜揣鍗曞疄浣�
import org.springframework.stereotype.Component;

@Component

public class OrderIn {
	
	private Long id;
	private Float money;//杩涜揣鍗曟�婚噾棰�
	private Integer productNum;//鍟嗗搧鏁伴噺
	private int payType=0;//鏀粯鏂瑰紡0锛氱幇閲戯紝1锛氶摱琛岃浆璐�
	//2锛氭敮浠樺疂锛�3锛氬井淇★紝4锛氭敮绁紝5锛氬叾浠�
	private int status=0;//鐘舵��0锛氭湭鏀粯锛�1锛氬凡鏀粯
	private String operator;//鎿嶄綔浜�
	private String remark;//澶囨敞
	private List<OrderInDetail> orderInDetailList=new
			ArrayList<OrderInDetail>();
	public List<OrderInDetail> getOrderInDetailList() {
		return orderInDetailList;
	}
	public void setOrderInDetailList(List<OrderInDetail> orderInDetailList) {
		this.orderInDetailList = orderInDetailList;
	}
	private Date createTime;//杩涜揣鏃堕棿
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	

}
	
	
