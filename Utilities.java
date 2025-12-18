import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

abstract class Utilities {
	
	public static final int MAX_NUMBER_OF_BOOKS  = 5;
	public static final int ALL_BOOKS  = 1;
	public static final int ALL_AVAILABLE_BOOKS  = 2;
	public static final int ALL_BORROWED_BOOKS  = 3;

	static String GetDateAndTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);	
        return formattedDateTime;
	}
	
	static void PrintLoans(Loan[] loans) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");		
		for (Loan l : loans) {
		    //System.out.println(l);
		    if (l != null) {
		    	System.out.println(l.getUser().getName());
				System.out.println(l.getBook().getId());
				System.out.println(l.getBook().getTitle());
				System.out.println("--------------------------");
		    }
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	} //PrintLoans ()
	
	static int DisplayMenu() {
				
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n=== LIBRARY APPLICATION ===");
		System.out.println("[1] Display All Books");
		System.out.println("[2] Display Available Books");
		System.out.println("[3] Display All Borrowed Books");
		System.out.println("[4] Borrow Book");
		System.out.println("[5] Return Book");
		System.out.println("[6] Exit");
		System.out.print("Choice: ");
		
		if (scanner.hasNextInt()) {
			int id = scanner.nextInt();
			scanner.nextLine();
			if ( id > 0 && id < 7 ) {
				return id;
			} else {
				System.out.println("Invalid Menu Choice, Please choose between 1 - 6.");
			}
		} else {
			System.out.println("Invalid Menu Choice, please provide numeric value.");
			scanner.nextLine();
		}
		
		return 0;
		
	}
	
}
