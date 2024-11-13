package service.Landmark;

import database.entity.*;
import database.repository.history.HistoryRepository;
import database.repository.landmark.LandmarkRepository;
import database.repository.location.LocationRepository;
import database.repository.photo.PhotoRepository;

import java.util.*;

public class LandmarkService implements ILandmarkService {

    private final HistoryRepository historyRepository = HistoryRepository.getInstance();
    private final LandmarkRepository landmark = LandmarkRepository.getInstance();
    private final LocationRepository locationRepository = LocationRepository.getInstance();
    private final PhotoRepository photoRepository = PhotoRepository.getInstance();

    @Override
    public List<LandmarkEntity> getAllLandmark() {
        return landmark.getRepository();
    }

    @Override
    public int getLengthLandmarkTable() {
        return landmark.getRepository().size();
    }

    @Override
    public List<PhotoEntity> getListPhoto(int idLandmark) {
        return photoRepository.getPhotosByIdLandmark(idLandmark);
    }

    @Override
    public List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark) {
        return historyRepository.getHistoriesByIdLandmark(idLandmark);
    }

    @Override
    public LandmarkEntity getLandmarkById(int idLandmark) {
        return landmark.getLandmarkById(idLandmark);
    }

    @Override
    public LocationEntity getLocationById(int idLandmark) {
        return locationRepository.getLandmarkById(idLandmark);
    }
}
