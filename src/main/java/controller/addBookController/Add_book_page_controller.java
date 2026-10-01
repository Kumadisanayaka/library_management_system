package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtPublishedYear;

    @FXML
    private TextField txtQuantity;

    private void showError(String msg){

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();

    }

    private void showSuccess(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Book added successfully!");

        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_dashboard_page.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = (Stage) txtBookId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addOnAction(ActionEvent event) {

        //BookID
    if(add_book_controller.isEmpty(txtBookId.getText())){

        showError("Please Enter Book ID.");
        txtBookId.requestFocus();
        return;

    }

    if(!add_book_controller.isNumber(txtBookId.getText())){

        showError("Book ID must contain numbers only.");
        txtBookId.requestFocus();
        return;

    }

    //BookTitle
    if(add_book_controller.isEmpty(txtBookTitle.getText())){

        showError("Please Enter Book Title.");
        txtBookTitle.requestFocus();
        return;

    }

    //Author
    if (add_book_controller.isEmpty(txtAuthor.getText())){

        showError("Please Enter Author name.");
        txtAuthor.requestFocus();
        return;

    }

    //Category
    if (cbCategory.getValue() == null){

        showError("Please select a category.");
        cbCategory.requestFocus();
        return;

    }


    //Published Year
    if(add_book_controller.isEmpty(txtPublishedYear.getText())){

        showError("Please enter Published Year.");
        txtPublishedYear.requestFocus();
        return;

    }

    if (!add_book_controller.isNumber(txtPublishedYear.getText())){

        showError("Published Year must contain 4 digits.");
        txtPublishedYear.requestFocus();
        return;

    }

    //Quantity
    if (add_book_controller.isEmpty(txtQuantity.getText())){

        showError("Please enter Quantity.");
        txtQuantity.requestFocus();
        return;
    }

    if (!add_book_controller.isNumber(txtQuantity.getText())){

        showError("Quantity must contain numbers only.");
        txtQuantity.requestFocus();
        return;
    }

    if (Integer.parseInt(txtQuantity.getText()) <= 0 ){

        showError("Quantity must be greater than 0.");
        return;
    }

    showSuccess();


    }

    @FXML
    void backOnAction(ActionEvent event) {

    }

    @FXML
    void clearOnAction(ActionEvent event) {

        txtBookId.setText("");
        txtBookTitle.setText("");
        txtAuthor.setText("");
        txtPublishedYear.setText("");
        txtQuantity.setText("");

        txtBookId.requestFocus();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCategory.getItems().addAll(
                "Fiction",
                "Non-Fiction",
                "Science",
                "Technology",
                "History",
                "Biography",
                "Education"
        );
    }
}
