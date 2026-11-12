package controller.addMemberController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.MemberDTO;
import scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Add_member_page_controller implements Initializable {

    Add_member_form_validation_Controller addMemberFormValidationController = new Add_member_form_validation_Controller();
    Add_member_controller addMemberController = new Add_member_controller();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnGenerateMemberId;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageMembers;

    @FXML
    private Button btnManageBooks;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    @FXML
    private ComboBox<String> cmbMemberCategory;

    @FXML
    private Label lblAddressError;

    @FXML
    private Label lblEmailError;

    @FXML
    private Label lblFullNameError;

    @FXML
    private Label lblMemberIdError;

    @FXML
    private Label lblMemberCategoryError;

    @FXML
    private Label lblPhoneNumberError;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_book_page.fxml");
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_member_page.fxml");
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isValid = isValidAllInputs();

        if (isValid){
            Integer categoryId = addMemberController.getCategoryId(cmbMemberCategory.getValue());
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setFullName(txtFullName.getText());
            memberDTO.setId(txtMemberId.getText());
            memberDTO.setCategory(cmbMemberCategory.getValue());
            memberDTO.setEmail(txtEmail.getText());
            memberDTO.setPhoneNumber(txtPhoneNumber.getText());
            memberDTO.setAddress(txtAddress.getText());
            memberDTO.setCategoryId(categoryId);
            memberDTO.setStatus("Active");

            try {
                boolean isAdded = addMemberController.addMember(memberDTO);

                if (isAdded){
                    new Alert(Alert.AlertType.INFORMATION,"Member Added Successfully!\nMember ID : "+memberDTO.getId()).show();
                    clearFields();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Failed to Add Member!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error : "+e.getMessage()).show();
            }

        }
    }

    private void clearFields(){
        txtMemberId.clear();
        txtFullName.clear();
        cmbMemberCategory.setValue(null);
        txtEmail.clear();
        txtPhoneNumber.clear();
        txtAddress.clear();
    }

    private boolean isValidAllInputs(){

        //Validate member name
        if(addMemberFormValidationController.isEmpty(txtFullName.getText())){
            lblFullNameError.setText("Please Enter Full Name.");
            txtFullName.getStyleClass().add("input-error");
            txtFullName.requestFocus();
        }else {
            lblFullNameError.setText("");
            txtFullName.getStyleClass().remove("input-error");
        }

        //Validate member id
        if (addMemberFormValidationController.isEmpty(txtMemberId.getText())){
            lblMemberIdError.setText("Please Genarate Member ID.");
            txtMemberId.getStyleClass().add("input-error");
            txtMemberId.requestFocus();
        }else{
            lblMemberIdError.setText("");
            txtMemberId.getStyleClass().remove("input-error");
        }

        //Validate member Category
        if (addMemberFormValidationController.isEmpty(cmbMemberCategory.getValue())){
            lblMemberCategoryError.setText("Please Select a Category");
            cmbMemberCategory.getStyleClass().add("input-error");
            cmbMemberCategory.requestFocus();
        }else{
            lblMemberCategoryError.setText("");
            cmbMemberCategory.getStyleClass().remove("input-error");
        }

        //Validate Email
        if (addMemberFormValidationController.isEmpty(txtEmail.getText())){
            lblEmailError.setText("Please Enter a Email.");
            txtEmail.getStyleClass().add("input-error");
            txtEmail.requestFocus();
        } else if (!addMemberFormValidationController.isValidEmail(txtEmail.getText())) {
            lblEmailError.setText("Please Enter a Valid Email.");
            txtEmail.getStyleClass().add("input-error");
            txtEmail.requestFocus();
        }else{
            lblEmailError.setText("");
            txtEmail.getStyleClass().remove("input-error");
        }

        //Validate Phone number
        if (addMemberFormValidationController.isEmpty(txtPhoneNumber.getText())){
            lblPhoneNumberError.setText("Please Enter a Phone number.");
            txtPhoneNumber.getStyleClass().add("input-error");
            txtPhoneNumber.requestFocus();
        } else if (!addMemberFormValidationController.isNumber(txtPhoneNumber.getText())) {
            lblPhoneNumberError.setText("Please Enter Valide Phone number.");
            txtPhoneNumber.getStyleClass().add("input-error");
            txtPhoneNumber.requestFocus();
        }else{
            lblPhoneNumberError.setText("");
            txtPhoneNumber.getStyleClass().remove("input-error");
        }

        //Validate Address
        if (addMemberFormValidationController.isEmpty(txtAddress.getText())){
            lblAddressError.setText("Please Enter a Address.");
            txtAddress.getStyleClass().add("input-error");
            txtAddress.requestFocus();
        }else{
            lblAddressError.setText("");
            txtAddress.getStyleClass().remove("input-error");
        }

        return true;
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/main_dashboard_page.fxml");
    }

    @FXML
    void btnGenerateMemberIdOnAction(ActionEvent event) {
        String memberCategory = cmbMemberCategory.getValue();

        if (memberCategory == null){
            new Alert(Alert.AlertType.WARNING,"Please select Member Category to generate Member ID!").show();
            cmbMemberCategory.requestFocus();
            return;
        }else{
            try{
                txtMemberId.setText(addMemberController.genarateMemberId(memberCategory));
                txtMemberId.setEditable(false);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Failed to generate Member ID : "+e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/borrowing_history_page.fxml");
    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/issue_page.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnManageMembersOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_member_page.fxml");
    }

    @FXML
    public void btnManageBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_books_page.fxml");
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    public void loadMemberCategories(){
        ObservableList<String> memberCategoryList = addMemberController.getMemberCategories();
        cmbMemberCategory.setItems(memberCategoryList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMemberCategories();
        txtMemberId.setEditable(false);
        cmbMemberCategory.setPromptText("Select a Category");
    }

}
