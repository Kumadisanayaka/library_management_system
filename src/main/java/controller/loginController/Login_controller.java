package controller;

public class Login_controller {


    public boolean checkUsernameAndPassword(String userName, String password) {

        if (userName.equals("root") && password.equals("1234")){

            return true;

        }
        return false;

    }
}
