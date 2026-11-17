package Repository;

import javafx.collections.ObservableList;
import model.dto.MemberCountDTO;
import model.dto.MemberDTO;

public interface ManageMemberRepository {

    ObservableList<String> getMemberCategories();

    ObservableList<MemberDTO> applyFilters(String searchText, String category);

    boolean updateMember(String id, String fullname, String email, String phonenumber, String address);

    boolean deleteMember(String id);

    MemberCountDTO getMemberCount();
}
