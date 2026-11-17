package Repository;

import javafx.collections.ObservableList;
import model.dto.BorrowingHistoryDTO;
import model.dto.StatusCountDTO;

public interface BorrowingHistoryRepository {

    ObservableList<BorrowingHistoryDTO> applyFilters(String searchtxt, String status);

    StatusCountDTO getRecordCount();


}
