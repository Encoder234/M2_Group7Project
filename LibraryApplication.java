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
 * 
 * 
 * How to use Array.....
 
// Creates an array with fixed length 5..
Book[] bookArray = new Book[5]; 
 
// Sample initialization
bookArray[0] = new Book("Clean Code");
bookArray[1] = new Book("Effective Java");
// ... and so on up to index 4
 
// Sample code for accessing an item in the array.
System.out.println(bookArray[0].getTitle() ); // Works after initialization
 * */
import java.util.Scanner;
public class LibraryApplication {
	private Scanner sc = new Scanner(System.in);

	// Main Application Logic, call this in your Main.java
	public void start() {
		//5 Default books
		Book[] books = {
				new Book(1,"1984", "George Orwell"),
				new Book(2,"To Kill a Mockingbird", "Harper Lee"),
				new Book(3,"The Great Gatsby", "F. Scott Fitzgerald"),
				new Book(4,"Moby Dick", "Herman Melville"),
				new Book(5,"Pride and Prejudice", "Jane Austen")
		};

		//Create user
		System.out.println("Enter user name:");
		User user = new User(sc.nextLine()); //Set the new user
		System.out.println(user);

		//Set the library with defined books
		Library library = new Library(books);
		//Menu
		int choice;
		do{
			System.out.println("[1] Display All Books\n"
					+ "[2] Display Available Books\n"
					+ "[3] Display All Borrowed Books\n"
					+ "[4] Borrow Book\n"
					+ "[5] Return Book\n"
					+ "[6] Exit");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();

			//SWITCH CASE
			switch (choice) {
				case 1:
					library.displayAllBook();
					break;
				case 2:
					library.displayAvailableBooks();
					break;
				case 3:
					library.displayBorrowedBooks();
					break;
				case 4:
					System.out.print("Enter book id : ");
					library.borrowBook(sc.nextInt());
					break;
				case 5:{
					System.out.print("Enter book id : ");
					library.returnBook(sc.nextInt());
					break;
				}
				case 6: System.out.println("Goodbye!");
					break;
				default: System.out.println("Invalid choice.");
			} // END OF SWITCH CASE
		} while(choice != 6); //END OF DO-WHILE LOOP
	}
}
