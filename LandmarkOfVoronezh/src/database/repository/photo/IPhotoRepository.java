package database.repository.photo;

import database.entity.PhotoEntity;

import java.util.List;

public interface IPhotoRepository {
    List<PhotoEntity> getRepository();

    List<PhotoEntity> getPhotosByIdLandmark(int idLandmark);
}
