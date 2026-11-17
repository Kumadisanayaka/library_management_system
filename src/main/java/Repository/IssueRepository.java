package Repository;

import javafx.collections.ObservableList;
import model.dto.BookIssuedDTO;

public interface IssueRepository {

    ObservableList<String> getBookIds(String memberId);

    ObservableList<String> getMemberIds();

    String getMemberName(String memberId);

    String getBookTitle(String bookId);

    boolean issueBook(BookIssuedDTO bookIssuedDTO);

    boolean updateAvailableBookCount(String bookId);
}
