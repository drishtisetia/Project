
package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.dto.DealsDTO;
import com.infy.ekart.exception.EKartException;

public interface DealsService {

public List<DealsDTO> getsellerTodayDeals(String sellerEmailId) throws EKartException;

public Integer addProductToDeal(DealsDTO dealsDTO) throws EKartException;

public String deleteProductFromDeal(Integer dealId) throws EKartException;

//US04 Interface to get deals for today DTO
public List<DealsDTO> getTodayDeals() throws EKartException;

}
