package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_dashboard_page_controller {

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnBorrowingHistory;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnManageMembers;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Label lblBorrowodBooksCount;

    @FXML
    private Label lblOverdueCount;

    @FXML
    private Label lblTotalBooksCount;

    @FXML
    private Label lblTotalMembersCount;

    @FXML
    void addBookOnAction(ActionEvent event) {

        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/add_book_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Add Book");

        Stage dashboardStage = (Stage) btnAddBook.getScene().getWindow();
        dashboardStage.close();

    }

    @FXML
    void addMemberOnAction(ActionEvent event) {

        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/add_member_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Add Member");

        Stage dashboardStage = (Stage) btnAddMember.getScene().getWindow();
        dashboardStage.close();

    }

    @FXML
    void borrowingHistoryOnAction(ActionEvent event) {

        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/borrowing_history_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();;
        stage.setTitle("Borrowing History");

        Stage dashBoardStage = (Stage) btnBorrowingHistory.getScene().getWindow();
        dashBoardStage.close();
    }

    @FXML
    void issueBookOnAction(ActionEvent event) {

        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/issue_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Issue Book");

        Stage dashBoardStage = (Stage) btnIssueBook.getScene().getWindow();
        dashBoardStage.close();

    }

    @FXML
    void logOutOnAction(ActionEvent event) {

        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Login");

        Stage dashBoardStage = (Stage) btnLogOut.getScene().getWindow();
        dashBoardStage.close();

    }

    @FXML
    void manageMembersOnAction(ActionEvent event) {

        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/manage_member_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Manage Members");

        Stage dashBoardStage = (Stage) btnManageMembers.getScene().getWindow();

        dashBoardStage.close();

    }

    @FXML
    void returnBookOnAction(ActionEvent event) {

        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/return_book_page.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Return Book");

        Stage dashboardStage = (Stage) btnReturnBook.getScene().getWindow();
        dashboardStage.close();

    }

}
