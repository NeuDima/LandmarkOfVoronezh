package UI;

import UI.auxiliary.Tools;
import database.entity.LandmarkEntity;
import database.entity.LocationEntity;
import database.entity.PhotoEntity;
import service.Favorites.FavoritesService;
import service.Landmark.LandmarkService;

import java.util.ArrayList;
import java.util.List;

public class LandmarkMoreDetails {
    Tools tools = new Tools();
    public void show(int idLandmark, int idUser) {
        LandmarkEntity landmark = new LandmarkService().getLandmarkById(idLandmark);
        LocationEntity location = new LandmarkService().getLocationById(idLandmark);

        showLandmark(landmark, location);
        actions(landmark, idUser);
    }

    private void showLandmark(LandmarkEntity landmark, LocationEntity location) {
        System.out.println('\n' +
                landmark.getName() + "\n\n" +
                "Описание:"
        );

        ArrayList<String> listDescription = tools.breakToLength(landmark.getDescription(), 200);
        for (String str : listDescription) {
            System.out.println(str);
        }

        System.out.println("\nИстория:");
        ArrayList<String> listHistory = tools.breakToLength(landmark.getHistory(), 200);
        for (String str : listHistory) {
            System.out.println(str);
        }

        if (location != null) {
            System.out.println(
                    "\nМестоположение: " + "\n" +
                            location.getStreetName() + ", " + location.getHome());

            if (!location.getCoordinates().equals("")) {
                System.out.println(location.getCoordinates());
            }
        }

        List<PhotoEntity> listPhoto = new LandmarkService().getListPhoto(landmark.getId());
        if (listPhoto.size() != 0) {
            System.out.println("\nФото: ");
            for (PhotoEntity photo : listPhoto) {
                if (photo.getDate() != null) {
                    System.out.print(tools.calendarToStr(photo.getDate()) + " ");
                }
                System.out.println("URL: " + photo.getImageURL());
            }
        }
    }

    private void actions(LandmarkEntity landmark, int idUser) {
        FavoritesService favoritesService = new FavoritesService();

        String isFavoriteText;
        boolean favoriteOption = favoritesService.cheekIsFavorites(landmark, idUser);
        if (favoriteOption) {
            isFavoriteText = "Удалить из избанного";
        } else {
            isFavoriteText = "Добавить в избранное";
        }

        System.out.println(
                "\n" + "Действия:" + "\n" +
                "1. Посмотреть вырезки из новостей" + "\n" +
                "2. " + isFavoriteText + "\n" +
                "3. Назад"
        );

        int value = tools.checkNumberInRange(1, 3);

        if (value == 1) {
            System.out.println(landmark.getName() + " в новостях" + "\n");
            new News().show(landmark.getId(), idUser);
        } else if (value == 2) {
            if (favoriteOption) {
                favoritesService.removeFavorites(landmark, idUser);
            } else {
                favoritesService.addFavorites(landmark, idUser);
            }
            show(landmark.getId(), idUser);
        } else {
            AllLandmarks landmarks = new AllLandmarks();
            landmarks.show(idUser);
        }
    }
}
