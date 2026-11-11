package controller.manageMemberController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MemberDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class Edit_member_page_controller implements Initializable {

    Manage_member_controller manageMemberController = new Manage_member_controller();

    private Manage_member_Page_controller parentController;

    public void setParentController(Manage_member_Page_controller controller){

        this.parentController = controller;
    }

    private MemberDTO selectedMember;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdateMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateMemberOnAction(ActionEvent event) {

        String fullname = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phonenumber = txtPhoneNumber.getText();
        String id = selectedMember.getId();

        if(fullname.isBlank() || email.isBlank() || address.isBlank()){

            showError("Please fill all fields.");
            return;
        }

        if (!fullname.matches("[a-zA-Z ]+")){
            showError("Please enter a valid name.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            showError("Please enter a valid email address.");
            return;
        }

        if (!phonenumber.matches("^[0-9]{10,15}$")){
            showError("Please enter a valid phone number.");
            return;
        }

        boolean isUpdated = manageMemberController.updateMember(id,fullname,email,phonenumber,address);

        if (isUpdated){
            showSuccess("Member updated successfully!");

            Stage stage = (Stage) btnUpdateMember.getScene().getWindow();
            stage.close();

            parentController.loadFilters();
        }else {
            showError("Member update failed.");
        }

    }

    public void setMemberDate(MemberDTO member) {

        this.selectedMember = member;

        txtMemberId.setText(member.getId());
        txtName.setText(member.getFullName());
        txtAddress.setText(member.getAddress());
        txtEmail.setText(member.getEmail());
        txtPhoneNumber.setText(member.getPhoneNumber());

    }

    private void showError(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input!");
        alert.setContentText(message);

        alert.showAndWait();

    }

    private void showSuccess(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success");
        alert.setHeaderText("Operation Successful");
        alert.setContentText(message);

        alert.showAndWait();

    }

    private boolean isDataChanged(){

        return !txtName.getText().equals(selectedMember.getFullName())
                || !txtEmail.getText().equals(selectedMember.getEmail())
                || !txtPhoneNumber.getText().equals(selectedMember.getPhoneNumber())
                || !txtAddress.getText().equals(selectedMember.getAddress());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnUpdateMember.setDisable(true);
        txtMemberId.setEditable(false);

        txtName.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {

                    btnUpdateMember.setDisable(!isDataChanged());
        });

        txtEmail.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {

                    btnUpdateMember.setDisable(!isDataChanged());
                });

        txtPhoneNumber.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {

                    btnUpdateMember.setDisable(!isDataChanged());
                });

        txtAddress.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {

                    btnUpdateMember.setDisable(!isDataChanged());
                });


    }
}
