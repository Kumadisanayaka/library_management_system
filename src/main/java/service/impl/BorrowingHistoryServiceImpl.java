package controller.borrowingHistoryController;

import db.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.BorrowingHistoryDTO;
import model.dto.StatusCountDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class Borrowing_history_controller {

    public ObservableList<BorrowingHistoryDTO> applyFilters(String searchtxt, String status) {

        ObservableList<BorrowingHistoryDTO> historyList = FXCollections.observableArrayList();

        String hql = "SELECT bi.issueId, " +
                "bi.member.memberId, " +
                "bi.member.fullName, " +
                "bi.book.bookId, " +
                "bi.book.bookTitle, " +
                "bi.issueDate, " +
                "bi.dueDate, " +
                "CASE " +
                "WHEN bi.status = 'Issued' AND bi.dueDate < CURRENT_DATE " +
                "THEN 'Overdue' " +
                "ELSE bi.status " +
                "END " +
                "FROM BookIssueEntity bi " +
                "WHERE 1=1 ";

        if (searchtxt != null && !searchtxt.isBlank()) {

            hql += " AND (bi.member.memberId LIKE :search " +
                    "OR bi.book.bookId LIKE :search " +
                    "OR bi.book.bookTitle LIKE :search) ";
        }

        if (status != null && !status.equals("All Transactions")) {

            if (status.equals("Overdue")) {

                hql += " AND bi.status = 'Issued' " +
                        "AND bi.dueDate < CURRENT_DATE ";

            } else if (status.equals("Issued")) {

                hql += " AND bi.status = 'Issued' " +
                        "AND bi.dueDate >= CURRENT_DATE ";

            } else if (status.equals("Returned")) {

                hql += " AND bi.status = 'Returned' ";
            }
        }

        hql += " ORDER BY bi.issueId DESC ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            Query<Object[]> query = session.createQuery(hql, Object[].class);

            if (searchtxt != null && !searchtxt.isBlank()) {

                query.setParameter("search", "%" + searchtxt.trim() + "%");
            }

            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {

                BorrowingHistoryDTO historyDTO = new BorrowingHistoryDTO();

                historyDTO.setIssueId((Integer) result[0]);
                historyDTO.setMemberId((String) result[1]);
                historyDTO.setMemberName((String) result[2]);
                historyDTO.setBookId((String) result[3]);
                historyDTO.setBookTitle((String) result[4]);
                historyDTO.setIssueDate((LocalDate) result[5]);
                historyDTO.setDueDate((LocalDate) result[6]);
                historyDTO.setStatus((String) result[7]);

                historyList.add(historyDTO);
            }

            return historyList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public StatusCountDTO getRecordCount() {

        String hql = "SELECT COUNT(bi), " +
                "SUM(CASE " +
                "WHEN bi.status = 'Issued' " +
                "AND bi.dueDate >= CURRENT_DATE " +
                "THEN 1 ELSE 0 END), " +
                "SUM(CASE " +
                "WHEN bi.status = 'Returned' " +
                "THEN 1 ELSE 0 END), " +
                "SUM(CASE " +
                "WHEN bi.status = 'Issued' " +
                "AND bi.dueDate < CURRENT_DATE " +
                "THEN 1 ELSE 0 END) " +
                "FROM BookIssueEntity bi";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            Object[] result = session.createQuery(hql, Object[].class)
                    .getSingleResult();

            StatusCountDTO countDTO = new StatusCountDTO();

            countDTO.setTotalRecords(((Number) result[0]).intValue());
            countDTO.setIssuedBooks(((Number) result[1]).intValue());
            countDTO.setReturnBooks(((Number) result[2]).intValue());
            countDTO.setOverDueBooks(((Number) result[3]).intValue());

            return countDTO;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
