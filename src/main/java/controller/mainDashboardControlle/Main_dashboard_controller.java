package controller.mainDashboardControlle;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookIssuedDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main_dashboard_controller {

    Connection connection = DBConnection.getInstance().getConnection();


    public ObservableList<BookIssuedDTO> getLoadIssuedBookData() {

        ObservableList<BookIssuedDTO> bookList = FXCollections.observableArrayList();

        String sql = "SELECT " +
                        "b.book_title, " +
                        "m.full_name, " +
                        "bi.issue_date, " +
                        "bi.due_date, " +
                        "bi.status " +
                    "FROM book_issues bi " +
                    "INNER JOIN books b " +
                        "ON bi.book_id = b.book_id " +
                    "INNER JOIN members m " +
                        "ON bi.member_id = m.member_id " +
                    "ORDER BY bi.issue_date DESC, bi.issue_id DESC LIMIT 5 ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                BookIssuedDTO bookIssuedDTO = new BookIssuedDTO();
                bookIssuedDTO.setBookTitle(resultSet.getString("book_title"));
                bookIssuedDTO.setMemberName(resultSet.getString("full_name"));
                bookIssuedDTO.setIssueDate(resultSet.getDate("issue_date").toLocalDate());
                bookIssuedDTO.setDueDate(resultSet.getDate("due_date").toLocalDate());
                bookIssuedDTO.setStatus(resultSet.getString("status"));

                bookList.add(bookIssuedDTO);

            }

            return bookList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Integer getTotalBookCount() {

        String sql = "SELECT COUNT(*) FROM books";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Integer getTotalMemberCount() {

        String sql = "SELECT COUNT(*) FROM members";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Integer getTotalIssuedBookCount() {

        String sql = "SELECT COUNT(*) FROM book_issues";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Integer getTotalOverdueBookCount() {

        String sql = "SELECT COUNT(*) FROM book_issues WHERE status = 'Issued' AND due_date < CURDATE()";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
