package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Manage_member_Page_controller {

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private Button deleteMemberBtn;

    @FXML
    private Button editMemberBtn;

    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TableColumn<?, ?> fullNameCol;

    @FXML
    private TableColumn<?, ?> memberIdCol;

    @FXML
    private TableView<?> memberTbl;

    @FXML
    private TableColumn<?, ?> phoneNumberCol;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    void deleteMemberBtnOnAction(ActionEvent event) {

    }

    @FXML
    void editMemberBtnOnAction(ActionEvent event) {

    }

    @FXML
    void searchBtnOnAction(ActionEvent event) {

    }

    @FXML
    void searchInputOnAction(ActionEvent event) {

    }

}
