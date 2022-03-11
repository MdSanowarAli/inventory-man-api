package com.sanowar.inventoryManApi.salesInvoice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Md. Sanowar Ali
 *
 */

@Getter
@Setter
@Entity
@Table(name = "sales_invoice")
public class SalesInvoiceEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@NotNull
	@Column(name = "invoice_date")
	private Date invoiceDate;
	
	@NotNull
	@Column(name = "customer_name")
	private String customerName;
	 
	@NotNull
	@Column(name = "total_amount")
	private Double totalAmount;
	
	  
}
