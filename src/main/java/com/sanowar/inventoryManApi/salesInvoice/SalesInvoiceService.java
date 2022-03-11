package com.sanowar.inventoryManApi.salesInvoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanowar.inventoryManApi.util.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Md. Sanowar Ali
 *
 */

@Service
public class SalesInvoiceService {

	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;


	public Response gridList(HttpServletRequest request) {
		return salesInvoiceRepository.gridList(request);
	}
	
	public Response getAllList(String reqObj) {
		return salesInvoiceRepository.getAllList(reqObj);
	}
	
	public Response save(String reqObj) {
		return salesInvoiceRepository.save(reqObj);
	}
	
	public Response update(String reqObj) {
		return salesInvoiceRepository.update(reqObj);
	}
	
	public Response findById(Long id) {
		return salesInvoiceRepository.findById(id);

	}
	
	public Response delete(Long id) {
		return salesInvoiceRepository.delete(id);
	}
	
}
