package controller.manageBooksController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Manage_books_controller implements Initializable {

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
    private Button btnSearch;

    @FXML
    private Button btnViewBooks;

    @FXML
    private TableColumn<?, ?> clmAction;

    @FXML
    private TableColumn<?, ?> clmAuthor;

    @FXML
    private TableColumn<?, ?> clmBookId;

    @FXML
    private TableColumn<?, ?> clmBookTitle;

    @FXML
    private TableColumn<?, ?> clmCategory;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private ComboBox<String> cmbBookCategories;

    @FXML
    private Label lblAvailableBookCount;

    @FXML
    private Label lblIssuedBookCount;

    @FXML
    private Label lblTotalBookCount;

    @FXML
    private TableView<?> tblBook;

    @FXML
    private TextField txtSearchField;

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
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    @FXML
    void txtSearchFieldTypingOnAction(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbBookCategories.getItems().addAll(
                "Fiction",
                "Non-Fiction",
                "Science",
                "Technology",
                "Education",
                "History",
                "Biography",
                "Business",
                "Self-Help",
                "Literature",
                "Reference",
                "Children's Books"
        );
    }
}
