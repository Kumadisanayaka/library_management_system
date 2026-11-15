package controller.issueBookController;

import controller.entity.BookEntity;
import controller.entity.BookIssueEntity;
import controller.entity.MemberEntity;
import controller.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookIssuedDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Issue_controller {

    public ObservableList<String> getBookIds(String memberId) {

        ObservableList<String> bookIdList = FXCollections.observableArrayList();

        String hql = "SELECT b.bookId FROM BookEntity b " +
                "WHERE b.status = 'ACTIVE' " +
                "AND b.availableCopies > 0 " +
                "AND NOT EXISTS (" +
                "SELECT 1 FROM BookIssueEntity bi " +
                "WHERE bi.member.memberId = :memberId " +
                "AND bi.book.bookId = b.bookId " +
                "AND bi.status = 'Issued')";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            List<String> results = session.createQuery(hql,String.class)
                    .setParameter("memberId",memberId)
                    .getResultList();

            bookIdList.addAll(results);

            return bookIdList;

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public ObservableList<String> getMemberIds() {

        ObservableList<String> memberIdList = FXCollections.observableArrayList();

        String hql = "SELECT m.memberId FROM MemberEntity m WHERE m.status = 'Active' ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            List<String> results = session.createQuery(hql,String.class)
                    .getResultList();

            memberIdList.addAll(results);

            return memberIdList;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    public String getMemberName(String memberId) {

        String hql = "SELECT m.fullName FROM MemberEntity m WHERE m.memberId = :memberId ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            String memberName = session.createQuery(hql,String.class)
                    .setParameter("memberId",memberId)
                    .uniqueResult();

            return memberName;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getBookTitle(String bookId) {

        String hql = "SELECT b.bookTitle FROM BookEntity b WHERE b.bookId = :bookId ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            String bookTitle = session.createQuery(hql,String.class)
                    .setParameter("bookId",bookId)
                    .uniqueResult();

            return bookTitle;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean issueBook(BookIssuedDTO bookIssuedDTO) {

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                MemberEntity member = session.find(MemberEntity.class,bookIssuedDTO.getMemberId());
                BookEntity book = session.find(BookEntity.class,bookIssuedDTO.getBookId());

                if (member == null || book == null){
                    transaction.rollback();
                    return false;
                }

                BookIssueEntity bookIssueEntity = new BookIssueEntity();
                bookIssueEntity.setMember(member);
                bookIssueEntity.setBook(book);
                bookIssueEntity.setIssueDate(bookIssuedDTO.getIssueDate());
                bookIssueEntity.setDueDate(bookIssuedDTO.getDueDate());
                bookIssueEntity.setStatus(bookIssuedDTO.getStatus());
                session.persist(bookIssueEntity);

                transaction.commit();

                return true;
            } catch (Exception e) {

                if (transaction.isActive()){
                    transaction.rollback();
                }
                throw e;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateAvailableBookCount(String bookId) {

        String hql = "UPDATE BookEntity b " +
                "SET b.availableCopies = b.availableCopies - 1 " +
                "WHERE b.bookId = :bookId AND b.availableCopies > 0";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                int rows = session.createMutationQuery(hql)
                        .setParameter("bookId", bookId)
                        .executeUpdate();

                transaction.commit();

                return rows > 0;

            } catch (Exception e) {

                if (transaction.isActive()) {
                    transaction.rollback();
                }

                throw e;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
