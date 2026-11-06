package controller.manageMemberController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manage_member_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<String> getMemberCategories() {

        ObservableList<String> memberCategoryList = FXCollections.observableArrayList();
        String sql = "SELECT category_name,short_code FROM membership_categories";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String short_code = resultSet.getString("short_code");
                String category_name = resultSet.getString("category_name");

                memberCategoryList.add(short_code+" - "+category_name);
            }
        } catch (SQLException e) {
            System.err.println("Could not load Member Categories from DB : " + e.getMessage());
        }

        return memberCategoryList;

    }
}
