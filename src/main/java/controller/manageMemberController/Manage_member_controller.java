package controller.manageMemberController;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MemberCountDTO;
import model.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public ObservableList<MemberDTO> applyFilters(String searchText, String category) {

        ObservableList<MemberDTO> memberList = FXCollections.observableArrayList();

        String sql = "SELECT m.member_id, m.full_name, m.phone, " +
                "mc.category_name, m.email, m.status, m.address, m.category_id " +
                "FROM members m " +
                "INNER JOIN membership_categories mc ON m.category_id = mc.category_id " +
                "WHERE 1=1 AND m.status = 'Active' ";

        List<Object> parameters = new ArrayList<>();

        if (searchText != null && !searchText.isBlank()){

            sql += " AND (m.member_id LIKE ? OR m.full_name LIKE ? OR m.phone LIKE ?) ";

            parameters.add("%" +searchText+ "%");
            parameters.add("%" +searchText+ "%");
            parameters.add("%" +searchText+ "%");
        }

        if (category != null && !category.isBlank() && !category.equals("All Categories")){

            String categoryName = category.split("-")[1].trim();

            sql += " AND mc.category_name LIKE ? ";

            parameters.add(categoryName);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < parameters.size(); i++){
                preparedStatement.setObject(i+1, parameters.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setId(resultSet.getString("member_id"));
                memberDTO.setFullName(resultSet.getString("full_name"));
                memberDTO.setCategory(resultSet.getString("category_name"));
                memberDTO.setEmail(resultSet.getString("email"));
                memberDTO.setPhoneNumber(resultSet.getString("phone"));
                memberDTO.setAddress(resultSet.getString("address"));
                memberDTO.setStatus(resultSet.getString("status"));
                memberDTO.setCategoryId(resultSet.getInt("category_id"));

                memberList.add(memberDTO);
            }

            return memberList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateMember(String id, String fullname, String email, String phonenumber, String address) {

        String sql = "UPDATE members SET full_name = ?, email = ?, phone = ?, address = ? WHERE member_id = ? ";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phonenumber);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, id);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMember(String id) {

        String sql = "UPDATE members SET status = 'Inactive' WHERE member_id = ? ";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MemberCountDTO getMemberCount() {

        String sql = "SELECT COUNT(*) AS total_members, " +
                "SUM(mc.category_name = 'Student') AS student_members, " +
                "SUM(mc.category_name != 'Student') AS other_members " +
                "FROM members m " +
                "INNER JOIN membership_categories mc " +
                "ON m.category_id = mc.category_id " +
                "WHERE m.status = 'Active' ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int total = resultSet.getInt("total_members");
                int student = resultSet.getInt("student_members");
                int other = resultSet.getInt("other_members");

                MemberCountDTO memberCountDTO = new MemberCountDTO();
                memberCountDTO.setTotalMembers(total);
                memberCountDTO.setStudentMembers(student);
                memberCountDTO.setOtherMembers(other);

                return memberCountDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
