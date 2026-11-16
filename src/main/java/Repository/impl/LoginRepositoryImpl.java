package Repository.impl;

import Repository.LoginRepository;
import model.entity.UserEntity;
import db.util.HibernateUtil;
import org.hibernate.Session;

public class LoginRepositoryImpl implements LoginRepository {

    @Override
    public boolean checkUsernameAndPassword(String userName, String password) {

        String HQL = "FROM UserEntity u " +
                "WHERE u.username = :username " +
                "AND u.password = :password";

        try (Session session = HibernateUtil.getInstance()
                .getFactory().openSession()) {

            UserEntity user = session.createQuery(HQL, UserEntity.class)
                    .setParameter("username", userName)
                    .setParameter("password", password)
                    .uniqueResult();

            return user != null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
