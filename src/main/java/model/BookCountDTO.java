package model;

public class BookCountDTO {

    private int toatlBooks;
    private int availableBooks;
    private int issuedBooks;

    public BookCountDTO() {
    }

    public BookCountDTO(int toatlBooks, int availableBooks, int issuedBooks) {
        this.toatlBooks = toatlBooks;
        this.availableBooks = availableBooks;
        this.issuedBooks = issuedBooks;
    }

    public int getToatlBooks() {
        return toatlBooks;
    }

    public void setToatlBooks(int toatlBooks) {
        this.toatlBooks = toatlBooks;
    }

    public int getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(int availableBooks) {
        this.availableBooks = availableBooks;
    }

    public int getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(int issuedBooks) {
        this.issuedBooks = issuedBooks;
    }
}
