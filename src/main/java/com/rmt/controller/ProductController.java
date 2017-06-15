package com.rmt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmt.model.Product;
import com.rmt.repository.ProductRepository;

@Controller
public class ProductController  {

	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/product/{id}")
	public String product(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productRepository.findOne(id));
		return "product";
	}

	@RequestMapping(value = "/listproducts", method = RequestMethod.GET)
	public @ResponseBody Iterable<Product> productsList(Model model) {
		saveProductfromJson(System.getProperty("user.dir") + "\\import\\products.json");
		return productRepository.findAll();
	}

	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	@ResponseBody
	public String saveProduct(@RequestBody Product product) {
		productRepository.save(product);
		return product.getProductId().toString();
	}
	
	private void saveProductfromJson(String path) {
		System.out.println("Importing json from filesystem ...");
    	try {
			ObjectMapper mapper = new ObjectMapper();
			//JSON from file to Object
			Product [] products = mapper.readValue(new File(path), Product[].class);
			for (Product p : products) {
					System.out.println(p.toString());
					productRepository.save(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
