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
import java.util.Scanner;

public class LibraryApplication {
	
	private String name;
    private int id;
	private Library library;
	private User currentUser;
	
	
	// Main Application Logic, call this in your Main.java
	public void start() {
		
		this.id = 1;
        this.name = "akoto";
        this.currentUser = new User(this.id, this.name);
		Scanner sc = new Scanner(System.in);
		// initial user creation
		//this.user = new User(0, null);

		// initial library creation
		this.library = new Library();
		
		// add code here

		String choice = "";
        do {
            printMenu();
            choice = sc.nextLine();

            if (Integer.parseInt(choice) == 1) {
                System.out.println("Option 1");
                library.displayMinimal("");
            } else if (Integer.parseInt(choice) == 2) {
            	System.out.println("Option 2");
            	library.displayAvailableBooks();
            } else if (Integer.parseInt(choice) == 3) {
            	System.out.println("Option 3");
            	library.displayBorrowedBooks();
            } else if (Integer.parseInt(choice) == 4) {
            	System.out.println("Option 4");
            	library.borrowBook(this.currentUser, sc);
            } else if (Integer.parseInt(choice) == 5) {
                // Return Book
            	System.out.println("Option 5");
            	library.returnBook(sc);
            } else if (Integer.parseInt(choice) == 6) {
                // Add Book
            	System.out.println("Option 6");
            	library.addBook(sc);
            } else if (Integer.parseInt(choice) == 7) {
                // Remove Book
            	System.out.println("Option 7");
            	library.removeBook(sc);
            } else if (Integer.parseInt(choice) == 8) {
                // Update Book
            	System.out.println("Option 8");
            	library.updateBook(sc);
            } else if (Integer.parseInt(choice) == 0) {
            	  System.out.println("Exiting Application");
            	  break;
            } else {
                System.out.println("Invalid choice. Please select 1-8.");
            }

            System.out.println(); // spacing
        } while (Integer.parseInt(choice) != 0);

        sc.close();

	}
	
	// add code here
	 private void printMenu() {
	        System.out.println("\nSelect an option:");
	        System.out.println(" [1] Display All Books");
	        System.out.println(" [2] Display Available Books");
	        System.out.println(" [3] Display All Borrowed Books");
	        System.out.println(" [4] Borrow Book");
	        System.out.println(" [5] Return Book");
	        System.out.println(" [6] Add Book");
	        System.out.println(" [7] Remove Book");
	        System.out.println(" [8] Update Book");
	        System.out.println(" [0] Exit");
	        System.out.print("Your choice: ");
	    }
	
}
