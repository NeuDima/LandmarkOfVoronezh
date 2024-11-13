package service.Users;

public interface IUserSettingsService {

    boolean updateLoginUser(String loginNew);

    void updatePasswordUser(String passwordNew);

    void deleteUser();

    boolean checkTrueUser(String password);
}
