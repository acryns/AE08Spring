package com.rmt.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmt.model.CustomerOrder;
import com.rmt.repository.OrderRepository;

@Service
public class ImportOrderJson {

	@Autowired
	OrderRepository or;

	public void saveOrderfromJson(String path) {
		System.out.println("Importing json from filesystem ...");
		try {
			ObjectMapper mapper = new ObjectMapper();
			// JSON from file to Object
			CustomerOrder[] orders = mapper.readValue(new File(path), CustomerOrder[].class);
			for (CustomerOrder p : orders) {
				System.out.println(p.toString());
				or.save(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
