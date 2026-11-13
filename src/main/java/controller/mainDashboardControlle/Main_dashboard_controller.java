package controller.mainDashboardControlle;

import controller.util.HibernateUtil;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookIssuedDTO;
import model.DashboardDTO;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Main_dashboard_controller {

    Connection connection = DBConnection.getInstance().getConnection();


    public ObservableList<BookIssuedDTO> getLoadIssuedBookData() {

        ObservableList<BookIssuedDTO> bookList = FXCollections.observableArrayList();

        String hql = "SELECT " +
                        "b.bookTitle, " +
                        "m.fullName, " +
                        "bi.issueDate, " +
                        "bi.dueDate, " +
                        "bi.status " +
                    "FROM BookIssueEntity bi " +
                    "INNER JOIN book b " +
                    "INNER JOIN member m " +
                    "ORDER BY bi.issueDate DESC, bi.issueId DESC ";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            List<Object[]> results = session.createQuery(hql,Object[].class)
                    .setMaxResults(10)
                    .getResultList();

           for (Object[] row : results){
               BookIssuedDTO issuedDTO = new BookIssuedDTO();
               issuedDTO.setBookTitle((String) row[0]);
               issuedDTO.setMemberName((String) row[1]);
               issuedDTO.setIssueDate((LocalDate) row[2]);
               issuedDTO.setDueDate((LocalDate) row[3]);
               issuedDTO.setStatus((String) row[4]);

               bookList.add(issuedDTO);
           }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bookList;

    }


    public DashboardDTO getDashboardCardData() {

        String hql = "SELECT " +
                "(SELECT COUNT(b) FROM BookEntity b), " +
                "(SELECT COUNT(m) FROM MemberEntity m), " +
                "(SELECT COUNT(bi) FROM BookIssueEntity bi), " +
                "(SELECT COUNT(bi) FROM BookIssueEntity bi " +
                "WHERE bi.status = 'Issued' " +
                "AND bi.dueDate < CURRENT_DATE) ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            Object[] result = session.createQuery(hql,Object[].class)
                    .getSingleResult();

            int totalBooks = ((Number) result[0]).intValue();
            int totalMembers = ((Number) result[1]).intValue();
            int totalIssuedBooks = ((Number) result[2]).intValue();
            int totalOverdueBooks = ((Number) result[3]).intValue();

            DashboardDTO dashboardDTO = new DashboardDTO();
            dashboardDTO.setTotalBookCount(totalBooks);
            dashboardDTO.setTotalMemberCount(totalMembers);
            dashboardDTO.setTotalIssuedBookCount(totalIssuedBooks);
            dashboardDTO.setTotalOverdueBookCount(totalOverdueBooks);

            return dashboardDTO;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
