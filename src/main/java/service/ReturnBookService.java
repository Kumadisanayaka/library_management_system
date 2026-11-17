package service;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface ReturnBookService {

    ObservableList<String> getMemberIds();

    String getMemberName(String memberId);

    ObservableList<String> getBookIds(String memberId);

    String getBookTitle(String bookId);

    LocalDate getIssueDate(String bookId, String memberId);

    int getIssueId(String memberId, String bookId);

    boolean returnBook(Integer issueId);


}
