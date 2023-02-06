package com.elib.util;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyCheck {
	long lastModified;
	Logger logger = LogManager.getLogger(PropertyCheck.class);

	public void lastmodifyIntialization(File file) {
		
		if (lastModified == 0) {
			lastModified = file.lastModified();
		} else {
			logger.info("last modified already assingned");
		}
	}

	public boolean propertyFileCheck(File file) {
		if (file.lastModified() != lastModified) {
			return true;
		} else {
			return false;
		}
	}
}
