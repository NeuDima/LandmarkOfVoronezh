package database.repository.location;

import database.entity.LocationEntity;

import java.util.List;

public interface ILocationRepository {
    List<LocationEntity> getRepository();

    LocationEntity getLandmarkById(int idLandmark);
}
