package com.elib.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elib.dao.Userdboperations;
import com.elib.model.BookProduct;
import com.elib.model.Category;
import com.elib.model.Response;
import com.elib.util.ResponseHandler;

@RestController
@RequestMapping("/product")
public class BooksController {
	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;
	Logger logger = LogManager.getLogger(BooksController.class);

	@GetMapping(value = "/getcategories", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCategory(){
		Response res= new Response();
		try {
			logger.info("user get Categories API Entry");
			List<Category> categories = userdb.getCategories();
			if (categories==null||categories.isEmpty()) {
				logger.info("user get Categories API Exit");
				res.setMessage("No data Found");
				res.setStatus(HttpStatus.NOT_FOUND);
				res.setDateTime(new Timestamp(new Date().getTime()));
				return res;
			} else {
				logger.info("user get Categories API Exit");
				res.setMessage("success");
				res.setStatus(HttpStatus.OK);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(new JSONObject().put("Result", categories));
				return res;
			}

		} catch (Exception e) {
			logger.error(e);
			logger.info("user get Categories API Exit");
			res.setMessage("data fetching not successfull");
			res.setStatus(HttpStatus.NOT_FOUND);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		}
	}

	@GetMapping(value = "/getbooks/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getBooks(@PathVariable String category) {
		Response res= new Response();
		try {
			logger.info("get Books API Entry");
			List<BookProduct> books = userdb.getBooksbyCategory(category);
			if (books==null || books.isEmpty()) {
				logger.info("get Books API Exit");
				res.setMessage("No data Found");
				res.setStatus(HttpStatus.NOT_FOUND);
				res.setDateTime(new Timestamp(new Date().getTime()));
				return res;
			} else {
				logger.info("get Books API Exit");
				res.setMessage("success");
				res.setStatus(HttpStatus.OK);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(new JSONObject().put("Result", books));
				return res;
			}

		} catch (Exception e) {
			logger.error(e);
			logger.info("get Books API Exit");
			res.setMessage("data fetching not successfull Exception occured");
			res.setStatus(HttpStatus.NOT_ACCEPTABLE);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		}
	}
}
