import java.util.Arrays;
import java.util.Scanner;

public class Library extends Utilities {
   // can contain up to 5 books	
		
	private Book[] books = new Book[MAX_NUMBER_OF_BOOKS];
	private Loan[] loans = new Loan[MAX_NUMBER_OF_BOOKS];
	private static int loanCounter = 0;
	
	Library() {
		AddFiveBooks();
	}
	
	void AddFiveBooks() {
		Book book1 = new Book(1,"The Hobbit", "J.R.R. Tolkien");
		Book book2 = new Book(2,"Mistborn", "Brandon Sanderson");
		Book book3 = new Book(3,"City of Bones", "Cassandra Clare");
		Book book4 = new Book(4,"Eragon", "Christopher Paolini");
		Book book5 = new Book(5,"Uprooted", "Naomi Novik");
		
		books[0] = book1;
		books[1] = book2;
		books[2] = book3;
		books[3] = book4;
		books[4] = book5;
	}
	
	
	void DislayBooks(int displayType) {
		
		int countLoanedBooks = 0;

		switch (displayType) {
			case ALL_BOOKS: 			System.out.println("\n=== ALL BOOKS ==========================================================================================================");
										break; 
			case ALL_AVAILABLE_BOOKS: 	System.out.println("\n=== ALL AVAILABLE BOOKS FOR LOAN =======================================================================================");
										break; 
			case ALL_BORROWED_BOOKS: 	System.out.println("\n=== ALL BORROWED BOOKS ================================================================================================="); 
									 	break; 
		}
				
		
		System.out.printf("%-5s %-20s %-25s %-20s %-20s %-20s%n", "ID", "Title", "Author", "Availability", "Loaned To", "Loan out Date & Time");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        
       	
    		switch (displayType) {
				case ALL_BOOKS: 			for (int i = 0; i < MAX_NUMBER_OF_BOOKS; i++) { 
												if (this.books[i].getAvailabilityStatus()) 
													System.out.printf("%-5s %-20s %-25s %-20b %-20s %-20s%n", this.books[i].getId(), this.books[i].getTitle(), this.books[i].getAuthor(), this.books[i].getAvailabilityStatus(),"","");
											}											
											for (Loan l : loans) {											    
											    if (l != null) { 
													System.out.printf("%-5s %-20s %-25s %-20b %-20s %-20s%n", l.getBook().getId(), l.getBook().getTitle(), l.getBook().getAuthor(), l.getBook().getAvailabilityStatus(),l.getUser().getName(),l.getBook().getLoanDateAndTime());
												}
											    
											}
											break;								
											
				case ALL_AVAILABLE_BOOKS: 	
											for (int i = 0; i < MAX_NUMBER_OF_BOOKS; i++) { 
												if (this.books[i].getAvailabilityStatus()) 
													System.out.printf("%-5s %-20s %-25s %-20b %-20s %-20s%n", this.books[i].getId(), this.books[i].getTitle(), this.books[i].getAuthor(), this.books[i].getAvailabilityStatus(),"","");
											}
											break;									
				
				case ALL_BORROWED_BOOKS: 	

											for (Loan l : loans) {				    
											    if (l != null) { 
													System.out.printf("%-5s %-20s %-25s %-20b %-20s %-20s%n", l.getBook().getId(), l.getBook().getTitle(), l.getBook().getAuthor(), l.getBook().getAvailabilityStatus(),l.getUser().getName(),l.getBook().getLoanDateAndTime());
												}
											}			
										 	break;
    		
    		} // switch()
        	
        	     
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Number Of Available Books for Loan: %d     Number of Loaned Out Books: %d%n", MAX_NUMBER_OF_BOOKS - loanCounter, loanCounter  );
        System.out.println("========================================================================================================================");
    	
        //Utilities.PrintLoans(this.loans);
	
	} //DislayBooks ()
	
	Boolean CheckIfValidBookID(int id) {
		boolean found = false;
		for (int i = 0; i < MAX_NUMBER_OF_BOOKS; i++) { 
			if (this.books[i].getId() == id) {
				found = true;
				break;
			}
		}
		return found;
	} //CheckIfValidBookID()
	
	
	Boolean CheckoutBookID(int id, User user) {
		for (int i = 0; i < MAX_NUMBER_OF_BOOKS; i++) { 
			if (this.books[i].getId() == id) {
				if (this.books[i].getAvailabilityStatus()) {
					this.books[i].setAvailabilityStatus(false);
					this.books[i].setLoanDateAndTime(Utilities.GetDateAndTime());
					
					Loan loan = new Loan (user, this.books[i]);
					loans[loanCounter]= loan;
					loanCounter++;	
					break;
				} else {
					System.out.printf("Book ID: %d is currently not available for loan, please pick a different book...%n", id);
					return false;
				}
			}
		}
		
		//Utilities.PrintLoans(this.loans);
		
		return true;
	} //CheckoutBookID()

	
	Boolean ReturnBookID(int id, User user) {		
		boolean found = false;
		
		//Utilities.PrintLoans(this.loans);

		//for (int x = 0; x < loanCounter ; x++  ) {
		for (int x = 0; x < MAX_NUMBER_OF_BOOKS ; x++  ) {	
			if (this.loans[x] != null) {
				if (this.loans[x].getUser().getName() == user.getName()) {				
					if (this.loans[x].getBook().getId() == id) {				
						for (int i = 0; i < MAX_NUMBER_OF_BOOKS; i++) { 
							if (this.loans[x].getBook().getId() == this.books[i].getId()) { 
								this.books[i].setAvailabilityStatus(true);
								this.books[i].setLoanDateAndTime("");
								this.loans[x] = null;
								found = true;
								loanCounter-= 1;
								break;
							} //if
						}// for
						
					} //if		
				}//if
				
			} //if			
		}//for
					
		return found;
	} //ReturnBookID()
	
		
	void LoanBook(User user) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println(Utilities.GetDateAndTime());
		
		boolean exit = false;
		while (!exit) {
			System.out.println("\n===== BORROW A BOOK =====");
			System.out.print("Enter Book ID to be Borrowed: ");
			
			if (scanner.hasNextInt()) {
				int id = scanner.nextInt();
				scanner.nextLine();
				if (CheckIfValidBookID(id)) {
					
					if(CheckoutBookID(id, user)) {
						System.out.println("\n===== BOOK CHECKOUT =====");
						System.out.printf("Book ID: %d has now been Checked out to %s%n", id, user.getName());
						exit = true;
					} else {
						exit = true;
					}
										
				} else {
					System.out.println("Invalid ID, Book id not found, please provide a valid Book id.");
				}
				
			} else {
				System.out.println("Invalid ID, please provide numeric value.");
				scanner.nextLine();
			}
		}
		
	} // LoanBook()
	
	
	
	void ReturnBook(User user) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println(Utilities.GetDateAndTime());
		
		System.out.println("\n===== RETURN A BOOK =====");
		System.out.printf("Borrower: %s%n", user.getName());
		
		System.out.println("Currently Borrowed Books: ");
		System.out.printf("%-5s %-20s %-25s %-20s %-20s %-20s%n", "ID", "Title", "Author", "Availability", "Loaned To", "Loan out Date & Time");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
		
		if (loanCounter == 0) {
			System.out.println("You currently Don't have borrowed Books");
		} else {
			int userLoanedBooks = 0;			
			for (Loan l : loans) {
			    if (l != null) { 
					System.out.printf("%-5s %-20s %-25s %-20b %-20s %-20s%n", l.getBook().getId(), l.getBook().getTitle(), l.getBook().getAuthor(), l.getBook().getAvailabilityStatus(),l.getUser().getName(),l.getBook().getLoanDateAndTime());
					userLoanedBooks++;
			    }
			}
			
			if (userLoanedBooks == 0) {
				System.out.println("You currently Don't have borrowed Books");
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.print("Enter Book ID to of Book you wish to return: ");
				
				if (scanner.hasNextInt()) { 
					int id = scanner.nextInt();
					scanner.nextLine();
					if (CheckIfValidBookID(id)) { 
						if (ReturnBookID(id, user)) {
							System.out.printf("Thank you for returning book id: %s%n", id);
						} else {
							System.out.printf("Please check supplied Book ID: %d, you may have not borrowed that book. %n", id);
						}
						
						
					} else {
						System.out.println("Invalid ID, Book id not found, please provide a valid Book id.");
					}
					
				} else {
					System.out.println("Invalid ID, please provide numeric value.");
					scanner.nextLine();
				}
				
			}
		}
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
			
	} // LoanBook()
	
	

}
