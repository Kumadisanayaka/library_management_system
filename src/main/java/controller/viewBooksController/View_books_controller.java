package controller.viewBooksController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ViewBookDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class View_books_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<String> getCategories() {

        ObservableList<String> categoryList = FXCollections.observableArrayList();

        String sql = "SELECT category_name,short_code FROM book_categories";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String shortCode = resultSet.getString("short_code");
                String categoryName = resultSet.getString("category_name");

                categoryList.add(shortCode+" - "+categoryName);
            }

        } catch (SQLException e) {
            System.err.println("Could not load Book Categories from DB : "+e.getMessage());
        }
        return categoryList;
    }

    public ObservableList<ViewBookDTO> searchBooksByTitleOrAuthor(String searchText) {

        ObservableList<ViewBookDTO> bookList = FXCollections.observableArrayList();

        String sql = "SELECT b.book_id, b.book_title, " +
                "a.author_name, b.isbn, b.total_copies, " +
                "b.available_copies, bc.category_name, b.status " +
                "FROM books b " +
                "INNER JOIN authors a ON b.author_id = a.author_id " +
                "INNER JOIN book_categories bc ON b.category_id = bc.category_id " +
                "WHERE b.book_title LIKE ? OR a.author_name LIKE ? " +
                "ORDER BY b.book_title ASC";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%" +searchText+ "%");
            preparedStatement.setString(2,"%" +searchText+ "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ViewBookDTO viewBookDTO = new ViewBookDTO();
                viewBookDTO.setId(resultSet.getString("book_id"));
                viewBookDTO.setTitle(resultSet.getString("book_title"));
                viewBookDTO.setAuthor(resultSet.getString("author_name"));
                viewBookDTO.setIsbn(resultSet.getString("isbn"));
                viewBookDTO.setCategory(resultSet.getString("category_name"));
                viewBookDTO.setTotalCopies(resultSet.getInt("total_copies"));
                viewBookDTO.setAvailableCopies(resultSet.getInt("available_copies"));
                viewBookDTO.setStatus(resultSet.getString("status"));

                bookList.add(viewBookDTO);
            }

            return bookList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
