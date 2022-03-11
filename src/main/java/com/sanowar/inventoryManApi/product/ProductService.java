package com.sanowar.inventoryManApi.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanowar.inventoryManApi.util.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Md. Sanowar Ali
 *
 */

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;


	public Response gridList(HttpServletRequest request) {
		return productRepository.gridList(request);
	}
	
	public Response getAllList(String reqObj) {
		return productRepository.getAllList(reqObj);
	}
	
	public Response save(String reqObj) {
		return productRepository.save(reqObj);
	}
	
	public Response update(String reqObj) {
		return productRepository.update(reqObj);
	}
	
	public Response findById(Long id) {
		return productRepository.findById(id);

	}
	
	public Response delete(Long id) {
		return productRepository.delete(id);
	}
	
}
