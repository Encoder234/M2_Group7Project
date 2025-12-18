

public class Book {
	
	// adding this "id" to give you an idea on what options you can do
	private Integer id;
	private String title;
	private String author;
	// feel free to add fields that may help
	private Boolean availabilityStatus = true;
	private String loanDateAndTime = "";
	

	Book(Integer id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	public Boolean getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(Boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public String getLoanDateAndTime() {
		return loanDateAndTime;
	}

	public void setLoanDateAndTime(String loanDateAndTime) {
		this.loanDateAndTime = loanDateAndTime;
	}
	
	
}
