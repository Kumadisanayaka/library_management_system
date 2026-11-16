package service.impl;

import Repository.LoginRepository;
import Repository.impl.LoginRepositoryImpl;
import service.LoginService;

public class LoginServiceImpl implements LoginService {

    LoginRepository repository = new LoginRepositoryImpl();

    public boolean checkUsernameAndPassword(String userName, String password) {
        return repository.checkUsernameAndPassword(userName, password);
    }
}
