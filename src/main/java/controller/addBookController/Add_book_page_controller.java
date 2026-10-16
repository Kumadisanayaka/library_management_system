package controller.addBookController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Add_book_page_controller implements Initializable {

    Add_book_controller add_book_controller = new Add_book_controller();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnAddbook;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageMembers;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private Label lblAuthorError;

    @FXML
    private Label lblBooktitleError;

    @FXML
    private Label lblCmbCategoryError;

    @FXML
    private Label lblDescriptionError;

    @FXML
    private Label lblIsbnError;

    @FXML
    private Label lblTotalCopiesError;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtTotalCopies;

    @FXML
    private Label lblDescriptionCount;

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_member_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) btnAddMember.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Member");
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        //Validate book title
        if (add_book_controller.isEmpty(txtBookTitle.getText())){
            lblBooktitleError.setText("Please Enter Book Title.");
            txtBookTitle.getStyleClass().add("input-error");
            txtBookTitle.requestFocus();
        }else{
            lblBooktitleError.setText("");
            txtBookTitle.getStyleClass().remove("input-error");
        }

        //Validate book author
        if (add_book_controller.isEmpty(txtAuthor.getText())){
            lblAuthorError.setText("Please Enter Author name.");
            txtAuthor.getStyleClass().add("input-error");
            txtAuthor.requestFocus();

        }else{
            lblAuthorError.setText("");
            txtAuthor.getStyleClass().remove("input-error");
        }

        //Validate ISBN
        if (add_book_controller.isEmpty(txtIsbn.getText())){
            lblIsbnError.setText("Please Enter ISBN Number.");
            txtIsbn.getStyleClass().add("input-error");
            txtIsbn.requestFocus();
        }else{
            lblIsbnError.setText("");
            txtIsbn.getStyleClass().remove("input-error");
        }

        //Validate Category
        if (add_book_controller.isEmpty(cmbCategory.getValue())){
            lblCmbCategoryError.setText("Please select a category.");
            cmbCategory.getStyleClass().add("input-error");
            cmbCategory.requestFocus();
        }else {
            lblCmbCategoryError.setText("");
            cmbCategory.getStyleClass().remove("input-error");
        }

        //Validate Description
        if (add_book_controller.isEmpty(txtDescription.getText())){
            lblDescriptionError.setText("Please Enter a Description.");
            txtDescription.getStyleClass().add("input-error");
            txtDescription.requestFocus();
        }else {
            lblDescriptionError.setText("");
            txtDescription.getStyleClass().remove("input-error");
        }

        //Validate Total copies
        if (add_book_controller.isEmpty(txtTotalCopies.getText())){
            lblTotalCopiesError.setText("Please Enter total copies.");
            txtTotalCopies.getStyleClass().add("input-error");
            txtTotalCopies.requestFocus();
        }else{
            try {
                int copies = Integer.parseInt(txtTotalCopies.getText());

                if (copies < 0){
                    lblTotalCopiesError.setText("Copies must be greater than 0.");
                    txtTotalCopies.requestFocus();
                }else{
                    lblTotalCopiesError.setText("");
                }
            } catch (NumberFormatException e) {
                lblTotalCopiesError.setText("Please enter a valid whole number.");
            }
        }


    }

    @FXML
    void btnAddbookOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnManageMembersOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

        txtBookTitle.setText("");
        txtAuthor.setText("");
        txtIsbn.setText("");
        txtDescription.setText("");
        txtTotalCopies.setText("");
        cmbCategory.setPromptText("Select Category");

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/view_books_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) btnViewBooks.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Books");
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbCategory.getItems().addAll(
                "Fiction",
                "Non-Fiction",
                "Science",
                "Technology",
                "Education",
                "History",
                "Biography",
                "Business",
                "Self-Help",
                "Literature",
                "Reference",
                "Children's Books"
        );

        cmbCategory.setPromptText("Select a Category");

        txtDescription.textProperty().addListener(
                (observable,oldValue,newValue) -> {

                    if (newValue.length() > 300){
                        txtDescription.setText(oldValue);
                    }

                    lblDescriptionCount.setText(
                            txtDescription.getText().length() + " / 300"
                    );


                });
    }
}
