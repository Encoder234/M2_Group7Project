package act.M2_Group7Project;

public class Loan {

	private int id;
	private User user;
//	private Book[] books = new Book[0];
	private Book book;
//	int borrowedCount = 0;
	
	public Loan(int id, Book book, User user) {
		this.id = id;
		this.book = book;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void displayInfo() {
		System.out.println(book.getInfo() + " Borrower: " + user.getName());
	}
	

}
