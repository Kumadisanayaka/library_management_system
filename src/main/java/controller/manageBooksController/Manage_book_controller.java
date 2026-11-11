package controller.manageBooksController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookCountDTO;
import model.BookDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Manage_book_controller {

    Connection connection = DBConnection.getInstance().getConnection();


    public ObservableList<String> getBookCategories() {

        ObservableList<String> bookCategoryList = FXCollections.observableArrayList();
        String SQL = "SELECT category_name,short_code FROM book_categories";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
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

    public ObservableList<BookDTO> applyFilters(String searchtxt, String category) {

        ObservableList<BookDTO> bookList = FXCollections.observableArrayList();

//        (book_id, book_title, isbn, total_copies, description,
//                available_copies, status, author_id, category_id) books table

//        INSERT INTO book_categories
//                (category_id, category_name, short_code) book categori table

//        INSERT INTO authors (author_id, author_name)

        String sql = "SELECT b.book_id, b.book_title, a.author_name, " +
                "bc.category_name, b.isbn, b.total_copies, b.available_copies, " +
                "b.description, b.status, b.author_id " +
                "FROM books b " +
                "INNER JOIN book_categories bc ON b.category_id = bc.category_id " +
                "INNER JOIN authors a ON b.author_id = a.author_id " +
                "WHERE 1=1 ";

        List<Object> parameters = new ArrayList<>();

        if (searchtxt != null && !searchtxt.isBlank()){

            sql += " AND(b.book_id LIKE ? OR b.book_title LIKE ? OR a.author_name LIKE ? ) ";

            parameters.add("%" +searchtxt+ "%");
            parameters.add("%" +searchtxt+ "%");
            parameters.add("%" +searchtxt+ "%");
        }

        if (category != null && !category.isBlank() && !category.equals("All Categories")){

            String categoryName = category.split("-")[1].trim();

            sql += "AND bc.category_name = ? ";

            parameters.add(categoryName);

        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < parameters.size(); i++){
                preparedStatement.setObject(i+1, parameters.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                BookDTO book = new BookDTO();
                book.setBookId(resultSet.getString("book_id"));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setAuthor(resultSet.getString("author_name"));
                book.setCategory(resultSet.getString("category_name"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTotalCopies(resultSet.getInt("total_copies"));
                book.setAvailablecopies(resultSet.getInt("available_copies"));
                book.setDescription(resultSet.getString("description"));
                book.setStatus(resultSet.getString("status"));


                bookList.add(book);
            }

            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateBook(String bookId,
                              String isbn,
                              String title,
                              int totalCopies,
                              String description
    ) {

        String sql = "UPDATE books SET book_title = ?, " +
                "isbn = ?, " +
                "total_copies = ?, " +
                "description = ? " +
                "WHERE book_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,isbn);
            preparedStatement.setInt(3,totalCopies);
            preparedStatement.setString(4,description);
            preparedStatement.setString(5,bookId);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getIssueCopiesCount(String bookId) {

        String sql = "SELECT COUNT(*) AS issue_copies " +
                "FROM book_issues " +
                "WHERE book_id = ? AND status = 'Issued' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int issueCount = resultSet.getInt("issue_copies");
                return issueCount;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public boolean updateAuthor(String author,Integer authorId) {

        String sql = "UPDATE authors SET author_name = ? WHERE author_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,author);
            preparedStatement.setInt(2,authorId);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer getAuthorId(String author) {

        String sql = "SELECT author_id FROM authors WHERE author_name = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,author);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt("author_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public BookCountDTO getBookCount() {

        String sql = "SELECT " +
                "(SELECT COUNT(*) FROM books) AS total_books, " +

                "(SELECT COUNT(*) FROM books " +
                "WHERE status = 'ACTIVE' AND available_copies > 0) AS available_books, " +

                "(SELECT COUNT(*) FROM book_issues " +
                "WHERE status = 'Issued') AS issued_books";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                int total = resultSet.getInt("total_books");
                int available = resultSet.getInt("available_books");
                int issued = resultSet.getInt("issued_books");

                BookCountDTO bookCountDTO = new BookCountDTO();
                bookCountDTO.setToatlBooks(total);
                bookCountDTO.setAvailableBooks(available);
                bookCountDTO.setIssuedBooks(issued);

                return bookCountDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getAvailableBookCount(String bookId) {

        String sql = "SELECT available_copies FROM books WHERE book_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                return resultSet.getInt("available_copies");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean deActiveBook(String bookId) {

        String sql = "UPDATE books SET status = 'INACTIVE' WHERE book_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookId);
            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean activeBook(String bookId) {

        String sql = "UPDATE books SET status = 'ACTIVE' WHERE book_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookId);
            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
