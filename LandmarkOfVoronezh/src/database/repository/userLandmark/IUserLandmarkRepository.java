package database.repository.userLandmark;

import database.entity.UserLandmarkEntity;

import java.util.List;

public interface IUserLandmarkRepository {
    List<UserLandmarkEntity> getRepository();

    List<UserLandmarkEntity> getUserLandmarkByIdUser(int idUser);

    void addUserLandmark(UserLandmarkEntity userLandmark);

    void deleteUserLandmark(UserLandmarkEntity userLandmark);
}
