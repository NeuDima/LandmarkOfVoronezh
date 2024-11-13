package service.Users;

public interface IUsersService {
    boolean checkUserLogin(String login, String password);

    boolean isNewUser(String login, String password);

    int getIdByLogin(String login);
}
