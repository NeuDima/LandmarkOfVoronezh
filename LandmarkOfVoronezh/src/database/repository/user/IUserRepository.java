package database.repository.user;

import database.entity.UserEntity;

import java.util.List;

public interface IUserRepository {
    List<UserEntity> getRepository();

    UserEntity getUserByLogin(String login);

    UserEntity getUserById(int id);

    void addUser(UserEntity user);

    void updateUser(UserEntity userOld, UserEntity userNew);

    void deleteUser(UserEntity user);
}
