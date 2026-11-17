package Repository.impl;

import Repository.ManageBookRepository;
import db.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.BookCountDTO;
import model.dto.BookDTO;
import model.entity.AuthorEntity;
import model.entity.BookCategoryEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ManageBookRepositoryImpl implements ManageBookRepository {

    public ObservableList<String> getBookCategories() {

        ObservableList<String> bookCategoryList = FXCollections.observableArrayList();
        String HQL = "FROM BookCategoryEntity ";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()){

            List<BookCategoryEntity> categories =
                    session.createQuery(HQL,BookCategoryEntity.class).getResultList();

            for (BookCategoryEntity category : categories){

                String shortCode = category.getShortCode();
                String categoryName = category.getCategoryName();

                bookCategoryList.add(shortCode + " - " +categoryName);
            }

        } catch (Exception e) {
            System.err.println("Could not load Book Categories from DB : " +e.getMessage());
        }

        return bookCategoryList;
    }

    public ObservableList<BookDTO> applyFilters(String searchtxt, String category) {

        ObservableList<BookDTO> bookList = FXCollections.observableArrayList();

        String hql = "SELECT b.bookId, b.bookTitle, a.authorName, bc.categoryName, " +
                "b.isbn, b.totalCopies, b.availableCopies, b.description, b.status " +
                "FROM BookEntity b " +
                "INNER JOIN b.category bc " +
                "INNER JOIN b.author a " +
                "WHERE 1=1 ";

        if (searchtxt != null && !searchtxt.isBlank()){

            hql += " AND(b.bookId LIKE :search " +
                    "OR b.bookTitle LIKE :search " +
                    "OR a.authorName LIKE :search) ";
        }

        if (category != null && !category.isBlank() && !category.equals("All Categories")){

            hql += " AND bc.categoryName = :categoryName ";

        }

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            Query<Object[]> query = session.createQuery(hql, Object[].class);

            if (searchtxt != null && !searchtxt.isBlank()){

                query.setParameter("search","%" + searchtxt + "%");
            }

            if (category != null && !category.isBlank() && !category.equals("All Categories")){

                String categoryName = category.split("-")[1].trim();
                query.setParameter("categoryName",categoryName);
            }

            List<Object[]> results = query.getResultList();

            for(Object[] result : results){
                BookDTO bookDTO = new BookDTO();
                bookDTO.setBookId((String) result[0]);
                bookDTO.setBookTitle((String) result[1]);
                bookDTO.setAuthor((String) result[2]);
                bookDTO.setCategory((String) result[3]);
                bookDTO.setIsbn((String) result[4]);
                bookDTO.setTotalCopies(((Number)result[5]).intValue());
                bookDTO.setAvailablecopies(((Number)result[6]).intValue());
                bookDTO.setDescription((String)result[7]);
                bookDTO.setStatus((String)result[8]);
                bookList.add(bookDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    public boolean updateBook(String bookId,
                              String isbn,
                              String title,
                              int totalCopies,
                              String description
    ) {

        String hql = "UPDATE BookEntity b SET " +
                "b.bookTitle = :title, " +
                "b.isbn = :isbn, " +
                "b.totalCopies = :totalCopies, " +
                "b.description = :description " +
                "WHERE bookId = :bookId ";

        Transaction transaction = null;

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){
            transaction = session.beginTransaction();

            int rows = session.createMutationQuery(hql)
                    .setParameter("title",title)
                    .setParameter("isbn",isbn)
                    .setParameter("totalCopies",totalCopies)
                    .setParameter("description",description)
                    .setParameter("bookId",bookId)
                    .executeUpdate();

            transaction.commit();

            return rows > 0;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public int getIssueCopiesCount(String bookId) {

        String hql = "SELECT COUNT(bi) " +
                "FROM BookIssueEntity bi " +
                "WHERE bi.book.bookId = :bookId " +
                "AND bi.status = 'Issued'";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            Long issueCount = session.createQuery(hql, Long.class)
                    .setParameter("bookId",bookId)
                    .getSingleResult();

            return issueCount.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateAuthor(String author,Integer authorId) {

        String hql = "UPDATE AuthorEntity SET authorName = :authorName WHERE authorId = :authorId ";

        Transaction transaction = null;

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            transaction = session.beginTransaction();

            int rows = session.createMutationQuery(hql)
                    .setParameter("authorName",author)
                    .setParameter("authorId",authorId)
                    .executeUpdate();

            transaction.commit();

            return rows > 0;
        } catch (Exception e) {
            if (transaction.isActive()){
                try {
                    transaction.rollback();
                } catch (Exception rollbackError) {
                    e.addSuppressed(rollbackError);
                }

            }
            throw new RuntimeException(e);
        }
    }

    public Integer getAuthorId(String author) {

        String hql = "FROM AuthorEntity a " +
                "WHERE LOWER(TRIM(a.authorName)) = :authorName";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            AuthorEntity authorEntity = session.createQuery(hql,AuthorEntity.class)
                    .setParameter("authorName",author.trim().toLowerCase())
                    .uniqueResult();

            return authorEntity.getAuthorId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BookCountDTO getBookCount() {

        String hql = "SELECT " +
                "(SELECT COUNT(b) FROM BookEntity b), " +
                "(SELECT COUNT(b) FROM BookEntity b " +
                "WHERE b.status = 'ACTIVE' AND b.availableCopies > 0), " +
                "(SELECT COUNT(bi) FROM BookIssueEntity bi " +
                "WHERE bi.status = 'Issued') ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            Object[] result = session.createQuery(hql,Object[].class)
                    .getSingleResult();

            int totalBook = ((Number)result[0]).intValue();
            int availableBook = ((Number)result[1]).intValue();
            int issueBook = ((Number)result[2]).intValue();

            BookCountDTO bookCountDTO = new BookCountDTO();
            bookCountDTO.setToatlBooks(totalBook);
            bookCountDTO.setAvailableBooks(availableBook);
            bookCountDTO.setIssuedBooks(issueBook);

            return bookCountDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getAvailableBookCount(String bookId) {

        String hql = "SELECT b.availableCopies " +
                "FROM BookEntity b " +
                "WHERE b.bookId = :bookId";

        try (Session session = HibernateUtil.getInstance()
                .getFactory().openSession()) {

            Integer availableCopies = session.createQuery(hql, Integer.class)
                    .setParameter("bookId", bookId)
                    .uniqueResult();

            return availableCopies != null ? availableCopies : -1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deActiveBook(String bookId) {

        String hql = "UPDATE BookEntity b SET b.status = 'INACTIVE' WHERE b.bookId = :bookId ";

        Transaction transaction = null;

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            transaction = session.beginTransaction();

            int rows = session.createMutationQuery(hql)
                    .setParameter("bookId",bookId)
                    .executeUpdate();

            transaction.commit();

            return rows > 0;
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }


    public boolean activeBook(String bookId) {

        String hql = "UPDATE BookEntity b SET b.status = 'ACTIVE' WHERE b.bookId = :bookId ";

        Transaction transaction = null;

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            transaction = session.beginTransaction();

            int rows = session.createMutationQuery(hql)
                    .setParameter("bookId", bookId)
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
