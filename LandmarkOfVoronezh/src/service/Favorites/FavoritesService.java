package service.Favorites;

import database.entity.LandmarkEntity;
import database.entity.UserLandmarkEntity;
import database.repository.landmark.LandmarkRepository;
import database.repository.userLandmark.UserLandmarkRepository;

import java.util.ArrayList;
import java.util.List;

public class FavoritesService implements IFavoritesService {
    private final LandmarkRepository landmarkRepository = LandmarkRepository.getInstance();
    private final UserLandmarkRepository userLandmarkRepository = UserLandmarkRepository.getInstance();

    @Override
    public List<LandmarkEntity> getFavorites(int idUser) {
        List<LandmarkEntity> favoritesList = new ArrayList<>();
        for (UserLandmarkEntity userLandmark : userLandmarkRepository.getUserLandmarkByIdUser(idUser)) {
            if (userLandmark.getIdUser() == idUser) {
                favoritesList.add(landmarkRepository.getLandmarkById(userLandmark.getIdLandmark()));
            }
        }
        return favoritesList;
    }

    @Override
    public boolean cheekIsFavorites(LandmarkEntity landmark, int idUser) {
        int idLandmark = landmark.getId();
        for (UserLandmarkEntity userLandmark : userLandmarkRepository.getRepository()) {
            if (userLandmark.getIdLandmark() == idLandmark && userLandmark.getIdUser() == idUser) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFavorites(LandmarkEntity landmark, int idUser) {
        userLandmarkRepository.addUserLandmark(new UserLandmarkEntity(
                idUser,
                landmark.getId())
        );
    }

    @Override
    public void removeFavorites(LandmarkEntity landmark, int idUser) {
        userLandmarkRepository.deleteUserLandmark(new UserLandmarkEntity(idUser, landmark.getId()));
    }
}
