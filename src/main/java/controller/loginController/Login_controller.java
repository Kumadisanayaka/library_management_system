package controller.loginController;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public boolean checkUsernameAndPassword(String userName, String password) {

        String SQL = "SELECT username,password FROM users";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet =preparedStatement.executeQuery();

            while (resultSet.next()){

            String dbUsername = resultSet.getString("username");
            String dbPassword = resultSet.getString("password");

            if (dbUsername.equals(userName) && dbPassword.equals(password)){
                return true;
            }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  false;
    }
}
