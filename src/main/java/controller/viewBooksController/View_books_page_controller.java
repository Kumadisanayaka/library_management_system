package controller.viewBooksController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import model.ViewBookDTO;
import scene.SceneManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class View_books_page_controller implements Initializable {

    View_books_controller viewBooksController = new View_books_controller();

    @FXML
    private TableColumn<ViewBookDTO, String> availableCopiesColumn;

    @FXML
    private TableColumn<ViewBookDTO, String> authorColumn;

    @FXML
    private TableColumn<ViewBookDTO, String> bookTitleColumn;

    @FXML
    private TableColumn<ViewBookDTO, String> statusColomn;

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
    private Button btnManageBooks;

    @FXML
    private Button btnResetFilter;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnViewBook;

    @FXML
    private TableColumn<ViewBookDTO, String> categoryColumn;

    @FXML
    private ComboBox<String> cmbAvailability;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private ComboBox<String> cmbSortBy;

    @FXML
    private TableColumn<ViewBookDTO, String> totalCopiesColumn;

    @FXML
    private TableColumn<ViewBookDTO, String> idColumn;

    @FXML
    private TableColumn<ViewBookDTO, String> isbnColumn;

    @FXML
    private Label lblTotalCount;

    @FXML
    private TableView<ViewBookDTO> tblViewBooks;

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
    void btnManageMemberOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_member_page.fxml");
    }

    @FXML
    public void btnManageBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_books_page.fxml");
    }

    @FXML
    void btnResetFilterOnAction(ActionEvent event) {

        txtSearch.clear();
        cmbCategory.setValue(null);
        cmbCategory.setPromptText("Select Category");
        cmbAvailability.setValue(null);
        cmbAvailability.setPromptText("Select Availability");
        cmbSortBy.setValue(null);
        cmbSortBy.setPromptText("Select Sort by");
        loadFilteredBooks();
        Integer totalBooks = viewBooksController.getCategoryBookCount(null);
        lblTotalCount.setText(String.valueOf(totalBooks));
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        loadFilteredBooks();
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

    private void loadCategories(){

        ObservableList<String> categoryList = viewBooksController.getCategories();
        cmbCategory.setItems(categoryList);
    }

    private void loadFilteredBooks(){

        String searchText = txtSearch.getText();
        String category = cmbCategory.getValue();
        String availability = cmbAvailability.getValue();
        String sort = cmbSortBy.getValue();

        ObservableList<ViewBookDTO> bookList = viewBooksController.applyFilters(searchText,category,availability,sort);

        tblViewBooks.setItems(bookList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCategories();

        cmbAvailability.getItems().addAll(
                "Available",
                "Unavailable"
        );

        cmbSortBy.getItems().addAll(
                "Title (A-Z)",
                "Title (Z-A)",
                "Author (A-Z)",
                "Author (Z-A)",
                "Book ID"
        );

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        availableCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));
        statusColomn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadFilteredBooks();

        lblTotalCount.setText(String.valueOf(viewBooksController.getCategoryBookCount(null)));

        txtSearch.textProperty().addListener(
            (observable, oldValue, newValue) ->{
                loadFilteredBooks();
            });

        cmbCategory.valueProperty().addListener(
            (observable, oldValue, newValue) ->{
                loadFilteredBooks();

                Integer bookCount = viewBooksController.getCategoryBookCount(newValue);

                lblTotalCount.setText(String.valueOf(bookCount));

            } );

        cmbAvailability.valueProperty().addListener(
            (observable, oldValue, newValue) ->{
                loadFilteredBooks();
            });

        cmbSortBy.valueProperty().addListener(
            (observable, oldValue, newValue) ->{
                loadFilteredBooks();
            });

    }
}
