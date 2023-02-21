package com.elib.controller;

import java.sql.Timestamp;
import java.util.Date;

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
import com.elib.model.Response;


@RestController
@RequestMapping("/user/")
public class UserController {
	Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	Userdboperations userdb;


	@GetMapping(value = "/verify/{user}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Response UserVerify(@PathVariable String user) {
		Response res = new Response();
		try {
			logger.info("user verify API Entry");
			logger.info("userid = " + Integer.parseInt(user));
			String password = userdb.getPassword(Integer.parseInt(user));
			if (password == null) {
				logger.info("user verify API Exit");
				res.setMessage("No data Found");
				res.setStatus(HttpStatus.NOT_FOUND);
				res.setDateTime(new Timestamp(new Date().getTime()));
				return res;

			} else {
				logger.info("user verify API Exit");
				res.setMessage("success");
				res.setStatus(HttpStatus.OK);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(new JSONObject().put("Result", password));
				return res;
			}
		} catch (NumberFormatException e) {
			logger.error(e);
			logger.info("user verify API Exit");
			res.setMessage("data fetching not successfull");
			res.setStatus(HttpStatus.NOT_FOUND);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		} catch (Exception e) {
			logger.error(e);
			logger.info("user verify API Exit");
			res.setMessage("data fetching not successfull");
			res.setStatus(HttpStatus.NOT_FOUND);
			res.setDateTime(new Timestamp(new Date().getTime()));
			return res;
		}

	}
}
