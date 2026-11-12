package controller.borrowingHistoryController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.BorrowingHistoryDTO;
import model.StatusCountDTO;
import scene.SceneManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Borrowing_history_page_controller implements Initializable {

    Borrowing_history_controller borrowingHistoryController = new Borrowing_history_controller();

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageBooks;

    @FXML
    private Button btnManageMembers;

    @FXML
    private Button btnResetFilters;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    @FXML
    private TableColumn<BorrowingHistoryDTO, String> clmBookId;

    @FXML
    private TableColumn<BorrowingHistoryDTO, String> clmBookTitle;

    @FXML
    private TableColumn<BorrowingHistoryDTO, LocalDate> clmDueDate;

    @FXML
    private TableColumn<BorrowingHistoryDTO, LocalDate> clmIssueDate;

    @FXML
    private TableColumn<BorrowingHistoryDTO, String> clmMemberId;

    @FXML
    private TableColumn<BorrowingHistoryDTO, String> clmMemberName;

    @FXML
    private TableColumn<BorrowingHistoryDTO, Integer> clmRecordId;

    @FXML
    private TableColumn<BorrowingHistoryDTO, String> clmStatus;

    @FXML
    private TableView<BorrowingHistoryDTO> tblHistoryData;

    @FXML
    private ComboBox<String> cmbBookStatCategories;

    @FXML
    private Label lblBookIssuedCount;

    @FXML
    private Label lblBooksReturnedCount;

    @FXML
    private Label lblOverdueBooksCount;

    @FXML
    private Label lblTotalRecordsCount;

    @FXML
    private TextField txtsearch;

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_book_page.fxml");
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_member_page.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/main_dashboard_page.fxml");
    }


    @FXML
    void btnHistoryOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/borrowing_history_page.fxml");
    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/issue_page.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnManageBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_books_page.fxml");
    }

    @FXML
    void btnManageMembersOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_member_page.fxml");
    }

    @FXML
    void btnResetFiltersOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    @FXML
    void txtSearchTypingOnAction(KeyEvent event) {

    }

    private void loadFilters(){

        ObservableList<BorrowingHistoryDTO> historyList =
                borrowingHistoryController.applyFilters(
                        txtsearch.getText(),cmbBookStatCategories.getValue());

        tblHistoryData.setItems(historyList);

    }

    private void loadRecordCount(){

        StatusCountDTO countDTO = borrowingHistoryController.getRecordCount();

        lblTotalRecordsCount.setText(String.valueOf(countDTO.getTotalRecords()));
        lblBookIssuedCount.setText(String.valueOf(countDTO.getIssuedBooks()));
        lblBooksReturnedCount.setText(String.valueOf(countDTO.getReturnBooks()));
        lblOverdueBooksCount.setText(String.valueOf(countDTO.getOverDueBooks()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadFilters();
        loadRecordCount();

        cmbBookStatCategories.getItems().addAll(
                "All Transactions",
                "Issued",
                "Returned",
                "Overdue"
        );

        cmbBookStatCategories.setValue("All Transactions");

        clmRecordId.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        clmBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        clmBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        clmIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        clmDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        cmbBookStatCategories.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    loadFilters();
                }
        );

        txtsearch.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    loadFilters();
                }
        );
    }
}
