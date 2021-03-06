package com.texas.CustomerBehaviorSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST)
	public 	ResponseEntity<?> save(@RequestBody Product product){
		productService.save(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Product>> findById(@PathVariable Long id){
		Optional<Product> product = productService.findById(id);
		if(product == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(product, HttpStatus.FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Product> findByName (@RequestParam String name){
		
		Product product = productService.findByName(name);
		if(product == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(product,HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/product_list", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products = productService.findAll();
		if(products == null || products.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}
	
	
	

}
