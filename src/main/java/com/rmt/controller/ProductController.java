package com.rmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmt.model.Product;
import com.rmt.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/product/{id}")
	public String product(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productRepository.findOne(id));
		return "product";
	}

	@RequestMapping(value = "/listproducts", method = RequestMethod.GET)
	public @ResponseBody Iterable<Product> productsList(Model model) {
/*		model.addAttribute("products", productRepository.findAll());
		return "products";*/
		return productRepository.findAll();
	}

	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	@ResponseBody
	public String saveProduct(@RequestBody Product product) {
		productRepository.save(product);
		return product.getProductId().toString();
	}
}
