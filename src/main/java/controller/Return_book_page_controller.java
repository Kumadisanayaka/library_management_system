package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Return_book_page_controller {

    @FXML
    private Button backBtn;

    @FXML
    private TextField bookIdTxt;

    @FXML
    private TextField bookTitleTxt;

    @FXML
    private TextField borrowedDateTxt;

    @FXML
    private TextField dueDateTxt;

    @FXML
    private TextField memberIDtxt;

    @FXML
    private TextField memberNameTxt;

    @FXML
    private Button returnBookBtn;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField searchTxt;

    @FXML
    private Label statusLbl;

    @FXML
    void backBtnOnAction(ActionEvent event) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) memberIDtxt.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void returnBookBtnOnAction(ActionEvent event) {

    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {

    }

}
