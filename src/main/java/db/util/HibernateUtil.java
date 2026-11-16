package controller.util;

import controller.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;
    private static HibernateUtil instance;

    private HibernateUtil(){

        try {
            factory = new Configuration()
                    .setProperty("hibernate.connection.url",
                            "jdbc:mysql://localhost:3306/library_management_system_v1?createDatabaseIfNotExist=true")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "1234")
                    .setProperty("hibernate.hbm2ddl.auto", "validate")
                    .setProperty("hibernate.show_sql","true")
                    .setProperty("hibernate.format_sql","true")
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
