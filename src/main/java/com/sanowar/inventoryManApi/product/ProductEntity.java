package com.sanowar.inventoryManApi.product;

import java.io.Serializable;

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
@Table(name = "product")
public class ProductEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "product_code")
	private String productCode;
	
	@NotNull
	@Column(name = "product_name")
	private String productName;
	 
	@NotNull
	@Column(name = "selling_price")
	private Double sellingPrice;
	
	  
}
