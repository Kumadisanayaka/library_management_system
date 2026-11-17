package controller.addMemberController;

import model.entity.MemberEntity;
import model.entity.MembershipCategoryEntity;
import db.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.MemberDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Add_member_controller {

    public ObservableList<String> getMemberCategories(){
        ObservableList<String> memberCategoryList = FXCollections.observableArrayList();
        String hql = "FROM MembershipCategoryEntity ";

        try(Session session = HibernateUtil.getInstance().getFactory().openSession()){

            List<MembershipCategoryEntity> categoryEntities =
                    session.createQuery(hql,MembershipCategoryEntity.class).getResultList();

            for(MembershipCategoryEntity category : categoryEntities){

                memberCategoryList.add(category.getShortCode()+ " - " +category.getCategoryName());
            }

        } catch (Exception e) {
            System.err.println("Could not load Member Categories from DB : " + e.getMessage());
        }

        return memberCategoryList;

    }

    public String genarateMemberId(String memberCategory){

        String categoryShortCode = memberCategory.split("-")[0].trim();

        String hql = "FROM MemberEntity m " +
                "WHERE m.memberId LIKE :pattern " +
                "ORDER BY m.memberId DESC";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            MemberEntity lastMember = session.createQuery(hql,MemberEntity.class)
                    .setParameter("pattern",categoryShortCode + "%")
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastMember != null){

                String lastMemberId = lastMember.getMemberId();

                if (lastMemberId.length() > categoryShortCode.length()){

                    String lastMemberIdStr = lastMemberId.substring(categoryShortCode.length());

                    int nextNumber = Integer.parseInt(lastMemberIdStr)+1;

                    return categoryShortCode + String.format("%03d",nextNumber);

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return categoryShortCode + "001";
    }

    public Integer getCategoryId(String memberCategory){
        String shortCode = memberCategory.split("-")[0].trim();

        String hql = "FROM MembershipCategoryEntity mc " +
                " WHERE mc.shortCode LIKE :shortCode ";

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            MembershipCategoryEntity category =
                    session.createQuery(hql,MembershipCategoryEntity.class)
                            .setParameter("shortCode",shortCode)
                            .uniqueResult();

            if (category != null){

                return category.getCategoryId();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean addMember(MemberDTO memberDTO,Integer categoryId) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getInstance().getFactory().openSession()){

            transaction = session.beginTransaction();

            MembershipCategoryEntity category =
                    session.find(MembershipCategoryEntity.class,categoryId);

            if (category == null){
                transaction.rollback();
                return false;
            }

            MemberEntity member = new MemberEntity();
            member.setMemberId(memberDTO.getId());
            member.setFullName(memberDTO.getFullName());
            member.setAddress(memberDTO.getAddress());
            member.setCategory(category);
            member.setPhone(memberDTO.getPhoneNumber());
            member.setEmail(memberDTO.getEmail());
            member.setStatus(member.getStatus());
            session.persist(member);
            transaction.commit();

            return true;
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
