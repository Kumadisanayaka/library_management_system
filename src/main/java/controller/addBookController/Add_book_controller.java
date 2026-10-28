package controller.addBookController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Add_book_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<String> getBookCategories(){
        ObservableList<String> bookCategoryList = FXCollections.observableArrayList();
        String SQL = "SELECT category_name,short_code FROM book_categories";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String short_code = resultSet.getString("short_code");
                String category_name = resultSet.getString("category_name");
                bookCategoryList.add(short_code+" - "+category_name);
            }
        } catch (SQLException e) {
            System.err.println("Could not load Book Categories from DB : "+e.getMessage());
        }

        return bookCategoryList;

    }

    public boolean isEmpty(String iD) {

        return iD == null || iD.trim().isEmpty();
    }

    public boolean isNumber(String id) {

        return id.matches("\\d+");
    }
}
