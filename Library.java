

public class Library {
   // can contain up to 5 books
    Book[] books;

    Library(Book[] book) {
        this.books = book;
    }

    //display all books
    void displayAllBook() {
        for (Book b : books) { //Loop to display all the books
            System.out.println(b);
        }
    } //end of display all books

    //display book available

    void displayAvailableBooks(){
        for(Book b : books) {
            if(!b.isBorrowed) {
                System.out.println(b);
            }
        }
    } // end of displayAvailableBooks

    void displayBorrowedBooks() {
        for (Book b : books) {
            if (b.isBorrowed) System.out.println(b);
        }
    }


    void borrowBook(int index) {
        if(index >= 0 && index < books.length) {
            if (!books[index].isBorrowed){
                books[index].isBorrowed = true;

                System.out.println("You borrowed: "+ books[index].getTitle());
            }else {
                System.out.println("Sorry this book is already borrowed.");
            }
        }
    }

    void returnBook(int index) {
        if (index >= 0 && index < books.length) {
            if (books[index].isBorrowed) {
                books[index].isBorrowed = false;
                System.out.println("You returned: " + books[index].getTitle());
            } else {
                System.out.println("This book was not borrowed.");
            }
        }
    }

}

