package controller.addBookController;

import controller.entity.AuthorEntity;
import controller.entity.BookCategoryEntity;
import controller.entity.BookEntity;
import controller.util.HibernateUtil;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

public class Add_book_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<String> getBookCategories(){
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

    //Validation check
    public boolean isEmpty(String iD) {

        return iD == null || iD.trim().isEmpty();
    }

    public boolean isNumber(String id) {

        return id.matches("\\d+");
    }

    //Genarate Book ID
    public String genarateBookId(String bookCategory) {

        String categoryShortCode = bookCategory.split("-")[0].trim();

        String hql = "FROM BookEntity b " +
                "WHERE b.bookId LIKE :pattern " +
                "ORDER BY b.bookId DESC";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()){

            BookEntity lastBook = session.createQuery(hql,BookEntity.class)
                    .setParameter("pattern",categoryShortCode +"%")
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastBook != null){

                String lastBookId = lastBook.getBookId();

                if (lastBookId.length() > categoryShortCode.length()){

                    String lastBookIdstr = lastBookId.substring(categoryShortCode.length());

                    int nextNumber = Integer.parseInt(lastBookIdstr)+1;

                    return categoryShortCode + String.format("%03d",nextNumber);

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return categoryShortCode + "001";

    }

    public boolean addBook(BookDTO book, Integer authorId, Integer categoryId) {

       Transaction transaction = null;

       try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

           transaction = session.beginTransaction();

           AuthorEntity author = session.find(AuthorEntity.class, authorId);

           BookCategoryEntity category = session.find(BookCategoryEntity.class, categoryId);

           if (author == null && category == null){
               transaction.rollback();
               return false;
           }

           BookEntity newBook = new BookEntity();
           newBook.setBookId(book.getBookId());
           newBook.setBookTitle(book.getBookTitle());
           newBook.setIsbn(book.getIsbn());
           newBook.setTotalCopies(book.getTotalCopies());
           newBook.setDescription(book.getDescription());
           newBook.setAvailableCopies(book.getTotalCopies());
           newBook.setStatus(book.getStatus());
           newBook.setAuthor(author);
           newBook.setCategory(category);
           session.persist(newBook);

           return true;
       } catch (Exception e) {

           if (transaction != null && transaction.isActive()){
               transaction.rollback();
           }
           throw new RuntimeException(e);
       }

    }

    public Integer getAuthorId(String author) {

        String hql = "FROM AuthorEntity a " +
                "WHERE LOWER(TRIM(a.authorName)) = :authorName";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            AuthorEntity existingAuthor = session.createQuery(hql,AuthorEntity.class)
                    .setParameter("authorName",author.trim().toLowerCase())
                    .setMaxResults(1)
                    .uniqueResult();

            if (existingAuthor != null){
                return existingAuthor.getAuthorId();
            }

            AuthorEntity newAuthor = new AuthorEntity();
            newAuthor.setAuthorName(author.trim());

            Transaction transaction = session.beginTransaction();
            session.persist(newAuthor);
            transaction.commit();

            return newAuthor.getAuthorId();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Integer getCategoryId(String category) {

        String shortCode = category.split("-")[0].trim();

        String hql = "FROM BookCategoryEntity bc " +
                "WHERE bc.shortCode = :shortCode ";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            BookCategoryEntity bookCategory = session.createQuery(hql,BookCategoryEntity.class)
                    .setParameter("shortCode",shortCode)
                    .uniqueResult();

            if (bookCategory != null){

                return bookCategory.getCategoryId();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
