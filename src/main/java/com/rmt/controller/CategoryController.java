package com.rmt.controller;

import com.rmt.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rmt.repository.CategoryRepository;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
