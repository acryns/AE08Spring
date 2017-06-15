package com.rmt.services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmt.model.Category;
import com.rmt.repository.CategoryRepository;

@Service
public class ImportCategoryJson {
	
	@Autowired
	CategoryRepository cr;
	
	public void saveCategoryFromJson(String path) {
		System.out.println("Importing json from filesystem ...");
    	try {
			ObjectMapper mapper = new ObjectMapper();
			//JSON from file to Object
			Category [] categories = mapper.readValue(new File(path), Category[].class);

			for (Category c : categories) {
				System.out.println(c.toString());
				cr.save(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
