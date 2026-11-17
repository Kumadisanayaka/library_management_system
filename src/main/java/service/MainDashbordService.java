package service;

import javafx.collections.ObservableList;
import model.dto.BookIssuedDTO;
import model.dto.DashboardDTO;

public interface MainDashbordService {

    ObservableList<BookIssuedDTO> getLoadIssuedBookData();

    DashboardDTO getDashboardCardData();
}
