package controller.mainDashboardControlle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BookIssuedDTO;
import scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Main_dashboard_page_controller  implements Initializable {

    Main_dashboard_controller mainDashboardController = new Main_dashboard_controller();

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
    private Button btnManageBooks;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    @FXML
    private TableColumn<BookIssuedDTO, String> clmBookTitle;

    @FXML
    private TableColumn<BookIssuedDTO, String> clmDueDate;

    @FXML
    private TableColumn<BookIssuedDTO, String> clmIssueDate;

    @FXML
    private TableColumn<BookIssuedDTO, String> clmMember;

    @FXML
    private TableColumn<BookIssuedDTO, String> clmStatus;

    @FXML
    private Label lblBookIssued;

    @FXML
    private Label lblOverDueBooks;

    @FXML
    private Label lblTotalBook;

    @FXML
    private Label lblTotalMember;

    @FXML
    private TableView<BookIssuedDTO> tblRecentIssuedBooks;

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
    void btnManageMembersOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_member_page.fxml");
    }

    @FXML
    public void btnManageBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_books_page.fxml");
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    private void loadTableDate(){
        ObservableList<BookIssuedDTO> issuedBookList = mainDashboardController.getLoadIssuedBookData();
        tblRecentIssuedBooks.setItems(issuedBookList);
    }

    private void loadTotalBookCount(){
        Integer totalBooks = mainDashboardController.getTotalBookCount();
        lblTotalBook.setText(String.valueOf(totalBooks));

    }

    private void loadTotalMemberCount(){
        Integer totalMembers = mainDashboardController.getTotalMemberCount();
        lblTotalMember.setText(String.valueOf(totalMembers));
    }

    private void loadTotalIssuedBookCount(){
        Integer totalIssuedBooks = mainDashboardController.getTotalIssuedBookCount();
        lblBookIssued.setText(String.valueOf(totalIssuedBooks));
    }

    private void loadTotalOverdueBookCount(){
        Integer totalOverdueBooks = mainDashboardController.getTotalOverdueBookCount();
        lblOverDueBooks.setText(String.valueOf(totalOverdueBooks));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTableDate();
        loadTotalBookCount();
        loadTotalMemberCount();
        loadTotalIssuedBookCount();
        loadTotalOverdueBookCount();

        clmBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        clmMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        clmIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        clmDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }
}
