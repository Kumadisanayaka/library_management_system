package controller.addBookController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.BookDTO;
import scene.SceneManager;

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
    private Button btnManageBooks;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    @FXML
    private Button btnGenarateBookId;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private Label lblAuthorError;

    @FXML
    private Label lblBookIdError;

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
    private TextField txtBookId;

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
        SceneManager.switchPage("/view/add_member_page.fxml");
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

        //Validate Book ID
        if (add_book_controller.isEmpty(txtBookId.getText())){
            lblBookIdError.setText("Plese Genarate Book ID.");
            txtBookId.getStyleClass().add("input-error");
            txtBookId.requestFocus();
        }else {
            lblBookIdError.setText("");
            txtBookId.getStyleClass().remove("input-error");
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

        String id = txtBookId.getText().trim();
        String title = txtBookTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String category = cmbCategory.getValue().trim();
        String isbn = txtIsbn.getText().trim();
        String description = txtDescription.getText().trim();
        Integer totalCopies = Integer.parseInt(txtTotalCopies.getText().trim());

        BookDTO book = new BookDTO(id,title,author,category,isbn,totalCopies,description);
        Integer authorId = add_book_controller.getAuthorId(author);
        Integer categoryId = add_book_controller.getCategoryId(category);

        try {
            boolean isAdded = add_book_controller.addBook(book,authorId,categoryId);

            if (isAdded){
                new Alert(Alert.AlertType.INFORMATION, "Book Added Successfully!\nBook ID : " + book.getBookId()).show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add Book!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error : "+e.getMessage()).show();
        }

    }

    private void clearFields(){
        txtBookId.clear();
        txtBookTitle.clear();
        txtAuthor.clear();
        txtIsbn.clear();
        txtDescription.clear();
        txtTotalCopies.clear();
        cmbCategory.setValue(null);
    }

    @FXML
    void btnAddbookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_book_page.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/main_dashboard_page.fxml");
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

        txtBookTitle.setText("");
        txtAuthor.setText("");
        txtIsbn.setText("");
        txtDescription.setText("");
        txtTotalCopies.setText("");
        cmbCategory.setPromptText("Select Category");

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    public void loadBookCategories(){
        ObservableList<String> bookCategories = add_book_controller.getBookCategories();
        cmbCategory.setItems(bookCategories);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtBookId.setEditable(false);
        loadBookCategories();
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
    @FXML
    public void btnGenarateBookIdOnAction(ActionEvent event) {
        String bookCategory = cmbCategory.getValue();

        if (bookCategory == null){
            new Alert(Alert.AlertType.WARNING,"Please select Book Category to generate Book ID!").show();
            cmbCategory.requestFocus();
            return;
        }

        try {
            String bookId = add_book_controller.genarateBookId(bookCategory);
            txtBookId.setText(bookId);
            txtBookId.setEditable(false);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate Book ID : "+e.getMessage()).show();
        }

    }

}
