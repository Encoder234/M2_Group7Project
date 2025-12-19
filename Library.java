import java.util.*;
import java.util.Scanner;


public class Library{
   // can contain up to 5 books	

	Book book1 = new Book();
	Book book2 = new Book();
	Book book3 = new Book();
	Book book4 = new Book();
	Book book5 = new Book();
	
	private int nextLoanId = 1;
	private List<Loan> activeLoans = new ArrayList<>(); 
	private List<Book> allBooksList = new ArrayList<>();

	public Library() {

        // Initialize book details here
        book1.setId(1);
        book1.setTitle("Intro to Java");
        book1.setAuthor("Homer M.");
        book1.setBorrowed(false);
        
        book2.setId(2);
        book2.setTitle("Data Structures");
        book2.setAuthor("Grace H.");
        book2.setBorrowed(false);
        
        book3.setId(3);
        book3.setTitle("OOP Programming");
        book3.setAuthor("John Wick");
        book3.setBorrowed(false);
        
        book4.setId(4);
        book4.setTitle("Kama Sutra");
        book4.setAuthor("Mang Kanor");
        book4.setBorrowed(false);
        
        book5.setId(5);
        book5.setTitle("The book of blah");
        book5.setAuthor("Ewan bahala.");
        book5.setBorrowed(true);
        
        allBooksList.add(book1);
        allBooksList.add(book2);
        allBooksList.add(book3);
        allBooksList.add(book4);
        allBooksList.add(book5);
        
       
    }
	
	private Book[] allBooks() {
        //return new Book[] { book1, book2, book3, book4, book5 };
		return allBooksList.toArray(new Book[0]);
    }
	
	public Book findBookById(int id) {
        for (Book b : allBooks()) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null; // Book not found
    }
	
	private Loan findLoanById(int id) {
        for (Loan loan : activeLoans) {
            if (loan.getId() == id) {
                return loan;
            }
        }
        return null;
    }
	


	private void printTableHeader() {
		System.out.println("-------------------------------- LIBRARY CATALOG -------------------------------------");
	    // Header Template: %-5s (ID), %-30s (Title), %-25s (Author), %-15s (Status)
	    System.out.printf("|   %-5s | %-30s | %-25s | %-11s |%n", "ID", "TITLE", "AUTHOR", "STATUS");
	    System.out.println("--------------------------------------------------------------------------------------");
	}
	
	private void printTableFooter() {
		System.out.println("--------------------------------------------------------------------------------------");
	}
	
	
	public void displayAllBooks() {
        System.out.println("ALL BOOKS:");
        Book[] books = allBooks();
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];

            System.out.println("  [" + b.getId() + "] " + String.format("%-20s", b.getTitle()) + " | by  "  + String.format("%-20s", b.getAuthor()));
            
        }
    }
	
	public void displayAvailableBooks() {
        System.out.println("ALL AVAILABLE BOOKS:");
        
        printTableHeader();
	    // Loop through your list of books
	    for (Book book : allBooks()) {
	        String status = book.isBorrowed() ? "BORROWED" : "AVAILABLE";
	        if (book.isBorrowed() == false) {
	        	System.out.printf("|   %-5d | %-30s | %-25s | %-11s |%n", 
	        			book.getId(), 
	        			book.getTitle(), 
	        			book.getAuthor(), 
	        			status);
	        }	    
        }
	    printTableFooter();
    }
	
	
	public void displayBorrowedBooks() {

        System.out.println("ALL BORROWED BOOKS:");
        printTableHeader();
	    // Loop through your list of books
	    for (Book book : allBooks()) {
	        String status = book.isBorrowed() ? "BORROWED" : "AVAILABLE";
	        if (book.isBorrowed() == true) {
	        	System.out.printf("|   %-5d | %-30s | %-25s | %-11s |%n", 
	        			book.getId(), 
	        			book.getTitle(), 
	        			book.getAuthor(), 
	        			status);
	        }	    
        }
	    printTableFooter();
    }
	
	public void borrowBook(User borrower, Scanner sc) {
		String input = null;
        
        do {
        	
        	displayAvailableBooks();
        	System.out.print("Enter the ID of the book to borrow: ");
        	String idChoice = sc.nextLine();
        	
        	if (!idChoice.matches("\\d+")) {
        	    System.out.println("❌ Error: Please enter a numeric ID.");
        	    continue; // Skips to the next iteration of the loop
        	}

        	int bookId = Integer.parseInt(idChoice);
        	Book chosenBook = findBookById(bookId);
        
            if (chosenBook == null) {
                System.out.println("Error: Invalid Book ID.");
            } else if (chosenBook.isBorrowed()) {
                System.out.println("Error: That book is already borrowed.");
            } else {
                // 1. Mark the Book as borrowed
                chosenBook.setBorrowed(true);
                
                // 2. Create the Loan object
                Loan newLoan = new Loan(nextLoanId++, chosenBook.getId(), borrower.getId(), "Borrowed on " + java.time.LocalDate.now().toString());
                
                // 3. Add the Loan to the list
                activeLoans.add(newLoan);
                
                System.out.println("Success! " + chosenBook.getTitle() + " borrowed by " + borrower.getName());
                displayMinimal(borrower.toString());  
        	    }
            
            System.out.println("\n Do you wnat to update another book info? [y/n]:");    
            input = sc.nextLine();	
        } while (input.equalsIgnoreCase("y")); 
    }
	
	public void returnBook(Scanner sc) {
        if (activeLoans.isEmpty()) {
            System.out.println("No books are currently on loan to return.");
            return;
        }

        displayBorrowedBooks(); // Show the list of current loans
        
        System.out.print("Enter the ID of the Loan to return: ");
        String idChoice = sc.nextLine();
        
            int loanId = Integer.parseInt(idChoice);
            Loan loanToRemove = findLoanById(loanId);
            
            if (loanToRemove != null) {
                Book returnedBook = findBookById(loanToRemove.getBookId());
                
                // 1. Mark the Book as NOT borrowed
                if (returnedBook != null) {
                    returnedBook.setBorrowed(false);
                }
                
                // 2. Remove the Loan from the list
                activeLoans.remove(loanToRemove);
                
                System.out.println("Success! Loan L" + loanId + " removed. " + 
                                   (returnedBook != null ? returnedBook.getTitle() : "Book") + 
                                   " is now available.");
            } else {
                System.out.println("Error: Invalid Loan ID.");
            }
    }
	
	 // --- Option 6: Add a Book ---
    public void addBook(Scanner sc) {
    	
    	String bookId;
    	String bookTitle;
    	String bookAuthor;
    	boolean enterAnother = false;
    	
    	do {
    		System.out.print("Enter Book ID: ");
    		bookId = sc.nextLine();
        
    		System.out.print("Enter Book Title: ");
    		bookTitle = sc.nextLine();
        
    		System.out.print("Enter Book Author: ");
    		bookAuthor = sc.nextLine();
            
            if (bookId == null || bookId.isBlank() || !bookId.matches("\\d+")) {
                 System.out.println("Book ID cannot be null or character");
                 enterAnother = true; // Stay in loop
                 continue;
            } else if (bookTitle == null || bookTitle.isBlank()) {
            	System.out.println("Book ID Title cannot be null");
            	enterAnother = true; // Stay in loop
                continue;
            } else if (bookAuthor == null || bookAuthor.isBlank()) {
            	System.out.println("Book ID Author cannot be null");
            	enterAnother = true; // Stay in loop
                continue;
            }
            
            Book newBook = new Book();
            
            newBook.setId(Integer.parseInt(bookId));
            newBook.setTitle(bookTitle);
            newBook.setAuthor(bookAuthor);
            newBook.setBorrowed(false);
            
            allBooksList.add(newBook);
            
            System.out.println("Book added to Library: " + newBook.getTitle());
            System.out.println("Add another book: [y/n]" );
            String input = sc.nextLine();
            
            if (input.equalsIgnoreCase("y")) {
                enterAnother = true;
           } else {
        	   enterAnother = false;
           }
            
    	} while (enterAnother);     
     
    }
    
    
public void removeBook(Scanner sc) {
	
	String input;
    
	do {
		
		displayAllBooksFormatted();
		System.out.println("\n--- Remove a Book ---");
		System.out.print("Enter Book ID or Title to remove (or 'cancel'): ");
		input = sc.nextLine();	
		
		if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Returning to main menu...");
            return; // This exits the method and returns to the LibraryApplication loop
        }
	
		if (input == null || input.isBlank()) {
			System.out.println("Enter a valid Book ID");
			continue; // Stay in the remove book option
		}
		
		Book toRemove = null;
		// 1. Search Logic: Check BOTH ID and Title
        for (Book b : allBooksList) {
            // Check if input matches ID (converted to string) OR the Title (case-insensitive)
            if (String.valueOf(b.getId()).equals(input) || b.getTitle().equalsIgnoreCase(input)) {
                toRemove = b;
                break;
            }
        }
        
     // 2. Process Removal
        if (toRemove != null) {
            if (toRemove.isBorrowed()) {
                System.out.println("❌ Error: '" + toRemove.getTitle() + "' is currently BORROWED.");
            } else {
                allBooksList.remove(toRemove); // ArrayList can remove by OBJECT directly!
                System.out.println("✅ Success! '" + toRemove.getTitle() + "' removed.");
            }
        } else {
            System.out.println("❌ No book found matching: " + input);
        }
	            
	    System.out.print("\nRemove another? [y/n]: ");
	} while (sc.nextLine().equalsIgnoreCase("y")); 
 }

public void updateBook(Scanner sc) {
	
	String input;
	boolean updateAnother = false;
    
	do {
		System.out.println("\n-------- Update a Book --------\n");
		
		displayAllBooksFormatted();
		System.out.println("Enter a Book ID to update:");
        
        input = sc.nextLine();			
		
		if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Returning to main menu...");
            return; // This exits the method and returns to the LibraryApplication loop
        }
	
		if (input == null || input.isBlank() || !input.matches("\\d+")) {
			System.out.println("Enter a valid update option");
			updateAnother = true;
			continue; // Stay in the remove book option
		}
		
	        Book toUpdate = findBookById(Integer.parseInt(input));
	        
	        if (toUpdate == null) {
	            System.out.println("❌ Error: No book found with that ID.");
	            return;
	        }
	            
	         if (toUpdate.isBorrowed()) {
	                System.out.println("❌ Error: Cannot update '" + toUpdate.getTitle() + "' because it is BORROWED.");
	                // We don't 'return' here because they might want to try a different ID
	                updateAnother = true;
	                continue; // Stay in the remove book option
	            } else {
	            	
	            	displaySpecificBook(toUpdate);
	            	processUpdateSelection(sc, toUpdate);
	            	displaySpecificBook(toUpdate);
	            	
	            	System.out.println("\n Do you wnat to update another book info? [y/n]:");
	        	    
    	            input = sc.nextLine();	
        		
        		if (input.equalsIgnoreCase("y")) {
                    //return to update option
        			updateAnother = true;
                    continue; 
                } else updateAnother = false;
        			System.out.println("Returning to main menu...");
        			break;	    	        
	        	    }
    
	} while (updateAnother); 
 }


	private void displaySpecificBook(Book b) {
		printTableHeader();
		String status = b.isBorrowed() ? "BORROWED" : "AVAILABLE";
		System.out.printf("| %-7d | %-30s | %-25s | %-11s |%n", 
                      b.getId(), b.getTitle(), b.getAuthor(), status);
		printTableFooter();
	}
	
	
	private void processUpdateSelection(Scanner sc, Book toUpdate) {
	    System.out.println("\nSelect an option to update:");
	    System.out.println(" [1] Update Book Title");
	    System.out.println(" [2] Update Book Author");
	    System.out.print(" Choice: ");
	    String updateChoice = sc.nextLine();

	    switch (updateChoice) {
	        case "1":
	            System.out.print("Input New Book Title: ");
	            String newTitle = sc.nextLine();
	            if (!newTitle.isBlank()) {
	                toUpdate.setTitle(newTitle);
	                System.out.println("✅ Success! Book title has been updated.");
	            }
	            break;

	        case "2":
	            System.out.print("Input New Book Author: ");
	            String newAuthor = sc.nextLine();
	            if (!newAuthor.isBlank()) {
	                toUpdate.setAuthor(newAuthor);
	                System.out.println("✅ Success! Book author has been updated.");
	            }
	            break;

	        default:
	            System.out.println("❌ Invalid option. No changes made.");
	            break;
	    }
	}
	
	
	public void displayMinimal(String user) {
	    
		
	    if (user == null || user.isEmpty()) {
	    	System.out.printf("  %-4s %-30s %-26s %-12s%n", "ID", "TITLE", "AUTHOR", "STATUS");
		    System.out.println("  ───  ────────────────────────────── ──────────────────────────  ────────────");
		    for (Book b : allBooksList) {
		    	String statusSymbol = b.isBorrowed() ? "● Borrowed" : "○ Available";
		    	System.out.printf("  %02d   %-30s %-26s %-12s%n", b.getId(), b.getTitle(), b.getAuthor(), statusSymbol);
		    }
	    System.out.println("  ───  ────────────────────────────── ──────────────────────────  ────────────");
	    } else {
	    	
	    	System.out.printf("  %-4s %-30s %-27s %-13s %-12s%n", "ID", "TITLE", "AUTHOR", "STATUS", "BORROWER");
		    System.out.println("  ───  ────────────────────────────── ──────────────────────────  ────────────  ────────────");
		    for (Book b : allBooksList ) {
		    	if (b.isBorrowed() == true) {
		    	String statusSymbol = b.isBorrowed() ? "● Borrowed" : "○ Available";
		    	System.out.printf("  %02d   %-30s %-27s %-13s %-12s%n", b.getId(), b.getTitle(), b.getAuthor(), statusSymbol, "✅ " + user);
		    
		    	}
		    }
		    System.out.println("  ───  ────────────────────────────── ──────────────────────────  ────────────  ────────────");
	    	
	    }
	}
	
	public void displayAllBooksFormatted() {
		/*System.out.println("-------------------------------- LIBRARY CATALOG -------------------------------------");
	    // Header Template: %-5s (ID), %-30s (Title), %-25s (Author), %-15s (Status)
	    System.out.printf("|   %-5s | %-30s | %-25s | %-11s |%n", "ID", "TITLE", "AUTHOR", "STATUS");
	    System.out.println("--------------------------------------------------------------------------------------");
        */
		
		printTableHeader();
	    // Loop through your list of books
	    for (Book book : allBooks()) {
	        String status = book.isBorrowed() ? "BORROWED" : "AVAILABLE";

	        // Row Template: Use the SAME numbers as the header to keep columns aligned
	        System.out.printf("|   %-5d | %-30s | %-25s | %-11s |%n", 
	            book.getId(), 
	            book.getTitle(), 
	            book.getAuthor(), 
	            status
	        );
	    }
	    printTableFooter();
	}
}
