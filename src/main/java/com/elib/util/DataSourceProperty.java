package com.elib.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceProperty {
	@Autowired
	PropertyCheck propertycheck;
	static BasicDataSource dataSource;
	static File file = new File("C:\\Users\\User\\Desktop\\Kaleeswaran\\ElibraryFiles\\prop.properties");
	Logger logger = LogManager.getLogger(DataSourceProperty.class);

	public BasicDataSource getDataSource() {
		try {
			if (dataSource == null) {
				Properties prop = new Properties();
				prop.load(new FileInputStream(file));
				BasicDataSource ds = new BasicDataSource();
				ds.setDriverClassName(prop.getProperty("driverjdbc"));
				ds.setUrl(prop.getProperty("connectionUrl"));
				ds.setUsername(prop.getProperty("dbusername"));
				ds.setPassword(prop.getProperty("dbpassword"));
				logger.info("driverjdbc = " + ds.getDriverClassName() + "  connectionUrl = " + ds.getUrl()
						+ "  dbusername = " + ds.getUsername() + "  dbpassword = " + ds.getPassword());
				ds.setMinIdle(5);
				ds.setMaxIdle(10);
				ds.setMaxTotal(25);
				dataSource=ds;
				return dataSource;
			} else {
				logger.info("data source already assigned");
				return dataSource;
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return dataSource;
	}

	public Connection getDBConnection() {
		try {
			propertycheck.lastmodifyIntialization(file);
			if (propertycheck.propertyFileCheck(file)) {
				Properties prop = new Properties();
				prop.load(new FileInputStream(file));
				BasicDataSource ds = new BasicDataSource();
				ds.setDriverClassName(prop.getProperty("driverjdbc"));
				ds.setUrl(prop.getProperty("connectionUrl"));
				ds.setUsername(prop.getProperty("dbusername"));
				ds.setPassword(prop.getProperty("dbpassword"));
				logger.info("driverjdbc = " + ds.getDriverClassName() + "  connectionUrl = " + ds.getUrl()
						+ "  dbusername = " + ds.getUsername() + "  dbpassword = " + ds.getPassword());
				ds.setMinIdle(5);
				ds.setMaxIdle(10);
				ds.setMaxTotal(25);
				dataSource=ds;
				return dataSource.getConnection();
			} else {
				return getDataSource().getConnection();
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	
}
