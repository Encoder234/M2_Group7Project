package act.M2_Group7Project;

public class Book{
	
	// adding this "id" to give you an idea on what options you can do
	private Integer id;
	private String title;
	private String author;

	
	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;			
	}
	
	public int getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void displayInfo() {
		System.out.println("Book: #" + id + " " + title + " by " + author);	
	}
	
	public String getInfo() {
		return "Book: #" + id + " " + title + " by " + author;
	}
	
	public String getTitle() {
		return title;
	}
	
}
