package controller.viewBooksController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ViewBookDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public ObservableList<ViewBookDTO> applyFilters(String searchText, String category, String availability, String sort) {

        ObservableList<ViewBookDTO> bookList = FXCollections.observableArrayList();


        String sql = "SELECT b.book_id, b.book_title, " +
                "a.author_name, b.isbn, b.total_copies, " +
                "b.available_copies, bc.category_name, b.status " +
                "FROM books b " +
                "INNER JOIN authors a ON b.author_id = a.author_id " +
                "INNER JOIN book_categories bc ON b.category_id = bc.category_id " +
                "WHERE 1=1 ";

        List<Object> parameters = new ArrayList<>();

        if (searchText != null && !searchText.isBlank()){

            sql += " AND (b.book_title LIKE ? OR a.author_name LIKE ?) ";

            parameters.add("%" +searchText+ "%");
            parameters.add("%" +searchText+ "%");
        }

        if (category != null && !category.isBlank()){

            String categoryName = category.split("-")[1].trim();

            sql += " AND bc.category_name LIKE ? ";

            parameters.add(categoryName);
        }

        if ("Available".equals(availability)){

            sql += " AND b.available_copies > 0 ";

        } else if ("Unavailable".equals(availability)) {

            sql += " AND b.available_copies = 0 ";
        }

        if ("Title (A-Z)".equals(sort)){

            sql += " ORDER BY b.book_title ASC ";
            
        } else if ("Title (Z-A)".equals(sort)) {

            sql += " ORDER BY b.book_title DESC ";
            
        } else if ("Author (A-Z)".equals(sort)) {
            
            sql += " ORDER BY a.author_name ASC ";

        } else if ("Author (Z-A)".equals(sort)) {

            sql += " ORDER BY a.author_name DESC ";

        }else {

            sql += " ORDER BY b.book_id ASC ";

        }

        System.out.println(sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < parameters.size(); i++){

                preparedStatement.setObject(i+1,parameters.get(i));
            }

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

    public Integer getCategoryBookCount(String category) {

        String sql = "SELECT COUNT(*) FROM books b " +
                    "INNER JOIN book_categories bc " +
                    "ON b.category_id = bc.category_id ";

        if (category != null && !category.isBlank()){
            sql += " WHERE bc.category_name = ? ";
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (category != null && !category.isBlank()){
                String categoryName = category.split("-")[1].trim();
                preparedStatement.setString(1,categoryName);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
