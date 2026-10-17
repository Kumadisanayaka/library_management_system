package controller.mainDashboardControlle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import scene.SceneManager;

public class Main_dashboard_page_controller {

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnAddbook;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageMembers;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    @FXML
    private TableColumn<?, ?> clmBookTitle;

    @FXML
    private TableColumn<?, ?> clmDueDate;

    @FXML
    private TableColumn<?, ?> clmIssueDate;

    @FXML
    private TableColumn<?, ?> clmMember;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private Label lblBookIssued;

    @FXML
    private Label lblOverDueBooks;

    @FXML
    private Label lblTotalBook;

    @FXML
    private Label lblTotalMember;

    @FXML
    private TableView<?> tblRecentIssuedBooks;

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_member_page.fxml");
    }

    @FXML
    void btnAddbookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_book_page.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnManageMembersOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

}
