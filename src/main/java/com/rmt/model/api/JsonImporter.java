package com.rmt.model.api;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmt.controller.ProductController;
import com.rmt.model.Product;
import com.rmt.repository.ProductRepository;


public class JsonImporter {

	@Autowired
	private ProductRepository pr;
	
	ProductController pc = new ProductController();
	
	
	
		public void  importJson(String file) throws JsonParseException, IOException {

			// create JsonParser object
//			JsonParser jsonParser = new JsonFactory().createParser(new File(file));

			// loop through the tokens
//			Product product = new Product();
//			product.setProductName(new String());
////			product.setProductPrice(new Double());
//			product.setProductDescription(new String());
//			boolean insidePropertiesObj = false;

			ObjectMapper mapper = new ObjectMapper();
			//JSON from file to Object
			Product [] products = mapper.readValue(new File(file), Product[].class);

			
			for (Product product : products) {
					System.out.println(product.toString());
//					jsonParser.nextToken();
//				}
//			pr.save(product);
//				pc.saveJson(product);

				
			}
//			jsonParser.close();
			
			
//				product.setProductName(obj[0].getProductName());
//				product.setProductPrice(obj[0].getProductPrice());
//				product.setProductDescription(obj[0].getProductDescription());
//				System.out.println(product.toString());
//				pr.save(product);		

//			pc.saveProduct(product);

//			//JSON from URL to Object
//			Product obj = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Product.class);

			//JSON from String to Object
//			Product obj = mapper.readValue(file, Product.class);
//			parseProductJSON(jsonParser, pr);
//			pr.save(obj);
			



		}



	
}
