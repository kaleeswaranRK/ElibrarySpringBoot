package com.elib.model;

public class BookProduct {
	private int bookID;
	private String bookName;
	private String authorName;
	private int bookQuantity;
	private double bookPrice;
	private int category;

	
	public BookProduct(int bookID, String bookName, String authorName, int bookQuantity, double bookPrice,
			int category) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookQuantity = bookQuantity;
		this.bookPrice = bookPrice;
		this.category = category;
	}

	@Override
	public String toString() {
		return "BookProduct [bookID=" + bookID + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", bookQuantity=" + bookQuantity + ", bookPrice=" + bookPrice + ", category=" + category + "]";
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getcategory() {
		return category;
	}

	public void setcategory(int category) {
		this.category = category;
	}
}
