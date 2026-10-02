package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Borrowing_history_page_controller {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> bookTitleCol;

    @FXML
    private TableColumn<?, ?> dueDateCol;

    @FXML
    private TableColumn<?, ?> issueDateCol;

    @FXML
    private TableColumn<?, ?> memberIdCol;

    @FXML
    private TableColumn<?, ?> returnDateCol;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    void backBtnOnAction(ActionEvent event) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) searchTxt.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {

    }

}
