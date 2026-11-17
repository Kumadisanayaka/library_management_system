package service.impl;

import Repository.IssueRepository;
import Repository.impl.IssueRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.BookIssuedDTO;
import service.IssueService;

public class IssueServiceImpl implements IssueService {

    IssueRepository repository = new IssueRepositoryImpl();

    public ObservableList<String> getBookIds(String memberId) {

        return repository.getBookIds(memberId);
    }

    public ObservableList<String> getMemberIds() {

        return repository.getMemberIds();
    }

    public String getMemberName(String memberId) {

        return repository.getMemberName(memberId);
    }

    public String getBookTitle(String bookId) {

        return repository.getBookTitle(bookId);
    }

    public boolean issueBook(BookIssuedDTO bookIssuedDTO) {

        return repository.issueBook(bookIssuedDTO);
    }

    public boolean updateAvailableBookCount(String bookId) {

        return repository.updateAvailableBookCount(bookId);
    }
}
