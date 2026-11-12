package controller.returnBookController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import scene.SceneManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Return_book_page_controller implements Initializable {

    Return_book_controller returnBookController = new Return_book_controller();

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnClear;

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
    private Button btnReturn;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBook;

    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private ComboBox<String> cmbMemberId;

    @FXML
    private DatePicker datepickerIssueDate;

    @FXML
    private DatePicker datepickerReturnDate;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtMemberName;

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_book_page.fxml");
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_member_page.fxml");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        refreshPage();
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
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

        String memberId = cmbMemberId.getValue();
        String memberName = txtMemberName.getText();
        String bookId = cmbBookId.getValue();
        String bookTitle = txtBookTitle.getText();
        LocalDate issueDate = datepickerIssueDate.getValue();
        LocalDate returnDate = datepickerReturnDate.getValue();

        if (memberId == null) {
            showError("Please select a Member ID.");
            return;
        }

        if (memberName == null || memberName.trim().isEmpty()) {
            showError("Member name is required.");
            return;
        }

        if (bookId == null) {
            showError("Please select a Book ID.");
            return;
        }

        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            showError("Book title is required.");
            return;
        }

        if (issueDate == null) {
            showError("Issue date is required.");
            return;
        }

        if (returnDate == null) {
            showError("Return date is required.");
            return;
        }

        if (returnDate.isBefore(issueDate)) {
            showError("Return date cannot be before the issue date.");
            return;
        }

        Integer issueId = returnBookController.getIssueId(memberId,bookId);

        if (issueId != null){

            boolean isReturned = returnBookController.returnBook(issueId);

            if (isReturned){
                showSuccess("Book Returned sucessfully!");
                refreshPage();
            }else {
                showError("Book Return Failed!");
            }
        }
    }

    @FXML
    void btnViewBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    private void loadMemberIds(){

        ObservableList<String> memberList = returnBookController.getMemberIds();
        cmbMemberId.setItems(memberList);

    }

    private void loadMemberName(){

        String memberName = returnBookController.getMemberName(cmbMemberId.getValue());
        txtMemberName.setText(memberName);
    }

    private void loadBookIds(){

        ObservableList<String> bookIdList = returnBookController.getBookIds(cmbMemberId.getValue());
        cmbBookId.setItems(bookIdList);
    }

    private void loadBookTitle(){

        String bookTitle = returnBookController.getBookTitle(cmbBookId.getValue());
        txtBookTitle.setText(bookTitle);
    }

    private void loadIssueDate(){

        LocalDate issuedDate = returnBookController.getIssueDate(cmbBookId.getValue(),cmbMemberId.getValue());
        datepickerIssueDate.setValue(issuedDate);
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

    private void refreshPage() {

        cmbMemberId.setValue(null);
        cmbMemberId.setPromptText("Select Member ID");
        txtMemberName.clear();

        cmbBookId.setPromptText("Select Available Book ID");
        txtBookTitle.clear();

        datepickerIssueDate.setValue(null);
        datepickerReturnDate.setValue(LocalDate.now());

        loadMemberIds();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMemberIds();
        txtMemberName.setEditable(false);
        txtBookTitle.setEditable(false);
        datepickerIssueDate.setEditable(false);
        datepickerReturnDate.setEditable(false);
        datepickerReturnDate.setValue(LocalDate.now());

        cmbMemberId.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    loadMemberName();
                    loadBookIds();
                }
        );

        cmbBookId.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                  loadBookTitle();
                  loadIssueDate();
                }
        );

    }
}
