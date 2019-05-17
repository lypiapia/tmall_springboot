package com.levy.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levy.service.OrderService;

@Entity
@Table(name = "order_")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Order {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;	//外键uid，指向用户表id字段
	
	private String orderCode;	//订单
	private String address;		//收货地址
	private String post;	//邮政编码
    private String receiver;	//收货人信息
    private String mobile;	//手机号
    private String userMessage;		//用户备注信息
    private Date createDate;	//创建时间
    private Date payDate;	//支付时间
    private Date deliveryDate;	//发货日期
    private Date confirmDate;	//确认收货日期
    private String status;	//订单状态
    
    @Transient
    private List<OrderItem> orderItems;	//该订单下的订单项列表
    
    @Transient
    private float total; //订单总金额
    
    @Transient
    private int totalNumber; //订单总数量
    
    @Transient
    private String statusDesc;
    
	public String getStatusDesc() {
		if(statusDesc != null) {
			return statusDesc;
		}
		String desc = "未知";
		switch (status) {
		case OrderService.WAIT_PAY:
			desc = "待付款";
			break;
		case OrderService.WAIT_DELIVERY:
			desc = "待发货";
			break;	
		case OrderService.WAIT_CONFIRM:
			desc = "待收货";
			break;
		case OrderService.WAIT_REVIEW:
			desc = "待评价";
			break;
		case OrderService.FINISH:
			desc = "完成";
			break;
		case OrderService.DELETE:
			desc = "删除";
			break;
		default:
			desc = "未知";
		}
		return desc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
}
