package com.rmt.controller;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmt.model.CustomerOrder;
import com.rmt.model.Product;
import com.rmt.repository.OrderRepository;
import com.rmt.repository.ProductRepository;
import com.rmt.services.ImportOrderJson;

@Controller
public class OrderController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ImportOrderJson importJson;

	@RequestMapping(value = "/listorders", method = RequestMethod.GET)
	public @ResponseBody Iterable<CustomerOrder> orderList() {
		return orderRepository.findAll();
	}

	@RequestMapping(value = "/createorder", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrder(@RequestParam(value = "productIds[]") Integer[] productIds) {

		CustomerOrder customerOrder = new CustomerOrder();
		Set<Product> productSet = new HashSet<Product>();
		for (Integer productId : productIds) {
			productSet.add(productRepository.findOne(productId));
		}
//		customerOrder.setProducts(productSet);
		Double total = 0.0;
		for (Integer productId : productIds) {
			total = total + (productRepository.findOne(productId).getProductPrice());
		}

		Time time = new Time(Calendar.getInstance().getTime().getTime());
		Date day = new Date();
		customerOrder.setDate(day);
//		customerOrder.setTime(time);
		orderRepository.save(customerOrder);

		return customerOrder.getOrderId().toString();
	}

	@RequestMapping(value = "/removeorder", method = RequestMethod.POST)
	@ResponseBody
	public String removeOrder(@RequestParam Integer Id) {
		orderRepository.delete(Id);
		return Id.toString();
	}
	
	@RequestMapping(value = "/loadorders", method = RequestMethod.POST)
	public @ResponseBody void load() {
		importJson.saveOrderfromJson(System.getProperty("user.dir") + "\\import\\orders.json");
	}

}
