package service;

import javafx.collections.ObservableList;
import model.dto.MemberDTO;

public interface AddMemberService {

    ObservableList<String> getMemberCategories();

    String genarateMemberId(String memberCategory);

    Integer getCategoryId(String memberCategory);

    boolean addMember(MemberDTO memberDTO, Integer categoryId);

}
