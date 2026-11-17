package service.impl;

import Repository.AddBookRepository;
import Repository.impl.AddBookRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.BookDTO;
import service.AddBookService;


public class AddBookServiceImpl implements AddBookService {

    AddBookRepository repository = new AddBookRepositoryImpl();

    public ObservableList<String> getBookCategories(){
        return repository.getBookCategories();
    }

    //Validation check
    public boolean isEmpty(String iD) {

        return iD == null || iD.trim().isEmpty();
    }

    public boolean isNumber(String id) {

        return id.matches("\\d+");
    }

    //Genarate Book ID
    public String genarateBookId(String bookCategory) {

        return repository.genarateBookId(bookCategory);
    }

    public boolean addBook(BookDTO book, Integer authorId, Integer categoryId) {

       return repository.addBook(book,authorId,categoryId);
    }

    public Integer getAuthorId(String author) {

        return repository.getAuthorId(author);
    }

    public Integer getCategoryId(String category) {

        return repository.getCategoryId(category);
    }
}
