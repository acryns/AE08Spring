package com.rmt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmt.model.Category;
import com.rmt.repository.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody
	Iterable<Category>  categoryList(Model model) {
		return categoryRepository.findAll();
	}
	
	@RequestMapping(value = "/category/{id}")
	public String category(@PathVariable Integer id, Model model) {
		model.addAttribute("category", categoryRepository.findOne(id));
		return "category";
	}
	
	public void saveProductfromJson(String path) {
		System.out.println("Importing json from filesystem ...");
    	try {
			ObjectMapper mapper = new ObjectMapper();
			//JSON from file to Object
			Category [] categories = mapper.readValue(new File(path), Category[].class);
			System.out.println(categories.toString());
			for (Category c : categories) {
					categoryRepository.save(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/loadcategories", method = RequestMethod.POST)
	public @ResponseBody void load(Model model) {
		saveProductfromJson(System.getProperty("user.dir") + "\\import\\categories.json");
	}
}
