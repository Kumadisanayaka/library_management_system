package Repository;

import javafx.collections.ObservableList;
import model.dto.MemberDTO;

public interface AddMemberRepository {

    ObservableList<String> getMemberCategories();

    String genarateMemberId(String memberCategory);

    Integer getCategoryId(String memberCategory);

    boolean addMember(MemberDTO memberDTO, Integer categoryId);
}
