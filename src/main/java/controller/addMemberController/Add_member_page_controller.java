package controller.addMemberController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Add_member_page_controller  {

    Add_member_form_validation_Controller add_member_form_validation_controller = new Add_member_form_validation_Controller();


    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullname;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtPhoneNumber;

    private void showError(String msg){

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Validation Error!");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    private void showSuccess(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("Member added successfully!");

        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) txtMemberId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addMemberOnAction(ActionEvent event) {

        //Member ID
        if (add_member_form_validation_controller.isEmpty(txtMemberId.getText())){

            showError("Please enter Member ID.");
            txtMemberId.requestFocus();
            return;

        }

        if (!add_member_form_validation_controller.isNumber(txtMemberId.getText())){

            showError("Member ID must contain numbers only.");
            txtMemberId.requestFocus();
            return;

        }

        //Full Name
        if (add_member_form_validation_controller.isEmpty(txtFullname.getText())){

            showError("Please enter Full Name.");
            txtFullname.requestFocus();
            return;

        }

        //Email
        if (add_member_form_validation_controller.isEmpty(txtEmail.getText())){

            showError("Please enter Email.");
            txtEmail.requestFocus();
            return;

        }

        if (!add_member_form_validation_controller.isValidEmail(txtEmail.getText())){

            showError("Please enter a valid Email address.");
            txtEmail.requestFocus();
            return;
        }

        //Address
        if (add_member_form_validation_controller.isEmpty(txtAddress.getText())){

            showError("Please enter Address.");
            txtAddress.requestFocus();
            return;

        }

        //PhoneNumber
        if (add_member_form_validation_controller.isEmpty(txtPhoneNumber.getText())){

            showError("Please enter Phone Number.");
            txtPhoneNumber.requestFocus();
            return;

        }

        if (!add_member_form_validation_controller.isValidPhoneNumber(txtPhoneNumber.getText())){

            showError("Phone Number must contain exactly 10 digits.");
            txtPhoneNumber.requestFocus();
            return;

        }

        showSuccess();


    }

    @FXML
    void backOnAction(ActionEvent event) {

     FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) txtMemberId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void clearOnAction(ActionEvent event) {

        txtMemberId.setText("");
        txtFullname.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");

    }

}
