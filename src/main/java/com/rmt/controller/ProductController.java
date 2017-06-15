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
import com.rmt.services.ImportProductJson;

@Controller
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImportProductJson importJson;

	@RequestMapping("/product/{id}")
	public String product(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productRepository.findOne(id));
		return "product";
	}

	@RequestMapping(value = "/listproducts", method = RequestMethod.GET)
	public @ResponseBody Iterable<Product> productsList() {
		return productRepository.findAll();
	}

	@RequestMapping(value = "/loadproducts", method = RequestMethod.POST)
	public @ResponseBody void load() {
		importJson.saveProductfromJson(System.getProperty("user.dir") + "\\import\\products.json");
	}

	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	@ResponseBody
	public String saveProduct(@RequestBody Product product) {
		productRepository.save(product);
		return product.getProductId().toString();
	}

	@RequestMapping(value = "/removeproduct", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeProduct(@RequestBody Product prod) {
		productRepository.delete(prod);
		return true;
	}
}
