package com.infy.ekart.service.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ekart.dto.DealsDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.Deals;
import com.infy.ekart.entity.Product;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.DealsRepository;
import com.infy.ekart.repository.ProductRepository;
import com.infy.ekart.repository.SellerRepository;
import com.infy.ekart.service.DealsService;
import com.infy.ekart.service.DealsServiceImpl;

@SpringBootTest
public class DealsTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private SellerRepository sellerRepository;
	
	@Mock
	private DealsRepository dealsRepository;

	@InjectMocks
	private DealsService dealsService=new DealsServiceImpl();
	
		//US01
		@Test
		public void addProductToDealValidTest() throws EKartException {
			 DealsDTO d=new DealsDTO();
			 d.setDealStartsAt(LocalDateTime.now().plusDays(1));
			 d.setDealEndsAt(LocalDateTime.now().plusDays(1).plusSeconds(1));
			 //d.setDealId(10);
			 ProductDTO p=new ProductDTO();
			 p.setProductId(1);
			 d.setProduct(p);
	
			 Deals d1=new Deals();
			 d1.setDealId(10);
			 Product p1=new Product();
			 p1.setProductId(1);
			 d1.setProduct(p1);
	
			 Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(p1));
			 Mockito.when(dealsRepository.save(Mockito.any())).thenReturn(d1);
			 Integer actual = dealsService.addProductToDeal(d);
			 Assertions.assertEquals(d1.getDealId(),actual);
		 }

	
	
	//US02
		@Test
		void getsellerTodayDealsValidTest() throws EKartException{
			String emailId="jack@infosys.com";
			List<Deals> deals=new ArrayList<>();
			Deals d1=new Deals();
			Product product=new Product();
			d1.setProduct(product);
			deals.add(d1);
			Mockito.when(dealsRepository.findBySellerEmailId(Mockito.anyString())).thenReturn(deals);
			Assertions.assertNotNull(dealsService.getsellerTodayDeals(emailId));
		}
		
		
		//US02
		@Test
		void getsellerTodayDealsInvalidTest(){
			String emailId="asfghgk@infosys.com";
			List<Deals> deals=new ArrayList<>();
			Mockito.when(dealsRepository.findBySellerEmailId(Mockito.anyString())).thenReturn(deals);
			Exception e=Assertions.assertThrows(EKartException.class, ()->dealsService.getsellerTodayDeals(emailId));
			Assertions.assertEquals("DealsService.INVALID_NO_DEALS_SELLER",e.getMessage());
		}
	
	//US03
	@Test
	void deleteProductFromDealValidTest(){
		Integer dealId=12;
		Deals deal=new Deals();
		Mockito.when(dealsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(deal));

		Assertions.assertDoesNotThrow(()->dealsService.deleteProductFromDeal(dealId));
	}

	//US03
	@Test
	void deleteProductFromDealInvalidTest(){
		Integer dealId=12;
		Mockito.when(dealsRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Exception e=Assertions.assertThrows(EKartException.class, ()->dealsService.deleteProductFromDeal(dealId));
		Assertions.assertEquals("DealsService.INVALID_DEALID",e.getMessage());
	}
	
	



	//US04
	@Test
	void getTodayDealsValidTest() throws EKartException{
		List<Deals> deals = new ArrayList<>();
		Deals deals1 = new Deals();
		deals1.setDealId(1);
		Product p= new Product();
		deals1.setProduct(p);
		deals.add(deals1);
		Mockito.when(dealsRepository.findByDealStartsAtBetween(Mockito.any(),Mockito.any())).thenReturn(deals);
		Assertions.assertNotNull(dealsService.getTodayDeals());
	}
	
	//US04
	@Test
	void getTodayDealsInvalidTest() throws EKartException{
		List<Deals> deals = new ArrayList<>();
		Mockito.when(dealsRepository.findByDealStartsAtBetween(Mockito.any(),Mockito.any())).thenReturn(deals);
		Exception e = Assertions.assertThrows(EKartException.class,()->dealsService.getTodayDeals());
		Assertions.assertEquals("DealService.NO_DEALS_TODAY",e.getMessage());
	}

	
}
