package controller.addMemberController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Add_member_page_controller {

    @FXML
    private Button btnAdd;

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
    private Button btnManageMembers;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    @FXML
    private Label lblAddressError;

    @FXML
    private Label lblEmailError;

    @FXML
    private Label lblFullNameError;

    @FXML
    private Label lblPhoneNumberError;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnAddBookOnAction(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_book_page.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnAddBook.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Book");
        stage.centerOnScreen();

    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_member_page.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnAddMember.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Member");
        stage.centerOnScreen();

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

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
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/view_books_page.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) btnViewBooks.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("View Books");
        stage.centerOnScreen();

    }

}
