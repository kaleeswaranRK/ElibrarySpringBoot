package com.elib.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
public class ResponseHandler {

	public ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);
		map.put("Date", new Timestamp(new Date().getTime()));
		return new ResponseEntity<Object>(map, status);
	}
}
