package com.bpi.oopGproject;

import java.util.*;

/*
 * 1. Upon application start, ask user to create one User
 * 2. Create one Library object
 * 3. Initialize 5 Book objects and add it to all Library slots
 * 4. Display options:
 * 
 * - [1] Display All Books
 * - [2] Display Available Books
 * - [3] Display All Borrowed Books
 * - [4] Borrow Book
 * - [5] Return Book
 * - [6] Exit
 * 
 * - user selects the number of the option
 * ===============================================
 * 
 *	 [1] Display All Books
 * - Display all Books (ID, Title and Author) regardless if there is a Loan existing for that Book.
 *   
 *   [2] Display Available Books
 * - Display Books that do not have a Loan slot
 * 
 *   [3] Display All Borrowed Books 
 * - Display Books that have a Loan equivalent.
 * - Display the Book title and the User name of borrower
 *   
 *	 [4] Borrow Book
 * - Displays all available books and User selects what book to borrow
 * - Create a Loan object, set Loan id set Book and set User to current user
 * 
 * 	 [5] Return Book
 * - Display all Loans, user selects the Loan and removes that from the slot
 * 
 *   [6] Exit
 * - Stops the program  
 * */

public class LibraryApplication {

	private User user;
	private Library library;

	public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Library!");
        System.out.println();
        System.out.print("Enter User name: ");
		String name = sc.nextLine();
		this.user = new User(name, 1);

		this.library = new Library();

		library.addBook(new Book(12, "Goodnight Moon", "Margaret Wise Brown"), 0);
		library.addBook(new Book(232, "Charlotte’s Web", "E. B. White"), 1);
		library.addBook(new Book(342, "Junie B. Jones series", "Barbara Park"), 2);
		library.addBook(new Book(443, "Amelia Bedelia series", "Peggy Parish"), 3);
		library.addBook(new Book(565, "The Gruffalo", "Julia Donaldson"), 4);

		while (true) {
			System.out.println("\nOptions:");
			System.out.println("[1] Display All Books");
			System.out.println("[2] Display Available Books");
			System.out.println("[3] Display All Borrowed Books");
			System.out.println("[4] Borrow Book");
			System.out.println("[5] Return Book");
			System.out.println("[6] Exit");

			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				library.displayAllBooks();
				break;
			case 2:
				library.displayAvailableBooks();
				break;
			case 3:
				library.displayBorrowedBooks();
				break;
			case 4:
				System.out.print("Enter Book ID to borrow: ");
				int bookId = sc.nextInt();
				library.borrowBook(bookId, user);
				break;
			case 5:
				System.out.print("Enter Loan ID to return: ");
				int loanId = sc.nextInt();
				library.returnBook(loanId);
				break;
			case 6:
				System.out.println("Exiting...");
				sc.close();
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
	}
}
