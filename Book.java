package com.bpi.oopGproject;

public class Book {

	// adding this "id" to give you an idea on what options you can do
	private Integer id;
	private String title;
	private String author;
	private boolean borrowed;
	// feel free to add fields that may help

	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.borrowed = false;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
}
