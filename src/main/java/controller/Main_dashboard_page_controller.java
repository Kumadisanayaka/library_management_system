package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

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

    }

    @FXML
    void btnAddbookOnAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_book_page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnAddbook.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Book");
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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

    }

}
