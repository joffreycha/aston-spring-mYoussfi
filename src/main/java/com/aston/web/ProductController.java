package com.aston.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.aston.dao.ProductRepository;
import com.aston.entities.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value = "/index")
	public String index(Model model, 
			@RequestParam(name = "page", defaultValue = "0")  int p, 
			@RequestParam(name = "size", defaultValue = "5") int s) {
		
		Page<Product> pageProduits = productRepository.findAll(PageRequest.of(p, s));
		
		model.addAttribute("listProduits", pageProduits.getContent());
		return "produits";
	}
}
