
package com.infy.ekart.dto;

import java.time.LocalDateTime;

import com.infy.ekart.entity.Product;

public class DealsDTO {
private Integer dealId;
//private Integer productId;
private Double dealDiscount;
private LocalDateTime dealStartsAt;
private LocalDateTime dealEndsAt;
private String sellerEmailId;


private ProductDTO product;

	
	
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public Integer getDealId() {
		return dealId;
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
