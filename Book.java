

public class Book {
	
	// adding this "id" to give you an idea on what options you can do
//	private int counter =1 ;
	private int id;
	private String title;
	private String author;
//	private String[] Book;



	boolean isBorrowed = false;

	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isBorrowed = false;
//		this.counter = counter++;
	}



	//Setter Getter
	public void setID(int id) { this.id = id;}
	public int getID() { return id;}
	public void setTitle(String title) { this.title = title;}
	public String getTitle() {return title;}
	public void setAuthor(){ this.author = author;}
	public String getAuthor() { return author;	}

	//toString Override the return value into ArrayTable
	@Override
	public String toString() {
//		System.out.println("ID    | Title                     | Author              | Status");
		return String.format("%-5d | %-25s | %-20s | %-10s",
				id,
				title,
				author,
				(isBorrowed ? "Borrowed" : "Available"));
	}
}
