package com.bpi.oopGproject;


public class Library {
	   // can contain up to 5 books	

    private Book[] books = new Book[5];
    private Loan[] loans = new Loan[5];

    public void addBook(Book book, int slot) {
        if (slot >= 0 && slot < books.length) {
            books[slot] = book;
        }
    }

    public void displayAllBooks() {
        for (Book b : books) {
            if (b != null) {
                System.out.println("ID: " + b.getId() + " | Title: " + b.getTitle() + " | Author: " + b.getAuthor());
            }
        }
    }

    public void displayAvailableBooks() {
        for (Book b : books) {
            if (b != null && !b.isBorrowed()) {
                System.out.println("Available -> " + b.getTitle());
            }
        }
    }

    public void displayBorrowedBooks() {
    	int count = 0;
    	
    	for (Loan l : loans) {
            if (l != null) {
                System.out.println("Borrowed -> " + l.getBook().getTitle() + " by " + l.getUser().getName());
                count++;
            }
        }
    	
    	 if (count == 0) {
    	        System.out.println("No borrowed books.");
    	    }
    }

    public void borrowBook(int bookId, User user) {
        for (Book b : books) {
            if (b != null && b.getId() == bookId && !b.isBorrowed()) {
                b.setBorrowed(true);
                for (int i = 0; i < loans.length; i++) {
                    if (loans[i] == null) {
                        loans[i] = new Loan(i + 1, user, b);
                        System.out.println(user.getName() + " borrowed " + b.getTitle());
                        return;
                    }
                }
            }
        }
        System.out.println("Book not available or loan slots full.");
    }

    public void returnBook(int loanId) {
        for (int i = 0; i < loans.length; i++) {
            if (loans[i] != null && loans[i].getId() == loanId) {
                loans[i].getBook().setBorrowed(false);
                System.out.println("Returned -> " + loans[i].getBook().getTitle());
                loans[i] = null;
                return;
            }
        }
        System.out.println("Loan ID not found.");
    }
}
