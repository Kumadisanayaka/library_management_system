package controller.borrowingHistoryController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Borrowing_history_page_controller implements Initializable {

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnExport;

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
    private TableColumn<?, ?> clmBookId;

    @FXML
    private TableColumn<?, ?> clmBookTitle;

    @FXML
    private TableColumn<?, ?> clmDueDate;

    @FXML
    private TableColumn<?, ?> clmIssueDate;

    @FXML
    private TableColumn<?, ?> clmMemberId;

    @FXML
    private TableColumn<?, ?> clmMemberName;

    @FXML
    private TableColumn<?, ?> clmRecordId;

    @FXML
    private TableColumn<?, ?> clmReturnDate;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private ComboBox<String> cmbBookStatCategories;

    @FXML
    private DatePicker datePickerFromDate;

    @FXML
    private DatePicker datePickerTodate;

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
    void btnExportOnAction(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbBookStatCategories.getItems().addAll(
                "All Transactions",
                "Issued",
                "Returned",
                "Overdue"
        );

        cmbBookStatCategories.setValue("All Transactions");
    }
}
