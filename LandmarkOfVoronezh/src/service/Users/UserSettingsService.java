package service.Users;

import database.entity.UserEntity;
import database.repository.user.UserRepository;

public class UserSettingsService implements IUserSettingsService {
    private final int id;
    private final UserRepository usersRepository = UserRepository.getInstance();

    public UserSettingsService(int id) {
        this.id = id;
    }

    @Override
    public boolean updateLoginUser(String loginNew) {
        if (checkUserLogin(loginNew)) {
            UserEntity user = usersRepository.getUserById(id);
            usersRepository.updateUser(user, new UserEntity(loginNew, user.getPassword()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updatePasswordUser(String passwordNew) {
        UserEntity user = usersRepository.getUserById(id);
        usersRepository.updateUser(user, new UserEntity(user.getLogin(), passwordNew));
    }

    @Override
    public void deleteUser() {
        usersRepository.deleteUser(usersRepository.getUserById(id));
    }

    @Override
    public boolean checkTrueUser(String password) {
        UserEntity user = usersRepository.getUserById(id);
        return user.getPassword().equals(password);
    }

    private boolean checkUserLogin(String login) {
        return usersRepository.getUserByLogin(login) == null;
    }
}
