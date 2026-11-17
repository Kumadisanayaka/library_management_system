package service.impl;

import Repository.ManageMemberRepository;
import Repository.impl.ManageMemberRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.MemberCountDTO;
import model.dto.MemberDTO;
import service.ManageMemberService;

public class ManageMemberServiceImpl implements ManageMemberService {

    ManageMemberRepository repository = new ManageMemberRepositoryImpl();

    public ObservableList<String> getMemberCategories() {

        return repository.getMemberCategories();

    }

    public ObservableList<MemberDTO> applyFilters(String searchText, String category) {

        return repository.applyFilters(searchText,category);

    }

    public boolean updateMember(String id, String fullname, String email, String phonenumber, String address) {

        return repository.updateMember(id,fullname,email,phonenumber,address);
    }

    public boolean deleteMember(String id) {

        return repository.deleteMember(id);
    }

    public MemberCountDTO getMemberCount() {

        return repository.getMemberCount();
    }
}
