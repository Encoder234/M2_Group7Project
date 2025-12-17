package M2.Group_Project;


public class Loan{
	private User user;
	private Book book;
	private int loanId[];
	private String loanBook[];
	
	public Loan(Integer count) {
		this.loanId = new int[count];
		this.loanBook = new String[count];
	}
	public Loan() {
		
	}
	
//	public void toLoan(int count, int bookId, int loanId) {
//		setIsLoaned(count, true);
//		this.loanId[count] = loanId;
//		this.loanBook[count] = getTitle(bookId-1);
//	}
	
	
	public void setLoanId(int index, int loanId) {
		this.loanId[index] = loanId;
	}
	public int getLoanId(int index) {
		return this.loanId[index];
	}
	public void setLoanBook(int index, String loanBook) {
		this.loanBook[index] = loanBook;
	}
	public String getLoanBook(int index) {
		return this.loanBook[index];
	}
	
	

	
}
