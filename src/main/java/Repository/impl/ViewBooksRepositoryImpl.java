package Repository.impl;

import Repository.ViewBooksRepository;
import db.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.ViewBookDTO;
import model.entity.BookCategoryEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ViewBooksRepositoryImpl implements ViewBooksRepository {

    public ObservableList<String> getCategories() {

        ObservableList<String> categoryList = FXCollections.observableArrayList();

        String hql = "FROM BookCategoryEntity b ";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()){

            List<BookCategoryEntity> results =
                    session.createQuery(hql,BookCategoryEntity.class).getResultList();

            for(BookCategoryEntity category : results) {
                categoryList.add(category.getShortCode() + " - " + category.getCategoryName());
            }

        } catch (Exception e) {
            System.err.println("Could not load Book Categories from DB : "+e.getMessage());
        }

        return categoryList;
    }

    public ObservableList<ViewBookDTO> applyFilters(String searchText, String category, String availability, String sort) {

        ObservableList<ViewBookDTO> bookList = FXCollections.observableArrayList();


        String hql = "SELECT b.bookId, b.bookTitle, " +
                "a.authorName, b.isbn, b.totalCopies, " +
                "b.availableCopies, bc.categoryName, b.status " +
                "FROM BookEntity b " +
                "INNER JOIN b.author a " +
                "INNER JOIN b.category bc " +
                "WHERE 1=1 ";

        if (searchText != null && !searchText.isBlank()){

            hql += " AND (b.bookTitle LIKE :search OR a.authorName LIKE :search) ";

        }

        if (category != null && !category.isBlank() && !category.equals("All Categories")){

            hql += " AND bc.categoryName LIKE :categoryName ";

        }

        if ("Available".equals(availability)){

            hql += " AND b.availableCopies > 0 ";

        } else if ("Unavailable".equals(availability)) {

            hql += " AND b.availableCopies = 0 ";
        }

        if ("Title (A-Z)".equals(sort)){

            hql += " ORDER BY b.bookTitle ASC ";

        } else if ("Title (Z-A)".equals(sort)) {

            hql += " ORDER BY b.bookTitle DESC ";

        } else if ("Author (A-Z)".equals(sort)) {

            hql += " ORDER BY a.authorName ASC ";

        } else if ("Author (Z-A)".equals(sort)) {

            hql += " ORDER BY a.authorName DESC ";

        }else {

            hql += " ORDER BY b.bookId ASC ";

        }

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            Query<Object[]> query = session.createQuery(hql, Object[].class);

            if (searchText != null && !searchText.isBlank()){

                query.setParameter("search","%" +searchText+ "%");
            }

            if (category != null && !category.isBlank() && !category.equals("All Categories")){

                String categoryName = category.split("-")[1].trim();

                query.setParameter("categoryName",categoryName);

            }

            List<Object[]> results = query.getResultList();

            for (Object[] result : results){
                ViewBookDTO viewBookDTO = new ViewBookDTO();
                viewBookDTO.setId((String) result[0]);
                viewBookDTO.setTitle((String) result[1]);
                viewBookDTO.setAuthor((String) result[2]);
                viewBookDTO.setIsbn((String) result[3]);
                viewBookDTO.setTotalCopies(((Number)result[4]).intValue());
                viewBookDTO.setAvailableCopies(((Number)result[5]).intValue());
                viewBookDTO.setCategory((String) result[6]);
                viewBookDTO.setStatus((String) result[7]);

                bookList.add(viewBookDTO);
            }

            return bookList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getCategoryBookCount(String category) {

        String hql = "SELECT COUNT(b) FROM BookEntity b " +
                "INNER JOIN b.category bc ";

        if (category != null && !category.isBlank() && !category.equals("All Categories")){
            hql += " WHERE bc.categoryName = :categoryName ";
        }

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()) {

            Query<Long> query = session.createQuery(hql,Long.class);

            if (category != null && !category.isBlank() && !category.equals("All Categories")){

                String categoryName = category.split("-")[1].trim();

                query.setParameter("categoryName",categoryName);
            }

            return query.getSingleResult().intValue();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
