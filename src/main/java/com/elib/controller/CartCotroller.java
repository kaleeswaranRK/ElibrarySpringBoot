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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elib.dao.Userdboperations;
import com.elib.model.BookCart;
import com.elib.model.Response;

@RestController
@RequestMapping("/cart")
public class CartCotroller {
	@Autowired
	Userdboperations userdb;

	Logger logger = LogManager.getLogger(CartCotroller.class);

	@GetMapping(value = "/cartview/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCartItems(@PathVariable String user) {
		Response res= new Response();
		try {
			logger.info("cart view API Entry");
			List<BookCart> books = userdb.getCartItems(Integer.parseInt(user));
			if (books==null||books.isEmpty()) {
				logger.info("data fetching not successfull");
				logger.info("cart view API Exit");
				res.setMessage("No data Found");
				res.setStatus(HttpStatus.NOT_FOUND);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(null);
				return res;
			} else {
				logger.info("data fetching successfull");
				logger.info("cart view API Exit");
				res.setMessage("success");
				res.setStatus(HttpStatus.OK);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(new JSONObject().put("Result", books));
				return res;
			}

		} catch (Exception e) {
			logger.error(e);
			logger.info("data fetching not successfull");
			res.setMessage("data fetching not successfull");
			res.setStatus(HttpStatus.NOT_FOUND);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		}
	}
	
	@PostMapping(value = "/addtocart", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addToCart(@RequestBody BookCart book){
		Response res= new Response();
		try {
			logger.info("add to cart API Entry");
			logger.info(book.getBook());
			boolean cartAdd = userdb.cartAddCount(book.getBook(), book.getQuantity(),book.getPrice(), book.getUser());
				if (cartAdd) {
					logger.info("book added successfully");
					logger.info("add to cart API Exit");
					res.setMessage("success");
					res.setStatus(HttpStatus.OK);
					res.setDateTime(new Timestamp(new Date().getTime()));
					res.setData(new JSONObject().put("Result", "true"));
					return res;
				} else {
					logger.info("book not added to cart");
					res.setMessage("book not added to cart");
					res.setStatus(HttpStatus.NOT_ACCEPTABLE);
					res.setDateTime(new Timestamp(new Date().getTime()));
					return res;
				}
			}
		 catch (Exception e) {
			logger.error(e);
			logger.info("book not added to cart");
			res.setMessage("book not added to cart");
			res.setStatus(HttpStatus.NOT_ACCEPTABLE);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		}
	}

	@PostMapping(value = "/cartremove", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response cartRemove(@RequestBody BookCart book) {
		Response res= new Response();
		try {
			if (userdb.cartRemove(book.getBook(),book.getUser())) {
				logger.info("success");
				res.setMessage("book removed successfully");
				res.setStatus(HttpStatus.OK);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(new JSONObject().put("Result", "true"));
				return res;
			} else {
				logger.info("book not remove from cart");
				res.setMessage("book not remove from cart");
				res.setStatus(HttpStatus.NOT_ACCEPTABLE);
				res.setDateTime(new Timestamp(new Date().getTime()));
				return res;
			}
		} catch (Exception e) {
			logger.error(e);
			res.setMessage("book not remove from cart");
			res.setStatus(HttpStatus.NOT_ACCEPTABLE);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		}
	}
	
	@PostMapping(value = "/cartreduce",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response cartReduce(@RequestBody BookCart book) {
		Response res= new Response();
		try {
			boolean result = userdb.CartReduce(book.getBook(), book.getQuantity(), book.getUser());
				if (result) {
					logger.info("book reduced successfully");
					res.setMessage("book reduced successfully");
					res.setStatus(HttpStatus.OK);
					res.setDateTime(new Timestamp(new Date().getTime()));
					res.setData(new JSONObject().put("Result", "true"));
					return res;
				} else {
					logger.info("book not reduced");
					res.setMessage("book not reduced");
					res.setStatus(HttpStatus.NOT_ACCEPTABLE);
					res.setDateTime(new Timestamp(new Date().getTime()));
					return res;
				}
			}
		 catch (Exception e) {
			logger.error(e);
			res.setMessage("book not reduced");
			res.setStatus(HttpStatus.NOT_ACCEPTABLE);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		}
	}

}
