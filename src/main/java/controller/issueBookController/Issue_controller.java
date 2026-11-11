package controller.issueBookController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import model.BookIssuedDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Issue_controller {

    Connection connection = DBConnection.getInstance().getConnection();


    public ObservableList<String> getBookIds(String memberId) {

        ObservableList<String> bookIdList = FXCollections.observableArrayList();

        String sql = "SELECT b.book_id FROM books b " +
                "WHERE b.status = 'ACTIVE' " +
                "AND b.available_copies > 0 " +
                "AND NOT EXISTS (" +
                "SELECT 1 FROM book_issues bi " +
                "WHERE bi.member_id = ? " +
                "AND bi.book_id = b.book_id " +
                "AND bi.status = 'Issued')";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, memberId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String bookId = resultSet.getString("book_id");

                bookIdList.add(bookId);

            }

            return bookIdList;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public ObservableList<String> getMemberIds() {

        ObservableList<String> memberIdList = FXCollections.observableArrayList();

        String sql = "SELECT member_id FROM members WHERE status = 'Active' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String memberId = resultSet.getString("member_id");
                memberIdList.add(memberId);

            }

            return memberIdList;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    public String getMemberName(String memberId) {

        String sql = "SELECT full_name FROM members WHERE member_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,memberId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                String name = resultSet.getString("full_name");
                return name;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getBookTitle(String bookId) {

        String sql = "SELECT book_title FROM books WHERE book_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                String bookTitle = resultSet.getString("book_title");
                return bookTitle;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean issueBook(BookIssuedDTO bookIssuedDTO) {

        String sql = " INSERT INTO book_issues " +
                " (member_id, book_id, issue_date, due_date, status) " +
                " VALUES (?,?,?,?,?) ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookIssuedDTO.getMemberId());
            preparedStatement.setString(2,bookIssuedDTO.getBookId());
            preparedStatement.setDate(3,java.sql.Date.valueOf(bookIssuedDTO.getIssueDate()));
            preparedStatement.setDate(4,java.sql.Date.valueOf(bookIssuedDTO.getDueDate()));
            preparedStatement.setString(5,bookIssuedDTO.getStatus());

           return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateAvailableBookCount(String bookId) {

        String sql = "UPDATE books " +
                "SET available_copies = available_copies - 1 " +
                "WHERE book_id = ? AND available_copies > 0";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            preparedStatement.setString(1, bookId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
