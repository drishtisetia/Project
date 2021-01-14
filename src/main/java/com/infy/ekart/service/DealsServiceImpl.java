//Implementing Interfaces of Deal Service class
// done
package com.infy.ekart.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dto.DealsDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.Deals;
import com.infy.ekart.entity.Product;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.DealsRepository;
import com.infy.ekart.repository.ProductRepository;

@Service(value = "dealsService")
@Transactional
public class DealsServiceImpl implements DealsService{

@Autowired
private DealsRepository dealsRepository;
@Autowired
private ProductRepository productRepository; //5555

//US02
@Override
public List<DealsDTO> getsellerTodayDeals(String sellerEmailId) throws EKartException {
	System.out.println("Inside Service:"+sellerEmailId);
	List<Deals> l=dealsRepository.findBySellerEmailId(sellerEmailId);
	if(l.isEmpty()) 
	{
		throw new EKartException("DealsService.INVALID_NO_DEALS_SELLER");
	}else {
		List<DealsDTO> res=new ArrayList<>();
	for(Deals d:l) {
		DealsDTO d1=new DealsDTO();
		d1.setDealId(d.getDealId());
		d1.setDealDiscount(d.getDealDiscount());
		d1.setDealStartsAt(d.getDealStartsAt());
		d1.setDealEndsAt(d.getDealEndsAt());
		d1.setSellerEmailId(d.getSellerEmailId());
		ProductDTO pdto = new ProductDTO();
		pdto.setProductId(d.getProduct().getProductId());
		pdto.setName(d.getProduct().getName());
		pdto.setBrand(d.getProduct().getBrand());
		pdto.setPrice(d.getProduct().getPrice());
		pdto.setDiscount(d.getProduct().getDiscount());
		pdto.setCategory(d.getProduct().getCategory());
		pdto.setDescription(d.getProduct().getDescription());
		pdto.setQuantity(d.getProduct().getQuantity());
		d1.setProduct(pdto);
		res.add(d1);
		}
	return res;
	}
}
//US01
@Override
public Integer addProductToDeal(DealsDTO dealsDTO) throws EKartException{
	if(dealsDTO.getDealStartsAt().toLocalDate().isAfter(LocalDate.now().plusDays(31)))
		throw new EKartException("DealsService.INVALID_MONTH");  
		
	if(dealsDTO.getDealEndsAt().toLocalTime().isBefore(dealsDTO.getDealStartsAt().toLocalTime()))
		throw new EKartException("DealsService.INVALID_ENDTIME");
		
	if(dealsDTO.getDealStartsAt().toLocalDate().equals(LocalDate.now()))
		throw new EKartException("DealsService.INVALID_STARTDATE_ENDDATE");
		
	if(!(dealsDTO.getDealStartsAt().toLocalDate().equals(dealsDTO.getDealEndsAt().toLocalDate())))
		throw new EKartException("DealsService.INVALID_DATE");
	
	Optional<Product> op=productRepository.findById(dealsDTO.getProduct().getProductId());
	Product p=op.orElseThrow();
	Deals d =new Deals();
	p.setProductId(dealsDTO.getProduct().getProductId());///change
	d.setDealDiscount(dealsDTO.getDealDiscount());
	d.setDealStartsAt(dealsDTO.getDealStartsAt());
	d.setDealEndsAt(dealsDTO.getDealEndsAt());
	d.setSellerEmailId(dealsDTO.getSellerEmailId());
	d.setProduct(p);
	Deals res=dealsRepository.save(d);
	return res.getDealId();
}

//US03
@Override
public String deleteProductFromDeal(Integer dealId) throws EKartException{
	Optional<Deals> op = dealsRepository.findById(dealId);
	Deals d = op.orElseThrow(()->new EKartException("DealsService.INVALID_DEALID"));
	Product p = null;
	d.setProduct(p);
	dealsRepository.delete(d);
	return "Deal deleted successfully with Deal Id:"+dealId;
}

//us04
@Override
public List<DealsDTO> getTodayDeals() throws EKartException{
	LocalTime time2=LocalTime.of(00,01);
	LocalDateTime datestart=LocalDateTime.of(LocalDate.now(),time2);
	LocalTime time=LocalTime.of(23,59);
	LocalDateTime dateEnd=LocalDateTime.of(LocalDate.now(),time);
	List<Deals> dealsStart=dealsRepository.findByDealStartsAtBetween(datestart,dateEnd);
	if(dealsStart.isEmpty())
		throw new EKartException("DealService.NO_DEALS_TODAY");
	else{
		List<DealsDTO> finalDeals=new ArrayList<>();
		for(int i=0;i<dealsStart.size();i++){
			DealsDTO dDTO=new DealsDTO();
			dDTO.setSellerEmailId(dealsStart.get(i).getSellerEmailId());
			dDTO.setDealDiscount(dealsStart.get(i).getDealDiscount());
			dDTO.setDealEndsAt(dealsStart.get(i).getDealEndsAt());
			dDTO.setDealStartsAt(dealsStart.get(i).getDealStartsAt());
			dDTO.setDealId(dealsStart.get(i).getDealId());
			ProductDTO pdto = new ProductDTO();
			
			pdto.setBrand(dealsStart.get(i).getProduct().getBrand());
			pdto.setCategory(dealsStart.get(i).getProduct().getBrand());
			pdto.setDescription(dealsStart.get(i).getProduct().getDescription());
			pdto.setDiscount(dealsStart.get(i).getProduct().getDiscount());
			pdto.setName(dealsStart.get(i).getProduct().getName());
			pdto.setPrice(dealsStart.get(i).getProduct().getPrice());
			pdto.setProductId(dealsStart.get(i).getProduct().getProductId());
			pdto.setQuantity(dealsStart.get(i).getProduct().getQuantity());
			pdto.setSellerEmailId(dealsStart.get(i).getProduct().getSellerEmailId());
			
			dDTO.setProduct(pdto);
			finalDeals.add(dDTO);
		}
	return finalDeals;
	}
}



}
