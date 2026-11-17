package service.impl;

import Repository.AddMemberRepository;
import Repository.impl.AddMemberRepositoryImpl;
import javafx.collections.ObservableList;
import model.dto.MemberDTO;
import service.AddMemberService;


public class AddMemberServiceImpl implements AddMemberService {

    AddMemberRepository repository = new AddMemberRepositoryImpl();

    public ObservableList<String> getMemberCategories(){
        return repository.getMemberCategories();
    }

    public String genarateMemberId(String memberCategory){

        return repository.genarateMemberId(memberCategory);
    }

    public Integer getCategoryId(String memberCategory){

        return repository.getCategoryId(memberCategory);
    }

    public boolean addMember(MemberDTO memberDTO,Integer categoryId) {

        return repository.addMember(memberDTO, categoryId);
    }
}
