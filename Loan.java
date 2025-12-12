package com.bpi.oopGproject;

public class Loan {

	private User user;
	private Book book;
	private int id;

	public Loan(int id, User user, Book book) {
		this.id = id;
		this.user = user;
		this.book = book;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}
}
