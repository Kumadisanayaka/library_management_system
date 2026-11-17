package Repository;

public interface LoginRepository {

    boolean checkUsernameAndPassword(String userName, String password);
}
