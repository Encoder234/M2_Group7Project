package M2.Group_Project;

public class Book {
	// adding this "id" to give you an idea on what options you can do
	private Integer[] id;
	private String[] title;
	private String[] author;
	// feel free to add fields that may help
	private boolean[] isLoaned;
	
	public Book(Integer number) {
		this.id = new Integer[number];
		this.title = new String[number];
		this.author = new String[number];
		this.isLoaned = new boolean[number];
	}
	public Book() {
		
	}
	

	public void setId(Integer index, Integer id) {
		this.id[index] = id;
	}
	public Integer getId(Integer index) {
		return this.id[index];
	}
	
	public void setTitle(Integer index, String title) {
		this.title[index] = title;
	}
	public String getTitle(Integer index) {
		return this.title[index];
	}
	
	public void setAuthor(Integer index, String author) {
		this.author[index] = author;
	}
	public String getAuthor(Integer index) {
		return this.author[index];
	}
	
	public void setIsLoaned(Integer index, boolean isLoaned) {
		this.isLoaned[index] = isLoaned;
	}
	public boolean getIsLoaned(Integer index) {
		return this.isLoaned[index];
	}
}
