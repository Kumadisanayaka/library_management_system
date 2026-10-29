package controller.addBookController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookDTO;

import java.sql.*;

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

    //Validation check
    public boolean isEmpty(String iD) {

        return iD == null || iD.trim().isEmpty();
    }

    public boolean isNumber(String id) {

        return id.matches("\\d+");
    }

    //Genarate Book ID
    public String genarateBookId(String bookCategory) {

        String categoryShortCode = bookCategory.split("-")[0].trim();

        String sql = "SELECT book_id FROM books WHERE book_id LIKE ? ORDER BY book_id DESC LIMIT 1";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,categoryShortCode + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String lastBookId =resultSet.getString("book_id");
                if (lastBookId != null && lastBookId.length() > categoryShortCode.length()){
                    String lastNumberStr = lastBookId.substring(categoryShortCode.length());

                    try {
                        int nextNumber = Integer.parseInt(lastNumberStr) + 1;
                        return categoryShortCode + String.format("%03d",nextNumber);
                    } catch (NumberFormatException ignored) {
                    }

                }
            }

            return categoryShortCode + "001";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean addBook(BookDTO book, Integer authorId, Integer categoryId) {

        String sql = "INSERT INTO books(" +
                    "book_id, " +
                    "book_title, " +
                    "isbn, " +
                    "total_copies, " +
                    "description," +
                    "available_copies, " +
                    "status, " +
                    "author_id, " +
                    "category_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,book.getBookId());
            preparedStatement.setString(2,book.getBookTitle());
            preparedStatement.setString(3,book.getIsbn());
            preparedStatement.setInt(4,book.getTotalCopies());
            preparedStatement.setString(5,book.getDescription());
            preparedStatement.setInt(6,book.getTotalCopies());
            preparedStatement.setString(7,"ACTIVE");
            preparedStatement.setInt(8,authorId);
            preparedStatement.setInt(9,categoryId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Integer getAuthorId(String author) {

        String sql = "SELECT * FROM authors";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String authorName = resultSet.getString("author_name");

                if (author.trim().equalsIgnoreCase(authorName.trim())){
                    return resultSet.getInt("author_id");
                }
            }

            String sql2 = "INSERT INTO authors (author_name) VALUES (?)";

            PreparedStatement insertStatement = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,author.trim());
            insertStatement.executeUpdate();

            ResultSet genaratedKey = insertStatement.getGeneratedKeys();

            if (genaratedKey.next()){
                return genaratedKey.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Integer getCategoryId(String category) {

        String shortCode = category.split("-")[0].trim();

        String sql = "SELECT * FROM book_categories";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String sc = resultSet.getString("short_code");
                if (shortCode.equalsIgnoreCase(sc.trim())){
                    return resultSet.getInt("category_id");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
