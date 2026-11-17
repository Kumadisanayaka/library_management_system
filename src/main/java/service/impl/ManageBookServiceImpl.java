package service.impl;

import Repository.ManageBookRepository;
import Repository.impl.ManageBookRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.BookCountDTO;
import model.dto.BookDTO;
import service.ManageBookService;


public class ManageBookServiceImpl implements ManageBookService {

    ManageBookRepository repository = new ManageBookRepositoryImpl();

    public ObservableList<String> getBookCategories() {

        return repository.getBookCategories();
    }

    public ObservableList<BookDTO> applyFilters(String searchtxt, String category) {

        return repository.applyFilters(searchtxt,category);
    }

    public boolean updateBook(String bookId,
                              String isbn,
                              String title,
                              int totalCopies,
                              String description
    ) {

        return repository.updateBook(bookId,isbn,title,totalCopies,description);
    }

    public int getIssueCopiesCount(String bookId) {

        return repository.getIssueCopiesCount(bookId);
    }

    public boolean updateAuthor(String author,Integer authorId) {

        return repository.updateAuthor(author,authorId);
    }

    public Integer getAuthorId(String author) {

        return repository.getAuthorId(author);
    }

    public BookCountDTO getBookCount() {

        return repository.getBookCount();
    }

    public int getAvailableBookCount(String bookId) {

       return repository.getAvailableBookCount(bookId);
    }

    public boolean deActiveBook(String bookId) {

       return repository.deActiveBook(bookId);
    }


    public boolean activeBook(String bookId) {

        return repository.activeBook(bookId);
    }
}
