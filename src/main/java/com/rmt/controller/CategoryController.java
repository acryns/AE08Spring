package com.rmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmt.model.Category;
import com.rmt.repository.CategoryRepository;
import com.rmt.services.ImportCategoryJson;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ImportCategoryJson importJson;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody Iterable<Category> categoryList(Model model) {
		return categoryRepository.findAll();
	}

	@RequestMapping(value = "/category/{id}")
	public String category(@PathVariable Integer id, Model model) {
		model.addAttribute("category", categoryRepository.findOne(id));
		return "category";
	}

	@RequestMapping(value = "/loadcategories", method = RequestMethod.POST)
	public @ResponseBody void load(Model model) {
		importJson.saveCategoryFromJson(System.getProperty("user.dir") + "\\import\\categories.json");
	}
}
