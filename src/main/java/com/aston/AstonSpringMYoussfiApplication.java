package com.aston;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.aston.dao.ProductRepository;
import com.aston.entities.Product;

@SpringBootApplication
public class AstonSpringMYoussfiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(AstonSpringMYoussfiApplication.class, args);
		ProductRepository productRepository = ctx.getBean(ProductRepository.class);
		productRepository.save(new Product("LX 4352", 670, 90));
		productRepository.save(new Product("Ordinateur HP 432", 670, 90));
		productRepository.save(new Product("Imprimante Epson", 450, 12));
		productRepository.save(new Product("Imp HP 5400", 45, 10));
		
		productRepository.findAll().forEach(p -> System.out.println(p.getDescription()));
	}

}
