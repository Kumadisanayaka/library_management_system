package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class Issue_page_controller {

    @FXML
    private ComboBox<?> cboxSelectBook;

    @FXML
    private ComboBox<?> cboxSelectMember;

    @FXML
    private DatePicker datePickerDue;

    @FXML
    private DatePicker datePickerIssue;

    @FXML
    private Button issueBtn;

    @FXML
    void issueBtnOnAction(ActionEvent event) {

    }

}
