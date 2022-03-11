package com.sanowar.inventoryManApi.salesInvoice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanowar.inventoryManApi.util.Response;

/**
 * @author Md. Sanowar Ali
 *
 */

@RestController
@RequestMapping("/api/sales-invoice")
public class SalesInvoiceController{
	
	@Autowired
	private SalesInvoiceService salesInvoiceService;
	
	@GetMapping("/grid-list")
	public Response gridList(HttpServletRequest request) {
		return salesInvoiceService.gridList(request);
	}
	
	@GetMapping("/list")
	public Response getAllList(@RequestBody(required = false) String reqObj) {
		return salesInvoiceService.getAllList(reqObj);
	}
	
	@GetMapping("/find-by-id")
	public Response findById(Long id) {
		return salesInvoiceService.findById(id);
	}
	
	@PostMapping("/create")
    public Response save(@RequestBody  String reqObj) {
        return salesInvoiceService.save(reqObj);
    }
	
	@PostMapping("/update")
    public Response update(@RequestBody  String reqObj) {
        return salesInvoiceService.update(reqObj);
    }
	
	@DeleteMapping("/delete")
	public Response delete(@RequestParam("id") long reqObj) {
		return salesInvoiceService.delete(reqObj);
	}
	
}
