


public class Loan {
    private int id;      // loan id
    private int bookId;
    private int userId;
    private String note;

    public Loan(int id, int bookId, int userId, String note) {
        this.id = id; this.bookId = bookId; this.userId = userId; this.note = note;
    }

    public int getId() { 
    	return id; 
    	}
    public int getBookId() { 
    	return bookId; 
    	}
    public int getUserId() { 
    	return userId; 
    	}
    public String getNote() { 
    	return note; 
    	}
}

