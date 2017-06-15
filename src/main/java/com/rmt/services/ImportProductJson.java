package com.rmt.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmt.model.Product;
import com.rmt.repository.ProductRepository;

@Service
public class ImportProductJson {

	@Autowired
	ProductRepository pr;

	public void saveProductfromJson(String path) {
		System.out.println("Importing json from filesystem ...");
		try {
			ObjectMapper mapper = new ObjectMapper();
			// JSON from file to Object
			Product[] products = mapper.readValue(new File(path), Product[].class);
			for (Product p : products) {
				System.out.println(p.toString());
				pr.save(p);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
