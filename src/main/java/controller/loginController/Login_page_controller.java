package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login_page_controller {

    Login_controller loginController = new Login_controller();

    @FXML
    private Button btnlogin;

    @FXML
    private Button btnreset;

    @FXML
    private Label lblerror;

    @FXML
    private Label lblpassword;

    @FXML
    private Label lbluname;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void loginOnAction(ActionEvent event) {

        if (loginController.checkUsernameAndPassword(txtusername.getText(),txtpassword.getText())){



        }else{
            lblerror.setText("Your entered invalid username or password");
        }


    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

}
