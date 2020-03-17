package com.aston.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			@RequestParam(name = "size", defaultValue = "5") int s,
			@RequestParam(name = "mc", defaultValue = "") String keyword)
	{
		
		Page<Product> pageProducts = productRepository.search("%" + keyword + "%", PageRequest.of(p, s));
		
		model.addAttribute("listProduits", pageProducts.getContent());
		int[] pages = new int[pageProducts.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("mc", keyword);
		return "produits";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Long id, int page, int size, 
			@RequestParam(name = "mc", defaultValue = "") String keyword) {
		productRepository.deleteById(id);
		return "redirect:/index?page=" + page + "&size=" + size + "&mc=" + keyword;
	}
}
