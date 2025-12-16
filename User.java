import java.util.Scanner;

public class User extends Utilities {
		
	private String name;
	
	User() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(Utilities.GetDateAndTime());
		System.out.println("=== Welcome to the Library, please Register ===");
		System.out.print("User Name: ");
		this.name = scanner.nextLine();
		
		System.out.println("\nWelcome " + this.name + " to the Library!");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
