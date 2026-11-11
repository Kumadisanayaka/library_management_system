package controller.returnBookController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Return_book_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<String> getMemberIds() {

        ObservableList<String> memberIdList = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT member_id FROM book_issues WHERE status = 'Issued' ";

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

    public ObservableList<String> getBookIds(String memberId) {

        ObservableList<String> bookList = FXCollections.observableArrayList();

        String sql = "SELECT book_id FROM book_issues WHERE member_id = ? AND status = 'Issued' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,memberId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String id = resultSet.getString("book_id");
                bookList.add(id);
            }

            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public String getBookTitle(String bookId) {

        String sql = "SELECT book_title FROM books WHERE book_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String title = resultSet.getString("book_title");
                return title;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public LocalDate getIssueDate(String bookId,String memberId) {

        String sql = "SELECT issue_date FROM book_issues " +
                "WHERE member_id = ? " +
                "AND book_id = ? " +
                "AND status = 'Issued' " +
                "LIMIT 1";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, memberId);
            preparedStatement.setString(2, bookId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getDate("issue_date").toLocalDate();

            }

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public int getIssueId(String memberId, String bookId) {

        String sql = "SELECT issue_id FROM book_issues " +
                "WHERE member_id = ? " +
                "AND book_id = ? " +
                "AND status = 'Issued' " +
                "LIMIT 1";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, memberId);
            preparedStatement.setString(2, bookId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt("issue_id");

            }

            return -1;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public boolean returnBook(Integer issueId) {

        String sql = "UPDATE book_issues SET status = 'Returned' WHERE issue_id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,issueId);

            return  preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
