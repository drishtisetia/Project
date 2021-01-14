
package com.infy.ekart.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="EK_DEALS_FOR_TODAY")
@Entity
public class Deals {

@Id
@Column(name="DEAL_ID")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer dealId;

@Column(name="DEAL_DISCOUNT")
private Double dealDiscount;

@Column(name="DEAL_STARTS_AT")
private LocalDateTime dealStartsAt;

@Column(name="DEAL_ENDS_AT")
private LocalDateTime dealEndsAt;

@Column(name="SELLER_EMAIL_ID")
private String sellerEmailId;

@ManyToOne(cascade=CascadeType.ALL)
@JoinColumn(name="PRODUCT_ID")
private Product product;


	
	public Integer getDealId() {
		return dealId;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	
	public Double getDealDiscount() {
		return dealDiscount;
	}
	
	public void setDealDiscount(Double dealDiscount) {
		this.dealDiscount = dealDiscount;
	}
	
	public LocalDateTime getDealStartsAt() {
		return dealStartsAt;
	}
	
	public void setDealStartsAt(LocalDateTime dealStartsAt) {
		this.dealStartsAt = dealStartsAt;
	}
	
	public LocalDateTime getDealEndsAt() {
		return dealEndsAt;
	}
	
	public void setDealEndsAt(LocalDateTime dealEndsAt) {
		this.dealEndsAt = dealEndsAt;
	}
	
	public String getSellerEmailId() {
		return sellerEmailId;
	}
	
	public void setSellerEmailId(String sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}
	

}