package controller.manageBooksController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BookDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class Edit_book_page_controller implements Initializable {

    Manage_book_controller manageBookController = new Manage_book_controller();

    private Manage_books_page_controller parentController;

    public void setParentController(Manage_books_page_controller controller){
        this.parentController = controller;
    }

    private BookDTO selectedBook;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdateBook;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtAvailableCopies;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtTotalCopies;

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateBookOnAction(ActionEvent event) {


        String bookId = txtBookId.getText();

        int issuecopy = manageBookController.getIssueCopiesCount(bookId);

        String isbn = txtIsbn.getText();
        String title = txtBookTitle.getText();
        String author = txtAuthor.getText();
        String totalCopies = txtTotalCopies.getText();
        String description = txtDescription.getText();

        if (isbn.isEmpty()){
            showError("Book ISBN is required");
            return;
        }

        if (title.isEmpty()){
            showError("Book title is required");
            return;
        }

        if (title.length() < 2 || title.length() > 100){
            showError("Book title must contain 2-100 characters");
            return;
        }

        if (author.isEmpty()){
            showError("Author name is required");
            return;
        }

        if (author.length() < 2 || author.length() > 100) {
            showError("Author name must contain 2-100 characters");
            return;
        }

        if (totalCopies.isEmpty()){
            showError("Total copies is required");
            return;
        }

        if (!totalCopies.matches("\\d+")){
            showError("Total copies must be a valid number");
            return;
        }

        int totalC = Integer.parseInt(totalCopies);


        if (totalC < issuecopy){

            showError("Total copies cannot be less than issued copies (" +issuecopy+ ")");
            return;
        }

        if (totalC < 0){
            showError("Total copies must be greater than zero");
            return;
        }

        if (description.length() > 300){
            showError("Description cannot exceed 300 characters");
            return;
        }

        boolean isAuthorChanged = !selectedBook.getAuthor().equals(author);

        boolean isOtherBookdetailsChange =
                !selectedBook.getBookTitle().equals(title)
                || !selectedBook.getIsbn().equals(isbn)
                || !selectedBook.getTotalCopies().equals(totalC)
                || !selectedBook.getDescription().equals(description);

        if (!isOtherBookdetailsChange && !isAuthorChanged) {

            showError("No changes detected.");

        } else {

            boolean isUpdatedOther = true;
            boolean isUpdatedAuthor = true;

            if (isOtherBookdetailsChange) {

                isUpdatedOther = manageBookController.updateBook(
                        bookId, isbn, title, totalC, description
                );
            }

            if (isAuthorChanged) {

                Integer authorId = manageBookController.getAuthorId(selectedBook.getAuthor());

                isUpdatedAuthor = manageBookController.updateAuthor(author,authorId);
            }

            if (isUpdatedOther && isUpdatedAuthor) {

                showSuccess("Book updated successfully!");

                Stage stage = (Stage) btnUpdateBook.getScene().getWindow();
                stage.close();

                parentController.loadFilters();

            } else {

                showError("Book update failed.");
            }
        }

    }


    public void setBookData(BookDTO book) {

        this.selectedBook = book;

        int issuecopy = manageBookController.getIssueCopiesCount(selectedBook.getBookId());

        int total = selectedBook.getTotalCopies();

        int available = total - issuecopy;

        txtBookId.setText(book.getBookId());
        txtIsbn.setText(book.getIsbn());
        txtBookTitle.setText(book.getBookTitle());
        txtAuthor.setText(book.getAuthor());
        txtCategory.setText(book.getCategory());
        txtTotalCopies.setText(String.valueOf(book.getTotalCopies()));
        txtAvailableCopies.setText(String.valueOf(available));
        txtDescription.setText(book.getDescription());

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

        return !txtIsbn.getText().equals(selectedBook.getIsbn())
                || !txtBookTitle.getText().equals(selectedBook.getBookTitle())
                || !txtAuthor.getText().equals(selectedBook.getAuthor())
                || !txtTotalCopies.getText().equals(selectedBook.getTotalCopies())
                || !txtDescription.getText().equals(selectedBook.getDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUpdateBook.setDisable(true);

        txtBookId.setEditable(false);
        txtCategory.setEditable(false);
        txtAvailableCopies.setEditable(false);

        txtIsbn.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    btnUpdateBook.setDisable(!isDataChanged());
                }
        );

        txtBookTitle.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    btnUpdateBook.setDisable(!isDataChanged());
                }
        );

        txtAuthor.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    btnUpdateBook.setDisable(!isDataChanged());
                }
        );

        txtTotalCopies.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    btnUpdateBook.setDisable(!isDataChanged());
                }
        );

        txtDescription.textProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    btnUpdateBook.setDisable(!isDataChanged());
                }
        );
    }

}
