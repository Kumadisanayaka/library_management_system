package model;

import java.time.LocalDate;

public class BorrowingHistoryDTO {

    private int issueId;
    private String memberId;
    private String memberName;
    private String bookId;
    private String bookTitle;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String status;

    public BorrowingHistoryDTO() {
    }

    public BorrowingHistoryDTO(int issueId,
                               String memberId,
                               String memberName,
                               String bookId,
                               String bookTitle,
                               LocalDate issueDate,
                               LocalDate dueDate,
                               String status) {
        this.issueId = issueId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
