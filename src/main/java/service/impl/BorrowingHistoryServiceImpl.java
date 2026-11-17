package service.impl;

import Repository.BorrowingHistoryRepository;
import Repository.impl.BorrowingHistoryRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.BorrowingHistoryDTO;
import model.dto.StatusCountDTO;
import service.BorrowingHistoryService;

public class BorrowingHistoryServiceImpl implements BorrowingHistoryService {

    BorrowingHistoryRepository repository = new BorrowingHistoryRepositoryImpl();

    public ObservableList<BorrowingHistoryDTO> applyFilters(String searchtxt, String status) {

        return repository.applyFilters(searchtxt, status);
    }

    public StatusCountDTO getRecordCount() {

        return repository.getRecordCount();
    }
}
