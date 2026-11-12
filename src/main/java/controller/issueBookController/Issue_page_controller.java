package controller.issueBookController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.BookIssuedDTO;
import scene.SceneManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Issue_page_controller implements Initializable {

    Issue_controller issueController = new Issue_controller();

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnGoAddMember;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnIssue;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageBooks;

    @FXML
    private Button btnManageMembers;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBook;

    @FXML
    private ComboBox<String> cmbBookId;

    @FXML
    private ComboBox<String> cmbMemberId;

    @FXML
    private DatePicker datePickerDueDate;

    @FXML
    private DatePicker datePickerIssueDate;

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
    void btnGoAddMemberOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_member_page.fxml");
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
    void btnIssueOnAction(ActionEvent event) {

        String memberId = cmbMemberId.getValue();
        String memberName = txtMemberName.getText();
        String bookId = cmbBookId.getValue();
        String bookTitle = txtBookTitle.getText();
        LocalDate issueDate = datePickerIssueDate.getValue();
        LocalDate dueDate = datePickerDueDate.getValue();


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

        if (dueDate == null) {
            showError("Please select a due date.");
            return;
        }

        if (!dueDate.isAfter(issueDate)) {
            showError("Due date must be after the issue date.");
            return;
        }

        BookIssuedDTO bookIssuedDTO = new BookIssuedDTO();
        bookIssuedDTO.setMemberId(memberId);
        bookIssuedDTO.setBookId(bookId);
        bookIssuedDTO.setIssueDate(issueDate);
        bookIssuedDTO.setDueDate(dueDate);
        bookIssuedDTO.setStatus("Issued");

        boolean isIssued = issueController.issueBook(bookIssuedDTO);
        boolean isUpdatedavailableCount = issueController.updateAvailableBookCount(bookId);

        if (isIssued && isUpdatedavailableCount){
            showSuccess("Book Issued Successfully!");
            refreshPage();
        }else {
            showError("Book issue failed!");
        }

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
    void btnViewBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    private void loadBookIds(){

        ObservableList<String> bookIdList = issueController.getBookIds(cmbMemberId.getValue());
        cmbBookId.setItems(bookIdList);
    }

    private void loadMemberIds(){

        ObservableList<String> memberList = issueController.getMemberIds();
        cmbMemberId.setItems(memberList);
    }

    private void loadMemberName(){
        String memberName = issueController.getMemberName(cmbMemberId.getValue());
        txtMemberName.setText(memberName);
    }

    private void loadBookTitle(){
        String bookTitle = issueController.getBookTitle(cmbBookId.getValue());
        txtBookTitle.setText(bookTitle);
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

        cmbMemberId.setPromptText("Select Member ID");
        txtMemberName.clear();

        cmbBookId.setPromptText("Select Available Book ID");
        txtBookTitle.clear();

        datePickerIssueDate.setValue(LocalDate.now());
        datePickerDueDate.setValue(null);

        loadMemberIds();
        loadBookIds();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        loadMemberIds();
        txtMemberName.setEditable(false);
        txtBookTitle.setEditable(false);
        datePickerIssueDate.setValue(LocalDate.now());
        datePickerIssueDate.setDisable(true);

        cmbMemberId.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    loadMemberName();
                    loadBookIds();
                }
        );

        cmbBookId.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    loadBookTitle();
                }
        );
    }
}
