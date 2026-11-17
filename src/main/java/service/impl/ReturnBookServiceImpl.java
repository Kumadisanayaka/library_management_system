package service.impl;

import Repository.ReturnBookRepository;
import Repository.impl.ReturnBookRepositoryImpl;
import javafx.collections.ObservableList;
import service.ReturnBookService;

import java.time.LocalDate;

public class ReturnBookServiceImpl implements ReturnBookService {

    ReturnBookRepository repository = new ReturnBookRepositoryImpl();

    public ObservableList<String> getMemberIds() {

        return repository.getMemberIds();
    }

    public String getMemberName(String memberId) {

        return repository.getMemberName(memberId);
    }

    public ObservableList<String> getBookIds(String memberId) {

        return repository.getBookIds(memberId);
    }

    public String getBookTitle(String bookId) {

        return repository.getBookTitle(bookId);
    }

    public LocalDate getIssueDate(String bookId, String memberId) {

        return repository.getIssueDate(bookId,memberId);
    }

    public int getIssueId(String memberId, String bookId) {

        return repository.getIssueId(memberId,bookId);
    }

    public boolean returnBook(Integer issueId) {

        return repository.returnBook(issueId);
    }
}
