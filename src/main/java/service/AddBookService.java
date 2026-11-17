package service;

import javafx.collections.ObservableList;
import model.dto.BookDTO;

public interface AddBookService {

    ObservableList<String> getBookCategories();

    boolean isEmpty(String iD);

    boolean isNumber(String id);

    String genarateBookId(String bookCategory);

    boolean addBook(BookDTO book, Integer authorId, Integer categoryId);

    Integer getAuthorId(String author);

    Integer getCategoryId(String category);


}
