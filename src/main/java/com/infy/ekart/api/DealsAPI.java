
package com.infy.ekart.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.ekart.dto.DealsDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.service.DealsService;


@CrossOrigin
@RestController
@RequestMapping("/deals-api")
public class DealsAPI {
	
@Autowired
private DealsService dealsService;

//US 01 add data into deals
	@PostMapping(value = "/addProductToDeal")
	public ResponseEntity<DealsDTO> addProductToDeal(@RequestBody DealsDTO dealsDTO) throws EKartException {
		Integer dealId=dealsService.addProductToDeal(dealsDTO);
		dealsDTO.setDealId(dealId);
		dealsDTO.getProduct().setSuccessMessage("Product added successfully to deals with Deal Id:" +dealId);
		return new ResponseEntity<>(dealsDTO, HttpStatus.OK);

}
	
	//deals are show from deals table US 02
	@GetMapping(value = "/sellerTodayDeals/{sellerEmailId}")
	public ResponseEntity<List<DealsDTO>> getsellerTodayDeals(@PathVariable String sellerEmailId) throws EKartException {
		sellerEmailId=sellerEmailId+".com";
	
		List<DealsDTO> sellerTodayDeals = dealsService.getsellerTodayDeals(sellerEmailId);
		return new ResponseEntity<>(sellerTodayDeals, HttpStatus.OK);
	}
	
	
	
	//delete deals from deals when deal is over ** US 03
	@GetMapping(value = "/deleteProductFromoDeal/{dealId}")
	public ResponseEntity<String> deleteProductFromDeal(@PathVariable Integer dealId) throws EKartException {
		String successMessage = dealsService.deleteProductFromDeal(dealId);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	
	}
	
	//us04
	@GetMapping(value="/todayDeals")
	public ResponseEntity<List<DealsDTO>> todayDeals() throws EKartException{
		return new ResponseEntity<>(dealsService.getTodayDeals(),HttpStatus.OK);
	}


}
