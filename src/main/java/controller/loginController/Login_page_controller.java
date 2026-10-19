package controller.loginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import scene.SceneManager;


public class Login_page_controller {

    Login_controller login_controller = new Login_controller();


    @FXML
    private Button btnLogin;

    @FXML
    private Label lblErrorMsg;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        if (login_controller.checkUsernameAndPassword(txtUsername.getText(),txtPassword.getText())){

            SceneManager.switchPage("/view/main_dashboard_page.fxml");
        }

    }
    @FXML
    public void btnForgetPasswordOnAction(ActionEvent event) {
    }
}
