package com.elib.model;

import java.sql.Timestamp;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public class Response {
	private String message;
	private String status;
	private String data;
	private String dateTime;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = String.valueOf(status.value());
	}

	public String getData() {
		return data;
	}

	public void setData(JSONObject json) {
		this.data = json.toString();
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = String.valueOf(dateTime);
	}
}
