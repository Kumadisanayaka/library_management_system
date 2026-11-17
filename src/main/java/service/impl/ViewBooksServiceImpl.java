package service.impl;

import Repository.ViewBooksRepository;
import Repository.impl.ViewBooksRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.ViewBookDTO;
import service.ViewBooksService;


public class ViewBooksServiceImpl implements ViewBooksService {

    ViewBooksRepository repository = new ViewBooksRepositoryImpl();

    public ObservableList<String> getCategories() {

       return repository.getCategories();
    }

    public ObservableList<ViewBookDTO> applyFilters(String searchText, String category, String availability, String sort) {

        return repository.applyFilters(searchText,category,availability,sort);
    }

    public Integer getCategoryBookCount(String category) {

        return repository.getCategoryBookCount(category);
    }
}
