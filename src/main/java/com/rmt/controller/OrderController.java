package com.rmt.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmt.model.CustomerOrder;
import com.rmt.model.Product;
import com.rmt.repository.OrderRepository;
import com.rmt.repository.ProductRepository;

@Controller
public class OrderController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String productsList(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @RequestMapping(value="/createorder", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrder(@RequestParam(value="productIds[]") Integer[] productIds){

        CustomerOrder customerOrder = new CustomerOrder();
        Set<Product> productSet = new HashSet<Product>();
        for (Integer productId:productIds){
            productSet.add(productRepository.findOne(productId));
        }
        customerOrder.setProducts(productSet);
        Double total = 0.0;
        for (Integer productId:productIds){
            total = total + (productRepository.findOne(productId).getProductPrice());
        }
        
        Timestamp timestamp = new Timestamp(new Date().getTime());
        customerOrder.setCurrentTime(timestamp);
        orderRepository.save(customerOrder);

        return customerOrder.getOrderId().toString();
    }

    @RequestMapping(value = "/removeorder", method = RequestMethod.POST)
    @ResponseBody
    public String removeOrder(@RequestParam Integer Id){
        orderRepository.delete(Id);
        return Id.toString();
    }
}
