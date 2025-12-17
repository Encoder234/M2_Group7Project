package M2.Group_Project;

import java.util.Scanner;

public class Library implements Actions{
	private Book bookList = new Book(5);
	private Loan loanList = new Loan(5);
	private int borrowCount = 0;
	Scanner input = new Scanner(System.in);
   // can contain up to 5 books	
	public Library() {
		initBook(0, 1, "Pride and Prejudice", "Jane Austen", false);
		initBook(1, 2, "The Alchemist", "Paolo Coelho", false);
		initBook(2, 3, "Life of Pi", "Yann Mertel", false);
		initBook(3, 4, "Strange Pictures", "Uketsu", false);
		initBook(4, 5, "Every Day", "David Levithan", false);
	}
	private void initBook(int index, int id, String title, String author, boolean isLoaned) {
		bookList.setId(index, id);
        bookList.setTitle(index, title);
        bookList.setAuthor(index, author);
        bookList.setIsLoaned(index, isLoaned);
	}
	
	@Override
	public void showBooks() {
		System.out.println("LIST OF ALL BOOKS");
		for(int i=0; i<5; i++) {
			System.out.println("=========================");
			System.out.println("");
			System.out.println("ID: " + bookList.getId(i));
			System.out.println(bookList.getTitle(i));
			System.out.println("Author:" + bookList.getAuthor(i));
			System.out.println("\n=========================");
			System.out.println("");
		}
	}
	
	@Override
	public void showAvailable() {
		int countAvail = 0;
		System.out.println("LIST OF AVAILABLE BOOKS");
		for(int i=0; i<5; i++) {
			if(!bookList.getIsLoaned(i)) {
				System.out.println("=========================");
				System.out.println("");
				System.out.println("ID: " + bookList.getId(i));
				System.out.println(bookList.getTitle(i));
				System.out.println("Author: " + bookList.getAuthor(i));
				System.out.println("\n=========================");
				System.out.println("");
				countAvail++;
			}
		}
		
		if(countAvail == 0) {
			System.out.println("All books are borrowed.");
			System.out.println("");
		}
	}
	
	@Override
	public void showLoaned() {
		int countLoan = 0;
		System.out.println("LIST OF BORROWED BOOKS");
		for(int i=0; i<5; i++) {
			if(bookList.getIsLoaned(i)) {
				System.out.println("=========================");
				System.out.println("");
				System.out.println("Loan ID: " + loanList.getLoanId(i));
				System.out.println(bookList.getTitle(i));
				System.out.println("Author: " + bookList.getAuthor(i));
				System.out.println("\n=========================");
				System.out.println("");
				countLoan++;
			}
		}
		
		if(countLoan ==  0) {
			System.out.println("All books are available.");
			System.out.println("");
		}
	}
	
	@Override
	public void borrowBook() {
		boolean checkId = false;
		if(borrowCount<5) {
			System.out.print("Select book to borrow: ");
		    int selectBook = input.nextInt();
			System.out.print("Input ID for Loan: ");
			int inputIdLoan = input.nextInt();
			for(int i=0; i<5; i++){
				if(inputIdLoan == loanList.getLoanId(i)) {
					checkId = true;
				}
			}
			if(!checkId) {
				if(!bookList.getIsLoaned(selectBook-1)) {
					bookList.setIsLoaned(selectBook-1, true);
					loanList.setLoanId(selectBook-1, inputIdLoan);
					loanList.setLoanBook(selectBook-1, bookList.getTitle(selectBook-1));
					
					System.out.println("Successfully borrowed book!");
					System.out.println("");
					borrowCount++;
				}
				else {
					System.out.println("Book already borrowed!");
					System.out.println("");
				}
			}
			else {
				System.out.println("Loan ID entered already in use. Try another.");
				System.out.println("");
			}
		}
	}
	
	@Override
	public void returnBook() {
		if (borrowCount >= 1) {
			System.out.print("Select book to return: ");
		    int selectBook = input.nextInt();
		    
			boolean exitLoop = false;
			int checkIndex = 0, foundIndex = 0;
			while(!exitLoop && checkIndex <= 4) {
				if(loanList.getLoanId(checkIndex) == selectBook) {
					exitLoop = true;
				}
				else {
					checkIndex++;
				}
			}
			
			if (checkIndex > 4) {
				System.out.println("Loan ID not found.");
				System.out.println("");
			}
			else {
				foundIndex = checkIndex;
				exitLoop = false;
				checkIndex = 0;
				while(!exitLoop) {
					if(loanList.getLoanBook(foundIndex) == bookList.getTitle(checkIndex)){
						exitLoop = true;
					}
					else checkIndex++;
				}
				bookList.setIsLoaned(checkIndex, false);
				loanList.setLoanId(checkIndex, 0);
				loanList.setLoanBook(checkIndex, null);
				
				System.out.println("Successfully returned book!");
				System.out.println("");
				borrowCount--;
			}
		}
	}
}
	
