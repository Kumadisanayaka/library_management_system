package Repository;

import javafx.collections.ObservableList;
import model.dto.BookDTO;

public interface AddBookRepository {

    ObservableList<String> getBookCategories();

    String genarateBookId(String bookCategory);

    boolean addBook(BookDTO book, Integer authorId, Integer categoryId);

    Integer getAuthorId(String author);

    Integer getCategoryId(String category);

}
