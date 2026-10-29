package model;

public class BookDTO {

    private String bookId;
    private String bookTitle;
    private String author;
    private String category;
    private String isbn;
    private Integer totalCopies;
    private String description;

    public BookDTO(
            String bookId,
            String bookTitle,
            String author,
            String category,
            String isbn,
            Integer totalCopies,
            String description
    ) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.description = description;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
