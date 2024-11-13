package database.repository.landmark;

import database.entity.LandmarkEntity;

import java.util.List;

public interface ILandmarkRepository {
    List<LandmarkEntity> getRepository();

    LandmarkEntity getLandmarkById(int idLandmark);
}
