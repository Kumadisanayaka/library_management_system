package Repository;

import javafx.collections.ObservableList;
import model.dto.BookCountDTO;
import model.dto.BookDTO;

public interface ManageBookRepository {

    ObservableList<String> getBookCategories();

    ObservableList<BookDTO> applyFilters(String searchtxt, String category);

    boolean updateBook(String bookId,
                       String isbn,
                       String title,
                       int totalCopies,
                       String description
    );

    int getIssueCopiesCount(String bookId);

    boolean updateAuthor(String author,Integer authorId);

    Integer getAuthorId(String author);

    BookCountDTO getBookCount();

    int getAvailableBookCount(String bookId);

    boolean deActiveBook(String bookId);

    boolean activeBook(String bookId);
}
