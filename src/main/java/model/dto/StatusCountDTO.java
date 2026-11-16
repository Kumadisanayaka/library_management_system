package model;

public class StatusCountDTO {

    private int totalRecords;
    private int issuedBooks;
    private int returnBooks;
    private int overDueBooks;

    public StatusCountDTO() {
    }

    public StatusCountDTO(int totalRecords, int issuedBooks, int returnBooks, int overDueBooks) {
        this.totalRecords = totalRecords;
        this.issuedBooks = issuedBooks;
        this.returnBooks = returnBooks;
        this.overDueBooks = overDueBooks;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(int issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    public int getReturnBooks() {
        return returnBooks;
    }

    public void setReturnBooks(int returnBooks) {
        this.returnBooks = returnBooks;
    }

    public int getOverDueBooks() {
        return overDueBooks;
    }

    public void setOverDueBooks(int overDueBooks) {
        this.overDueBooks = overDueBooks;
    }
}
