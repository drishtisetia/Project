
package com.infy.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.sellerEmailId =?1 and p.productId NOT IN (select d.product from Deals d where d.sellerEmailId=?1)")
	List<Product> findBySellerEmailId(String sellerEmailId);
	
}