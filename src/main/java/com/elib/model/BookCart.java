package com.elib.model;

public class BookCart {
	private int bookCartId;
	private String book;
	private int quantity;
	private double price;
	private int user;

	@Override
	public String toString() {
		return "BookCart [bookCartId=" + bookCartId + ", book=" + book + ", quantity=" + quantity + ", price=" + price
				+ ", userId=" + user + "]";
	}

	public BookCart() {
		// TODO Auto-generated constructor stub
	}

	public BookCart(String book, int quantity, double price, int user) {
		this.book = book;
		this.quantity = quantity;
		this.price = price;
		this.user = user;
	}

	public int getBookCartId() {
		return bookCartId;
	}

	public void setBookCartId(int bookCartId) {
		this.bookCartId = bookCartId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
}
