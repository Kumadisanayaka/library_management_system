package controller.manageBooksController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.BookCountDTO;
import model.BookDTO;
import scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Manage_books_page_controller implements Initializable {

    Manage_book_controller manageBookController = new Manage_book_controller();

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
    private TableColumn<BookDTO, Void> clmAction;

    @FXML
    private TableColumn<BookDTO, String> clmAuthor;

    @FXML
    private TableColumn<BookDTO, String> clmBookId;

    @FXML
    private TableColumn<BookDTO, String> clmBookTitle;

    @FXML
    private TableColumn<BookDTO, String> clmCategory;

    @FXML
    private TableColumn<BookDTO, String> clmStatus;

    @FXML
    private ComboBox<String> cmbBookCategories;

    @FXML
    private Label lblAvailableBookCount;

    @FXML
    private Label lblIssuedBookCount;

    @FXML
    private Label lblTotalBookCount;

    @FXML
    private TableView<BookDTO> tblBook;

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

        cmbBookCategories.setValue("All Categories");
//        loadFilters();
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        loadFilters();
    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    @FXML
    void txtSearchFieldTypingOnAction(KeyEvent event) {

    }

    private void loadBookCategories(){

        ObservableList<String> bookCategoryList = manageBookController.getBookCategories();

        cmbBookCategories.setItems(bookCategoryList);
        cmbBookCategories.setValue("All Categories");
    }

    public void loadFilters(){

        String searchtxt = txtSearchField.getText();
        String category = cmbBookCategories.getValue();

        ObservableList<BookDTO> bookList = manageBookController.applyFilters(searchtxt,category);

        tblBook.setItems(bookList);

        loadBookCount();
    }

    private void loadBookCount(){

        BookCountDTO bookCountDTO = manageBookController.getBookCount();

        lblTotalBookCount.setText(String.valueOf(bookCountDTO.getToatlBooks()));
        lblAvailableBookCount.setText(String.valueOf(bookCountDTO.getAvailableBooks()));
        lblIssuedBookCount.setText(String.valueOf(bookCountDTO.getIssuedBooks()));
    }

    private void showError(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input!");
        alert.setContentText(message);

        alert.showAndWait();

    }

    private void showSuccess(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success");
        alert.setHeaderText("Operation Successful");
        alert.setContentText(message);

        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadBookCategories();

        clmBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        clmBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        clmAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadFilters();
        // Action column
        clmAction.setCellFactory(column -> new TableCell<BookDTO, Void>() {

            private final Button btnEdit = new Button("Edit");
            private final Button btnStatus = new Button();

            private final HBox pane = new HBox(5, btnEdit, btnStatus);

            {
                // Edit button CSS
                btnEdit.getStyleClass().add("table-edit-button");

                // Edit button action
                btnEdit.setOnAction(event -> {
                    BookDTO book = getTableView().getItems().get(getIndex());

                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/view/edit_book_page.fxml"));

                        Parent root = loader.load();

                        Edit_book_page_controller controller = loader.getController();

                        controller.setBookData(book);

                        controller.setParentController(Manage_books_page_controller.this);

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Edit Book");
                        stage.show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                });

                // Status button action
                btnStatus.setOnAction(event -> {
                    BookDTO book = getTableView().getItems().get(getIndex());

                   int availableBookCount =
                           manageBookController.getAvailableBookCount(book.getBookId());

                   if(book.getStatus().equals("ACTIVE")){

                       boolean isUpdated = manageBookController.deActiveBook(book.getBookId());

                       if (isUpdated){
                           showSuccess("Book deactivated successfully!");
                           loadFilters();
                       }else {
                           showError("Book deactivation failed.");
                       }

                   }else {
                       if (availableBookCount > 0){

                           boolean isUpdated = manageBookController.activeBook(book.getBookId());

                           if (isUpdated){
                               showSuccess("Book activated successfully!");
                               loadFilters();
                           }else {
                               showError("Book activation failed.");
                           }

                       } else if (availableBookCount == 0) {
                           showError("Cannot activate this book. Available copies are 0.");

                       }else {
                           showError("Unable to check available book count.");
                       }
                   }

                });

                pane.setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getIndex() < 0) {
                    setGraphic(null);
                } else {
                    BookDTO book = getTableView().getItems().get(getIndex());

                    if (book.getStatus().equals("ACTIVE")) {
                        btnStatus.setText("Deactivate");
                        btnStatus.getStyleClass().setAll("table-deactivate-button");
                    } else {
                        btnStatus.setText("Activate");
                        btnStatus.getStyleClass().setAll("table-activate-button");
                    }

                    setGraphic(pane);
                }
            }

        });

        txtSearchField.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    loadFilters();
                }
        );

        cmbBookCategories.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    loadFilters();
                }
        );

    }
}
