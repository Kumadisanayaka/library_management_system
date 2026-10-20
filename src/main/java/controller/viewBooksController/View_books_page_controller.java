package controller.viewBooksController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class View_books_page_controller implements Initializable {

    @FXML
    private TableColumn<?, ?> actionsColumn;

    @FXML
    private TableColumn<?, ?> authorColumn;

    @FXML
    private TableColumn<?, ?> bookTitleColumn;

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
    private Button btnManageMember;

    @FXML
    private Button btnResetFilter;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnViewBook;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private ComboBox<String> cmbAvailability;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private ComboBox<String> cmbSortBy;

    @FXML
    private TableColumn<?, ?> copiesColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> isbnColumn;

    @FXML
    private Label lblTotalCount;

    @FXML
    private TableView<?> tblViewBooks;

    @FXML
    private TextField txtSearch;

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

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnManageMemberOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetFilterOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    @FXML
    void txtSearchEnteredOnAction(DragEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchTypeOnAction(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbCategory.getItems().addAll(
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

        cmbAvailability.getItems().addAll(
                "All Availability",
                "Available",
                "Unavailable"
        );

        cmbSortBy.getItems().addAll(
                "Title (A-Z)",
                "Title (Z-A)",
                "Author (A-Z)",
                "Book ID"
        );

        cmbCategory.setValue("All Categories");
        cmbAvailability.setValue("All Availability");
        cmbSortBy.setValue("Title (A-Z)");
    }
}
