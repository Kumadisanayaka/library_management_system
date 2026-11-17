package Repository;

import javafx.collections.ObservableList;
import model.dto.ViewBookDTO;

public interface ViewBooksRepository {

    ObservableList<String> getCategories();

    ObservableList<ViewBookDTO> applyFilters(String searchText, String category, String availability, String sort);

    Integer getCategoryBookCount(String category);

}
