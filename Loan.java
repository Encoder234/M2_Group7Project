import java.time.LocalDate;

public class Loan {


	private User user;
	private Book book;

	LocalDate borrowDate;
	LocalDate returnDate;
	

	Loan(User user, Book book){
		this.user = user;
		this.book = book;
		this.borrowDate = LocalDate.now();
		this.returnDate = null;
	}

	public void markReturned(){
		this.returnDate = LocalDate.now();

	}

	@Override
	public String toString(){
		return user.name + "borrowed" + book.getTitle() + "\" on " + borrowDate +
				(returnDate == null ? " [Not Returned]" : " | Returned on " + returnDate);
	}
}





	

