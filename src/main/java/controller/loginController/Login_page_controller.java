package controller.loginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import scene.SceneManager;
import service.LoginService;
import service.impl.LoginServiceImpl;


public class Login_page_controller {

    LoginService service = new LoginServiceImpl();


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

        if (service.checkUsernameAndPassword(txtUsername.getText(), txtPassword.getText())) {

            SceneManager.switchPage("/view/main_dashboard_page.fxml");
        }else {
            lblErrorMsg.setText("Invalid username or password.");
        }
    }
    @FXML
    public void btnForgetPasswordOnAction(ActionEvent event) {
    }
}
