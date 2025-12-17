

public class Book {
	
	// adding this "id" to give you an idea on what options you can do
	private Integer id;
	private String title;
	private String author;
	private boolean borrowed;
	// feel free to add fields that may help
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public boolean isBorrowed() { 
		return borrowed; 
	}
	
	 public void setBorrowed(boolean borrowed) { 
		 this.borrowed = borrowed; 
	}


}
