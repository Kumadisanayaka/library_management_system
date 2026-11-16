package controller.manageMemberController;

import model.entity.MembershipCategoryEntity;
import db.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.MemberCountDTO;
import model.dto.MemberDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class Manage_member_controller {

    public ObservableList<String> getMemberCategories() {

        ObservableList<String> memberCategoryList = FXCollections.observableArrayList();
        String hql = "FROM MembershipCategoryEntity";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()){

            List<MembershipCategoryEntity> categoryEntities =
                    session.createQuery(hql,MembershipCategoryEntity.class).getResultList();

            for (MembershipCategoryEntity category : categoryEntities){
                memberCategoryList.add(category.getShortCode() + " - " + category.getCategoryName());
            }

        } catch (Exception e) {
            System.err.println("Could not load Member Categories from DB : " + e.getMessage());
        }

        return memberCategoryList;

    }

    public ObservableList<MemberDTO> applyFilters(String searchText, String category) {

        ObservableList<MemberDTO> memberList = FXCollections.observableArrayList();

        String hql = "SELECT m.memberId, " +
                            "m.fullName, " +
                            "m.phone, " +
                            "mc.categoryName, " +
                            "m.email," +
                            "m.status, " +
                            "m.address, " +
                            "m.category.categoryId " +
                "FROM MemberEntity m " +
                "INNER JOIN m.category mc " +
                "WHERE 1=1 AND m.status = 'Active' ";


        if (searchText != null && !searchText.isBlank()){

            hql += " AND (m.memberId LIKE :search " +
                    "OR m.fullName LIKE :search " +
                    "OR m.phone LIKE :search) ";

        }

        if (category != null && !category.isBlank() && !category.equals("All Categories")){

            hql += " AND mc.categoryName LIKE :categoryName ";

        }

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            Query<Object[]> query = session.createQuery(hql,Object[].class);

            if (searchText != null && !searchText.isBlank()){

                query.setParameter("search","%" +searchText+ "%");
            }

            if (category != null && !category.isBlank() && !category.equals("All Categories")){

                String categoryName = category.split("-")[1].trim();
                query.setParameter("categoryName",categoryName);
            }

            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setId((String) result[0]);
                memberDTO.setFullName((String) result[1]);
                memberDTO.setPhoneNumber((String) result[2]);
                memberDTO.setCategory((String) result[3]);
                memberDTO.setEmail((String) result[4]);
                memberDTO.setStatus((String) result[5]);
                memberDTO.setAddress((String) result[6]);
                memberDTO.setCategoryId(((Number) result[7]).intValue());

                memberList.add(memberDTO);
            }

            return memberList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateMember(String id, String fullname, String email, String phonenumber, String address) {

        String hql = "UPDATE MemberEntity m " +
                "SET m.fullName = :fullName, " +
                "m.email = :email, " +
                "m.phone = :phone, " +
                "m.address = :address " +
                "WHERE m.memberId = :memberId ";


        try (Session session = HibernateUtil.getInstance().getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                int rows = session.createMutationQuery(hql)
                        .setParameter("fullName", fullname)
                        .setParameter("email", email)
                        .setParameter("phone", phonenumber)
                        .setParameter("address", address)
                        .setParameter("memberId", id)
                        .executeUpdate();

                transaction.commit();

                return rows > 0;

            } catch (Exception e) {

                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteMember(String id) {

        String hql = "UPDATE MemberEntity m " +
                            "SET m.status = 'Inactive' " +
                            "WHERE m.memberId = :memberId ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            Transaction transaction = session.beginTransaction();

            try {
                int rows = session.createMutationQuery(hql)
                        .setParameter("memberId",id)
                        .executeUpdate();

                transaction.commit();

                return rows > 0;
            } catch (Exception e) {

                if (transaction.isActive()){
                    transaction.rollback();
                }
                throw e;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MemberCountDTO getMemberCount() {

        String hql = "SELECT COUNT(m) , " +
                "SUM(CASE WHEN mc.categoryName = 'Student' THEN 1 ELSE 0 END), " +
                "SUM(CASE WHEN mc.categoryName != 'Student' THEN 1 ELSE 0 END) " +
                "FROM MemberEntity m " +
                "INNER JOIN m.category mc " +
                "WHERE m.status = 'Active' ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            Object[] result = session.createQuery(hql,Object[].class).getSingleResult();

            int totalMember = ((Number)result[0]).intValue();
            int studentMember = ((Number)result[1]).intValue();
            int otherStudent = ((Number)result[2]).intValue();

            MemberCountDTO countDTO = new MemberCountDTO();
            countDTO.setTotalMembers(totalMember);
            countDTO.setStudentMembers(studentMember);
            countDTO.setOtherMembers(otherStudent);

            return countDTO;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
