package controller.manageMemberController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import model.MemberDTO;
import scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Manage_member_Page_controller implements Initializable {

    Manage_member_controller manageMemberController = new Manage_member_controller();

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddMember;

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
    private Button btnResetFilters;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnViewBooks;

    @FXML
    private TableView<MemberDTO> tblMember;

    @FXML
    private TableColumn<MemberDTO, Void> clmAction;

    @FXML
    private TableColumn<MemberDTO, String> clmCategory;

    @FXML
    private TableColumn<MemberDTO, String> clmEmail;

    @FXML
    private TableColumn<MemberDTO, String> clmFullName;

    @FXML
    private TableColumn<MemberDTO, String> clmMemberId;

    @FXML
    private TableColumn<MemberDTO, String> clmPhone;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private Label lblOtherMembersCount;

    @FXML
    private Label lblStudentMembersCount;

    @FXML
    private Label lblTotalMembersCount;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_book_page.fxml");
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/add_member_page.fxml");
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
    void btnResetFiltersOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/return_book_page.fxml");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/view_books_page.fxml");
    }

    @FXML
    public void btnManageBooksOnAction(ActionEvent event) {
        SceneManager.switchPage("/view/manage_books_page.fxml");
    }

    @FXML
    void txtSearchTypeOnAction(KeyEvent event) {

    }

    private void loadMemberCategories(){

        ObservableList<String> categoryList = manageMemberController.getMemberCategories();

        cmbCategory.setItems(categoryList);
    }

    private void loadFilters(){
        String searchText = txtSearch.getText();
        String category = cmbCategory.getValue();

        ObservableList<MemberDTO> memberDTOObservableList = manageMemberController.applyFilters(searchText,category);

        tblMember.setItems(memberDTOObservableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadMemberCategories();

        cmbCategory.setValue("All Categories");

        clmAction.setCellFactory(column -> new TableCell<MemberDTO, Void>() {

            private final Button btnEdit = new Button("Edit");
            private final Button btnDelete = new Button("Delete");

            private final HBox pane = new HBox(5, btnEdit, btnDelete);

            {
                btnEdit.setOnAction(event -> {

                    MemberDTO member = getTableView()
                            .getItems()
                            .get(getIndex());

                    // Edit action
                });

                btnDelete.setOnAction(event -> {

                    MemberDTO member = getTableView()
                            .getItems()
                            .get(getIndex());

                    // Delete action
                });
            }


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });

        txtSearch.textProperty().addListener(
                (observable, oldValue, newValue) ->{
                    loadFilters();
                }
        );

        cmbCategory.valueProperty().addListener(
                (observable, oldValue, newValue) ->{
                    loadFilters();
                });

        clmMemberId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        clmFullName.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );

        clmCategory.setCellValueFactory(
                new PropertyValueFactory<>("category")
        );

        clmEmail.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        clmPhone.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber")
        );
    }

}
