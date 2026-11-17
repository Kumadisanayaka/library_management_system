package db.util;

import model.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;
    private static HibernateUtil instance;

    private HibernateUtil(){

        try {
            factory = new Configuration().configure()
                    .addAnnotatedClass(UserEntity.class)
                    .addAnnotatedClass(AuthorEntity.class)
                    .addAnnotatedClass(BookEntity.class)
                    .addAnnotatedClass(BookCategoryEntity.class)
                    .addAnnotatedClass(BookIssueEntity.class)
                    .addAnnotatedClass(MemberEntity.class)
                    .addAnnotatedClass(MembershipCategoryEntity.class)
                    .buildSessionFactory();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static HibernateUtil getInstance(){

        if (instance == null){

            instance = new HibernateUtil();

        }
        return instance;
    }

    public SessionFactory getFactory(){

        return factory;
    }
}
