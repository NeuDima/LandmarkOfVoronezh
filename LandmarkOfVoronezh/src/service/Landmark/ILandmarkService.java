package service.Landmark;

import database.entity.HistoryEntity;
import database.entity.LandmarkEntity;
import database.entity.LocationEntity;
import database.entity.PhotoEntity;

import java.util.List;

public interface ILandmarkService {
    List<LandmarkEntity> getAllLandmark();

    int getLengthLandmarkTable();

    List<PhotoEntity> getListPhoto(int idLandmark);

    List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark);

    LandmarkEntity getLandmarkById(int idLandmark);

    LocationEntity getLocationById(int idLandmark);
}
