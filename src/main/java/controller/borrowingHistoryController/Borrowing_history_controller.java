package controller.borrowingHistoryController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BorrowingHistoryDTO;
import model.StatusCountDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Borrowing_history_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<BorrowingHistoryDTO> applyFilters(String searchtxt, String status) {

        ObservableList<BorrowingHistoryDTO> historyList = FXCollections.observableArrayList();

        List<String> parameters = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT " +
                        "bi.issue_id, " +
                        "bi.member_id, " +
                        "m.full_name, " +
                        "bi.book_id, " +
                        "b.book_title, " +
                        "bi.issue_date, " +
                        "bi.due_date, " +
                        "CASE " +
                        "WHEN bi.status = 'Issued' " +
                        "AND bi.due_date < CURDATE() " +
                        "THEN 'Overdue' " +
                        "ELSE bi.status " +
                        "END AS display_status " +
                        "FROM book_issues bi " +
                        "INNER JOIN members m " +
                        "ON bi.member_id = m.member_id " +
                        "INNER JOIN books b " +
                        "ON bi.book_id = b.book_id " +
                        "WHERE 1=1 "
        );

        if (searchtxt != null && !searchtxt.trim().isEmpty()){

            sql.append(
                    "AND (bi.member_id LIKE ? " +
                    "OR bi.book_id LIKE ? " +
                    "OR b.book_title LIKE ?) "
            );

            String searchPattern = "%" + searchtxt.trim() + "%";

            parameters.add(searchPattern);
            parameters.add(searchPattern);
            parameters.add(searchPattern);
        }

        if (status != null && !status.equals("All Transactions")){

            if (status.equals("Overdue")){

                sql.append(
                        "AND bi.status = 'Issued' " +
                        "AND bi.due_date < CURDATE() "
                );
            } else if (status.equals("Issued")) {

                sql.append(
                        "AND bi.status = 'Issued' " +
                        "AND bi.due_date >= CURDATE() "
                );
            } else if (status.equals("Returned")) {

                sql.append(
                        "AND bi.status = 'Returned' "
                );

            }
        }

        sql.append("ORDER BY bi.issue_id DESC");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < parameters.size(); i++) {

                preparedStatement.setObject(i+1,parameters.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                BorrowingHistoryDTO historyDTO = new BorrowingHistoryDTO();
                historyDTO.setIssueId(resultSet.getInt("issue_id"));
                historyDTO.setMemberId(resultSet.getString("member_id"));
                historyDTO.setMemberName(resultSet.getString("full_name"));
                historyDTO.setBookId(resultSet.getString("book_id"));
                historyDTO.setBookTitle(resultSet.getString("book_title"));
                historyDTO.setIssueDate(resultSet.getDate("issue_date").toLocalDate());
                historyDTO.setDueDate(resultSet.getDate("due_date").toLocalDate());
                historyDTO.setStatus(resultSet.getString("display_status"));
                historyList.add(historyDTO);

            }
            return historyList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public StatusCountDTO getRecordCount() {

        String sql = "SELECT " +
                "COUNT(*) AS total_records, " +
                "SUM(CASE " +
                "WHEN status = 'Issued' " +
                "AND due_date >= CURDATE() " +
                "THEN 1 ELSE 0 END) AS issued_books, " +
                "SUM(CASE " +
                "WHEN status = 'Returned' " +
                "THEN 1 ELSE 0 END) AS returned_books, " +
                "SUM(CASE " +
                "WHEN status = 'Issued' " +
                "AND due_date < CURDATE() " +
                "THEN 1 ELSE 0 END) AS overdue_books " +
                "FROM book_issues";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                StatusCountDTO countDTO = new StatusCountDTO();
                countDTO.setTotalRecords(resultSet.getInt("total_records"));
                countDTO.setIssuedBooks(resultSet.getInt("issued_books"));
                countDTO.setReturnBooks(resultSet.getInt("returned_books"));
                countDTO.setOverDueBooks(resultSet.getInt("overdue_books"));

                return countDTO;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
