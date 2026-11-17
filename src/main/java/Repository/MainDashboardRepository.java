package Repository;

import javafx.collections.ObservableList;
import model.dto.BookIssuedDTO;
import model.dto.DashboardDTO;

public interface MainDashboardRepository {

    ObservableList<BookIssuedDTO> getLoadIssuedBookData();

    DashboardDTO getDashboardCardData();
}
