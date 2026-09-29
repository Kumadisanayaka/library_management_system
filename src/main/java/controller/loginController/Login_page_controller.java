package controller.loginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
            lblerror.setText("");
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/main_dashboard_page.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();

            Stage loginstage = (Stage) btnlogin.getScene().getWindow();

            loginstage.close();

        }else{
            lblerror.setText("Your entered invalid username or password");
        }


    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

}
