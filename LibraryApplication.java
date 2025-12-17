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
	
	private User user;
	private Library library;
	
	
	// Main Application Logic, call this in your Main.java
	public void start() {
		Scanner sc = new Scanner(System.in);
		// initial user creation
		this.user = new User(0, null);

		// initial library creation
		this.library = new Library();
		
		// add code here

		String choice = "";
        do {
            printMenu();
            choice = sc.nextLine();
            //choice = readIntSafe(sc); // no exceptions

            if (Integer.parseInt(choice) == 1) {
                System.out.println("Option 1");
                library.displayAllBooks();

            } else if (Integer.parseInt(choice) == 2) {
            	System.out.println("Option 2");
            	library.displayAvailableBooks();

            } else if (Integer.parseInt(choice) == 3) {
            	System.out.println("Option 3");
            	library.displayBorrowedBooks();

            } else if (Integer.parseInt(choice) == 4) {
            	System.out.println("Option 4");
            	library.borrowBook(this.user, sc);

            } else if (Integer.parseInt(choice) == 5) {
                // Return Book
            	System.out.println("Option 5");
            	library.returnBook(sc);
            }
              else if (Integer.parseInt(choice) == 6) {
            	  System.out.println("Exiting Application");
            	  break;
            } else {
                System.out.println("Invalid choice. Please select 1-6.");
            }

            System.out.println(); // spacing
        } while (Integer.parseInt(choice) != 6);

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
	        System.out.println(" [6] Exit");
	        System.out.print("Your choice: ");
	    }
	
}
