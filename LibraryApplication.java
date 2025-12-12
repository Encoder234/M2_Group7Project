package act.M2_Group7Project;

import java.util.Scanner;
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
	
	int choice;
	private Scanner scanner = new Scanner(System.in);
	
	// Main Application Logic, call this in your Main.java
	public void start() {
		// initial user creation
				
		String name = userName("Enter User Name: ");
		user = new User(name);
		// initial library creation
		library = new Library();
		
		library.initializeBooks();
		
		do {
            printMenu();
            choice = userChoice("User selects the number of the option: ");
            
            switch (choice) {
                case 1 -> displayAllBooks();
                case 2 -> displayAvailBooks();
                case 3 -> displayAllBorrowed();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> { 
                           System.out.println("Exiting... Thank you!"); }	                	
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // spacer
        } while (choice != 6 );
		
	}
	
    private void printMenu() {

		System.out.println("==== Library Application ====");
		System.out.println("[1] - Display All Books ");
		System.out.println("[2] - Display Available Books ");
		System.out.println("[3] - Display All Borrowed Books ");
		System.out.println("[4] - Borrow Books ");
		System.out.println("[5] - Return Books ");
		System.out.println("[6] - Exit ");
    }	
    
    
    private void displayAllBooks() {
    	library.displayAllBooks();
    }
    
    private void displayAvailBooks() {
    	library.displayAvailableBooks();
    }
    
    private void displayAllBorrowed() {
    	library.displayBorrowedBooks();
    }
    
    private void borrowBook() {
    	library.displayAvailableBooks();
        int id = userChoice("Enter Book ID: ");
        
        library.borrowBook(id, user); 
        
    }
    
    private void returnBook() {
    	library.displayBorrowedBooks();
    	int id = userChoice("Enter Book ID: ");

    	
    	 if (library.bookAvailable(id)) {
             System.out.println("Book not found.");
             return;
         }
    	 
    	 library.returnBook(id);
    }
    
   
    private int userChoice(String prompt) {
        String line;
        do {
            System.out.print(prompt);
            line = scanner.nextLine();
            if (line == null) line = "";
            line = line.trim();
            if (line.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (line.isEmpty());
        return Integer.parseInt(line);
    }
    
    private String userName(String prompt) {
        String line;
        do {
            System.out.print(prompt);
            line = scanner.nextLine();
            if (line == null) line = "";
            line = line.trim();
            if (line.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (line.isEmpty());
         return line;
    }
    
    
	
	
	
}
