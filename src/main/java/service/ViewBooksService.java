package service;

import javafx.collections.ObservableList;
import model.dto.ViewBookDTO;

public interface ViewBooksService {

    ObservableList<String> getCategories();

    ObservableList<ViewBookDTO> applyFilters(String searchText, String category, String availability, String sort);

    Integer getCategoryBookCount(String category);


}
