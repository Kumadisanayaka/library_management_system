package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    }

    @FXML
    void borrowingHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void issueBookOnAction(ActionEvent event) {

    }

    @FXML
    void logOutOnAction(ActionEvent event) {

    }

    @FXML
    void manageMembersOnAction(ActionEvent event) {

    }

    @FXML
    void returnBookOnAction(ActionEvent event) {

    }

}
