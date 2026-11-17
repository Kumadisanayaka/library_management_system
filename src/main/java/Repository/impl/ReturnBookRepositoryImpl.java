package Repository.impl;

import Repository.ReturnBookRepository;
import db.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class ReturnBookRepositoryImpl implements ReturnBookRepository {

    public ObservableList<String> getMemberIds() {

        ObservableList<String> memberIdList = FXCollections.observableArrayList();

        String hql = "SELECT DISTINCT bi.member.memberId " +
                "FROM BookIssueEntity bi " +
                "WHERE bi.status = 'Issued'";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            List<String> results = session.createQuery(hql, String.class)
                    .getResultList();

            memberIdList.addAll(results);

            return memberIdList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getMemberName(String memberId) {

        String hql = "SELECT m.fullName " +
                "FROM MemberEntity m " +
                "WHERE m.memberId = :memberId";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            String name = session.createQuery(hql, String.class)
                    .setParameter("memberId", memberId)
                    .uniqueResult();

            return name;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<String> getBookIds(String memberId) {

        ObservableList<String> bookList = FXCollections.observableArrayList();

        String hql = "SELECT bi.book.bookId " +
                "FROM BookIssueEntity bi " +
                "WHERE bi.member.memberId = :memberId " +
                "AND bi.status = 'Issued'";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            List<String> results = session.createQuery(hql, String.class)
                    .setParameter("memberId", memberId)
                    .getResultList();

            bookList.addAll(results);

            return bookList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getBookTitle(String bookId) {

        String hql = "SELECT b.bookTitle " +
                "FROM BookEntity b " +
                "WHERE b.bookId = :bookId";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            String title = session.createQuery(hql, String.class)
                    .setParameter("bookId", bookId)
                    .uniqueResult();

            return title;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate getIssueDate(String bookId, String memberId) {

        String hql = "SELECT bi.issueDate " +
                "FROM BookIssueEntity bi " +
                "WHERE bi.member.memberId = :memberId " +
                "AND bi.book.bookId = :bookId " +
                "AND bi.status = 'Issued'";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            LocalDate issueDate = session.createQuery(hql, LocalDate.class)
                    .setParameter("memberId", memberId)
                    .setParameter("bookId", bookId)
                    .setMaxResults(1)
                    .uniqueResult();

            return issueDate;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getIssueId(String memberId, String bookId) {

        String hql = "SELECT bi.issueId " +
                "FROM BookIssueEntity bi " +
                "WHERE bi.member.memberId = :memberId " +
                "AND bi.book.bookId = :bookId " +
                "AND bi.status = 'Issued'";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            Integer issueId = session.createQuery(hql, Integer.class)
                    .setParameter("memberId", memberId)
                    .setParameter("bookId", bookId)
                    .setMaxResults(1)
                    .uniqueResult();

            return issueId != null ? issueId : -1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean returnBook(Integer issueId) {

        String hql = "UPDATE BookIssueEntity bi " +
                "SET bi.status = 'Returned' " +
                "WHERE bi.issueId = :issueId";

        Transaction transaction = null;

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            transaction = session.beginTransaction();

            int rows = session.createMutationQuery(hql)
                    .setParameter("issueId", issueId)
                    .executeUpdate();

            transaction.commit();

            return rows > 0;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(e);
        }
    }
}
