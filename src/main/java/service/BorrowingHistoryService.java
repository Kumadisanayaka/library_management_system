package service;

import javafx.collections.ObservableList;
import model.dto.BorrowingHistoryDTO;
import model.dto.StatusCountDTO;

public interface BorrowingHistoryService {

    ObservableList<BorrowingHistoryDTO> applyFilters(String searchtxt, String status);

    StatusCountDTO getRecordCount();


}
