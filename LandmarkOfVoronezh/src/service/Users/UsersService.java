package service.Users;

import database.entity.UserEntity;
import database.repository.user.UserRepository;

public class UsersService implements IUsersService {
    private final UserRepository usersRepository = UserRepository.getInstance();

    @Override
    public boolean checkUserLogin(String login, String password) {
        for (int i = 0; i < usersRepository.getRepository().size(); i++) {
            if (usersRepository.getRepository().get(i).getLogin().equals(login)) {
                if (usersRepository.getRepository().get(i).getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isNewUser(String login, String password) {
        for (int i = 0; i < usersRepository.getRepository().size(); i++) {
            if (usersRepository.getUserByLogin(login) != null) {
                return false;
            }
        }
        usersRepository.addUser(new UserEntity(login, password));
        return true;
    }

    @Override
    public int getIdByLogin(String login) {
        return usersRepository.getUserByLogin(login).getId();
    }
}
