package controller.addMemberController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Add_member_controller {

    Connection connection = DBConnection.getInstance().getConnection();

    public ObservableList<String> getMemberCategories(){
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

    public String genarateMemberId(String memberCategory){

        String categoryShortCode = memberCategory.split("-")[0].trim();

        String sql = "SELECT member_id FROM members WHERE member_id LIKE ? ORDER BY member_id DESC LIMIT 1";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,categoryShortCode + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String lastMemberId = resultSet.getString("member_id");
                if (lastMemberId != null && lastMemberId.length() > categoryShortCode.length()){
                    String lastNumberStr = lastMemberId.substring(categoryShortCode.length());

                    try {
                        int nextNumber = Integer.parseInt(lastNumberStr) + 1;
                        return categoryShortCode + String.format("%03d",nextNumber);
                    } catch (NumberFormatException e) {
                    }
                }
            }

            return categoryShortCode + "001";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getCategoryId(String memberCategory){
        String shortCode = memberCategory.split("-")[0].trim();

        String sql = "SELECT * FROM membership_categories";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String sc = resultSet.getString("short_code");
                if (shortCode.equalsIgnoreCase(sc.trim())){
                    return resultSet.getInt("category_id");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean addMember(MemberDTO memberDTO) {

        String sql = "INSERT INTO members (" +
                    "member_id, " +
                    "full_name," +
                    "email, " +
                    "phone," +
                    "address," +
                    "category_id, " +
                    "status) " +
                    "VALUES" +
                    "(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,memberDTO.getId());
            preparedStatement.setString(2,memberDTO.getFullName());
            preparedStatement.setString(3,memberDTO.getEmail());
            preparedStatement.setString(4,memberDTO.getPhoneNumber());
            preparedStatement.setString(5,memberDTO.getAddress());
            preparedStatement.setInt(6,memberDTO.getCategoryId());
            preparedStatement.setString(7,memberDTO.getStatus());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
