package controller.loginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

            FXMLLoader  loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

            try {
                Parent root = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.setTitle("Main Dashboard");
                stage.centerOnScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }

}
