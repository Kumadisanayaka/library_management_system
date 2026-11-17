package service.impl;

import Repository.MainDashboardRepository;
import Repository.impl.MainDashboardRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.BookIssuedDTO;
import model.dto.DashboardDTO;
import service.MainDashbordService;

public class MainDashboardServiceImpl implements MainDashbordService {

    MainDashboardRepository repository = new MainDashboardRepositoryImpl();

    @Override
    public ObservableList<BookIssuedDTO> getLoadIssuedBookData() {
        return repository.getLoadIssuedBookData();
    }

    @Override
    public DashboardDTO getDashboardCardData() {
        return repository.getDashboardCardData();
    }
}
