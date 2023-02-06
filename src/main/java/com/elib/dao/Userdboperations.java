package com.elib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elib.model.BookCart;
import com.elib.model.BookProduct;
import com.elib.model.Category;
import com.elib.util.DataSourceProperty;

@Service
public class Userdboperations {
	@Autowired
	DataSourceProperty datasource;
	Logger logger = LogManager.getLogger(Userdboperations.class);

	public String getPassword(int userid) {
		try {
			logger.info("get Password method Entry");
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement;
			prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_PASSWORD @id =?");
			prepareStatement.setInt(1, userid);
			ResultSet resultSet = prepareStatement.executeQuery();
			logger.info("query executed");
			if (resultSet.next()) {
				logger.info("get Password method Exit");
				return resultSet.getString("CUSTOMER_PASSWORD");
			} else {
				logger.info("get Password method Exit");
				return null;
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public String getUser(int userid) {
		try {
			logger.info("get Password method Entry");
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement;
			prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_PASSWORD @id =?");
			prepareStatement.setInt(1, userid);
			ResultSet resultSet = prepareStatement.executeQuery();
			logger.info("query executed");
			if (resultSet.next()) {
				logger.info("get Password method Exit");
				return resultSet.getString("CUSTOMER_PASSWORD");
			} else {
				logger.info("get Password method Exit");
				return null;
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public List<Category> getCategories() {
		try {
			logger.info("get Categories method Entry");
			List<Category> list = new ArrayList<Category>();
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_CATEGORIES");
			ResultSet result = prepareStatement.executeQuery();
			logger.info("query executed");
			while (result.next()) {
				Category category = new Category(result.getInt("CATEGORY_ID"), result.getString("CATEGORY_NAME"));
				list.add(category);
			}
			logger.info("get Categories method Exit");
			return list;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	
	public List<BookProduct> getBooksbyCategory(int categoryid) {
		try {
			logger.info("get book by Category method Entry");
			List<BookProduct> list = new ArrayList<BookProduct>();
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_GET_BOOKS_BY_CATEGORY @id=?");
			prepareStatement.setInt(1, categoryid);
			ResultSet result = prepareStatement.executeQuery();
			logger.info("query executed");
			if (result.next()) {
				while (result.next()) {
					BookProduct book = new BookProduct(result.getInt("BOOK_ID"), result.getString("BOOK_NAME"),
							result.getString("AUTHOR_NAME"), result.getInt("BOOK_QUANTITY"), result.getDouble("BOOK_PRICE"),
							categoryid);
					list.add(book);
				}
				logger.info("get book by Category method Exit");
				return list;
			}
			else {
				logger.info("No Data Found");
				return null;
			}
			
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BookCart> getCartItems(int userid) {
		try {
			logger.info("get cart items method Entry");
			List<BookCart> cartItems = new ArrayList<BookCart>();
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_CART_ITEMS @id=?");
			prepareStatement.setInt(1, userid);
			ResultSet result = prepareStatement.executeQuery();
			logger.info("query executed");
			
			while (result.next()) {
				BookCart cartItem = new BookCart(result.getInt("BOOK_CART_ID"), result.getString("BOOK_NAME"), result.getInt("BOOK_QUANTITY"),
						result.getDouble("BOOK_PRICE"), userid);
				cartItems.add(cartItem);
			}
			logger.info("get cart items method Exit");
			return cartItems;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public ResultSet checkCartItem(int userid, String bookName) {

		try {
			logger.info("checkCartItem method Entry");
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement;
			prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_CHECK_CART @id=?,@book=?");
			prepareStatement.setInt(1, userid);
			prepareStatement.setString(2, bookName);
			ResultSet result = prepareStatement.executeQuery();
			logger.info("query executed");
			logger.info("checkCartItem method Exit");

			return result;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}
	public Category getCategory(int categoryid) {
		try {
			logger.info("getCategory method Entry");
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_CATEGORY @id=?");
			prepareStatement.setInt(1, categoryid);
			ResultSet categoryresult = prepareStatement.executeQuery();
			if (categoryresult.next()) {
				Category category = new Category(categoryresult.getInt("CATEGORY_ID"),
						categoryresult.getString("CATEGORY_NAME"));
				logger.info("getCategory method Exit");
				return category;
			} else {
				return null;
			}

		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public BookProduct getBookByName(String bookName) {
		try {
			logger.info("getBookByName method Entry");

			BookProduct book = null;
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_GET_BOOK_BY_NAME @name=?");
			prepareStatement.setString(1, bookName);
			ResultSet result = prepareStatement.executeQuery();
			logger.info("query executed");
			if (result.next()) {
				book = new BookProduct(result.getInt("BOOK_ID"), result.getString("BOOK_NAME"),
						result.getString("AUTHOR_NAME"), result.getInt("BOOK_QUANTITY"), result.getDouble("BOOK_PRICE"),
						result.getInt("CATEGORY_ID"));
				logger.info("getBookByName method Exit");
				return book;
			} else {
				logger.info("getBookByName method Exit");
				logger.info("No book Data");
				return null;
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public boolean addToCart(String bookName, int bookQuantity, double bookPrice, int userid) {
		try {
			logger.info("addToCart method Entry");
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_ADD_TO_CART @name=? ,@quantity=? ,@price=? ,@id=?");
			prepareStatement.setString(1, bookName);
			prepareStatement.setInt(2, bookQuantity);
			prepareStatement.setDouble(3, bookPrice);
			prepareStatement.setInt(4, userid);
			if (prepareStatement.executeUpdate() < 1) {
				logger.info("query executed");
				logger.info("addToCart method Exit");
				return false;
			} else {
				logger.info("query executed");
				logger.info("addToCart method Exit");
				return true;
			}

		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	public boolean cartRemove(String bookname, int userid) {
		try {
			logger.info("cartRemove method Entry");
			Connection connection = datasource.getDBConnection();
			logger.info("DB connection Establised");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_DELETE_PRODUCT @name=?,@id=?");
			prepareStatement.setString(1, bookname);
			prepareStatement.setInt(2, userid);
			if (prepareStatement.executeUpdate() < 1) {
				logger.info("query executed");
				logger.info("cartRemove method Exit");
				return false;
			} else {
				logger.info("query executed");
				logger.info("cartRemove method Exit");
				return true;
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;

	}

	public boolean cartAddCount(String bookName, int Quantity, double Price, int userid) {
		try {
			logger.info("cartAddCount method Entry");
			ResultSet result = checkCartItem(userid, bookName);
			if (result.next()) {
				int bookCount = result.getInt("BOOK_QUANTITY") + Quantity;
				BookProduct bookByName = getBookByName(bookName);
				if (bookCount > bookByName.getBookQuantity()) {
					return false;
				} else {
					double bookPrice = bookCount * bookByName.getBookPrice();
					Connection connection = datasource.getDBConnection();
					logger.info("DB connection Establised");
					PreparedStatement prepareStatement = connection
							.prepareStatement("EXEC KALEESWARAN_MODIFY_CART @name=?,@id=?,@quantity=?,@price=?");
					prepareStatement.setString(1, bookName);
					prepareStatement.setInt(2, userid);
					prepareStatement.setInt(3, bookCount);
					prepareStatement.setDouble(4, bookPrice);
					if (prepareStatement.executeUpdate() < 1) {
						logger.info("query executed");
						logger.info("cartAddCount method Exit");
						return false;
					} else {
						logger.info("query executed");
						logger.info("cartAddCount method Exit");
						return true;
					}
				}

			} else {
				return addToCart(bookName, Quantity, Price, userid);
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;

	}

	public boolean CartReduce(String bookName, int Quantity, double Price, int userid) {
		try {
			logger.info("CartReduce method Entry");
			ResultSet result = checkCartItem(userid, bookName);
			if (result.next()) {
				int bookCount = result.getInt("BOOK_QUANTITY");
				BookProduct bookByName = getBookByName(bookName);
				if (bookCount - Quantity == 0) {
					logger.info("CartReduce method Exit");
					return cartRemove(bookName, userid);
				} else if (bookCount - Quantity < 0) {
					logger.info("CartReduce method Exit");
					return false;
				} else {
					double bookPrice = (bookCount - Quantity) * bookByName.getBookPrice();
					Connection connection = datasource.getDBConnection();
					logger.info("DB connection Establised");
					PreparedStatement prepareStatement = connection
							.prepareStatement("EXEC KALEESWARAN_MODIFY_CART @name=?,@id=?,@quantity=?,@price=?");
					prepareStatement.setString(1, bookName);
					prepareStatement.setInt(2, userid);
					prepareStatement.setInt(3, (bookCount - Quantity));
					prepareStatement.setDouble(4, bookPrice);
					if (prepareStatement.executeUpdate() < 1) {
						logger.info("query executed");
						logger.info("CartReduce method Exit");
						return false;
					} else {
						logger.info("query executed");
						logger.info("CartReduce method Exit");
						return true;
					}
				}
			} else {
				logger.info("CartReduce method Exit");
				return false;
			}

		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;

	}

}
