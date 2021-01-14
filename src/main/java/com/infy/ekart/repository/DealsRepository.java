
package com.infy.ekart.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Deals;


public interface DealsRepository extends CrudRepository<Deals, Integer>{
	
	//US02
	List<Deals> findBySellerEmailId(String sellerEmailId);
	
	//US04
	List<Deals> findByDealStartsAtBetween(LocalDateTime datetime1,LocalDateTime datetime2);

}
