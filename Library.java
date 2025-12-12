package act.M2_Group7Project;

public class Library {
	private Book[] availableBooks;
	private Loan[] loans = new Loan[0];
	
    public void initializeBooks(){
    	
    	Book[] availableBooks = {
    			new Book(1,"Harry Potter","J.K. Rowling"),
    			new Book(2,"Rich Dad Poor Dad","Robert Kiyosaki"),
    			new Book(3,"Cash Flow Quadrant","Robert Kiyosaki"),
    			new Book(4,"Why we want to be Rich","Robert Kiyosaki"),
    			new Book(5,"Rich Kid Smart Kid","Robert Kiyosaki")
    	};
    	this.availableBooks = new Book[availableBooks.length];
    	this.availableBooks = availableBooks;
    }
    
    public void displayAllBooks() {
    	for(int i = 0; i < availableBooks.length; i++) {
    		availableBooks[i].displayInfo();
    	}
    	
    	for(int i = 0; i < loans.length; i++) {
    		loans[i].displayInfo();
    	}
    }
	

    public void displayAvailableBooks() {
    	if(availableBooks.length == 0) {
    		System.err.println("No available book");
    		return;
    	}
    	
    	for(int i = 0; i < availableBooks.length; i++) {
    		availableBooks[i].displayInfo();
    	}
    }
    
    public void borrowBook(int id, User user) {
    	boolean exists = false;
    	int toBeBorrowIndex = -1;
    	for(int i = 0; i < availableBooks.length; i++) {
    		if(availableBooks[i].getId() == id) {
    			toBeBorrowIndex = i;
    			exists = true;
    			addLoan(id, availableBooks[i], user);
    		}
    	}
    	
    	if(!exists) {
    		return;
    	}
    	
    	removeAvailableBookByIndex(toBeBorrowIndex);
    }
    
    public void displayBorrowedBooks() {
    	if(loans.length == 0) {
    		System.err.println("No borrowed book");
    		return;
    	}
    	
    	for(int i = 0; i < loans.length; i++) {
    		loans[i].displayInfo();
    	}
    }
    
    private void addLoan(int id, Book book, User user) {
    	Loan[] copyLoans = new Loan[loans.length + 1];
    	for(int i = 0; i < loans.length; i++) {
    		copyLoans[i] = loans[i];
    	}
    	copyLoans[copyLoans.length - 1] = new Loan(id, book, user);
    	this.loans = new Loan[copyLoans.length];
    	this.loans = copyLoans;
    	System.out.println(book.getInfo() + " checked out!");
    }
    
    private void removeLoanByIndex(int index) {
    	Loan[] copyLoans = new Loan[loans.length - 1];
    	int counter = 0;
    	for(int i = 0; i< loans.length; i++) {
    		if(index != i) {
    			copyLoans[counter] = loans[i];
    			counter++;
    		}
    	}
    	loans = new Loan[copyLoans.length];
    	loans = copyLoans;
    }
    
    private void removeAvailableBookByIndex(int index) {
    	Book[] copyAvailableBooks = new Book[availableBooks.length - 1];
    	int counter = 0;
    	for(int i = 0; i< availableBooks.length; i++) {
    		if(index != i) {
    			copyAvailableBooks[counter] = availableBooks[i];
    			counter++;
    		}
    	}
    	
    	availableBooks = new Book[counter];
    	availableBooks = copyAvailableBooks;
    }
    
    private void addAvailableBook(Book book) {
    	int bookCount = availableBooks.length + 1;
    	Book[] copyBooks = new Book[bookCount];
    	for(int i = 0; i < availableBooks.length; i++) {
    		copyBooks[i] = availableBooks[i];
    	}
    	copyBooks[bookCount - 1] = book;
    	this.availableBooks = new Book[bookCount];
    	this.availableBooks = copyBooks;
    }
    
    public void returnBook(int id) {
    	boolean exists = false;

    	Book toBeReturnBook = new Book(0, null, null);
    	
    	for(int i = 0; i < loans.length; i++) {
    		if(id == loans[i].getId()) {
    			exists = true;
    			toBeReturnBook = loans[i].getBook();
    			removeLoanByIndex(i);
    		}
    	}
    	
    	if(!exists) {
    		System.err.println("Loan not found");
    		return;
    	}else {
    		System.out.println(toBeReturnBook.getInfo() + " returned.");
    	}
    	
    	addAvailableBook(toBeReturnBook);

    }
    
    public boolean bookAvailable(int id) {
    	
    	for(int i = 0; i < availableBooks.length; i++) {
    		if(availableBooks[i].getId() == id) {
    			return true;
    		}
    	}
    	return false;
    }
}
