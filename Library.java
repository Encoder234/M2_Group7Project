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
        book5.setBorrowed(false);

       
    }
	
	private Book[] allBooks() {
        return new Book[] { book1, book2, book3, book4, book5 };
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
        Book[] books = allBooks();
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];

            if (b.isBorrowed() == false) {
            System.out.println("  [" + b.getId() + "] " + String.format("%-20s", b.getTitle()) + " | " + String.format("%-20s", b.getAuthor())
                + (b.isBorrowed() ? "| (BORROWED)" : "| (AVAILABLE)"));
            }
            
        }
    }
	
	public void displayBorrowedBooks() {
		if (activeLoans.isEmpty()) {
            System.out.println("No books are currently borrowed.");
            return;
        }
        System.out.println("ALL BORROWED BOOKS:");
        Book[] books = allBooks();
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];

            if (b.isBorrowed() == true) {
            System.out.println("  [" + b.getId() + "] " + String.format("%-20s", b.getTitle()) + " | " + String.format("%-20s", b.getAuthor())
                + (b.isBorrowed() ? "| (BORROWED)" : "| (AVAILABLE)"));
            }
            
        }
    }
	
	public void borrowBook(User borrower, Scanner sc) {
        displayAvailableBooks();
        
        System.out.print("Enter the ID of the book to borrow: ");
        //Scanner sc = new Scanner(System.in);
        String idChoice = sc.nextLine();
        
        try {
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
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        
        //sc.close();
    }
	
	public void returnBook(Scanner sc) {
        if (activeLoans.isEmpty()) {
            System.out.println("No books are currently on loan to return.");
            return;
        }

        displayBorrowedBooks(); // Show the list of current loans
        
        System.out.print("Enter the ID of the Loan to return: ");
        //Scanner sc = new Scanner(System.in);
        String idChoice = sc.nextLine();
        
        try {
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
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        
        //sc.close();
    }

}
