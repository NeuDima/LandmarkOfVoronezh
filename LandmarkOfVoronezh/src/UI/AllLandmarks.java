package UI;

import UI.auxiliary.Tools;
import database.entity.LandmarkEntity;
import service.Favorites.FavoritesService;
import service.Landmark.LandmarkService;

import java.util.List;

public class AllLandmarks {
    Tools tools = new Tools();
    public void show(int idUser) {
        System.out.println();

        showFavorites(idUser);
        showAllLandmark();

        System.out.println("""
                Действия:
                1. Посмотреть подробнее достопримечательность но номеру
                2. Настройки профиля
                3. Выйти из профиля""");

        int value = tools.checkNumberInRange(1, 3);
        if (value == 1) {
            System.out.print("Введите номер: ");
            int idLandmark = tools.checkNumberLandmarkAndReturnId();
            new LandmarkMoreDetails().show(idLandmark, idUser);
        } else if (value == 2) {
            System.out.print("Введите номер: ");
            new UserSetting(idUser).show();
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            new LogIn().show();
        }
    }

    private void showFavorites(int idUser) {
        List<LandmarkEntity> favoritesList = new FavoritesService().getFavorites(idUser);
        if (!favoritesList.isEmpty()) {
            System.out.println("Избранные: ");
            for (LandmarkEntity landmark : favoritesList) {
                System.out.println(
                        landmark.getId() + ". " + landmark.getName() +
                        "\n   Описание: " + tools.trimToLength(landmark.getDescription(), 100) + '\n'
                );
            }
        }
    }

    private void showAllLandmark() {
        System.out.println("Достопримечательности Воронежа: ");
        List<LandmarkEntity> landmarks = new LandmarkService().getAllLandmark();
        for (LandmarkEntity landmark : landmarks) {
            System.out.println(
                    landmark.getId() + ". " + landmark.getName() +
                    "\n   Описание: " + tools.trimToLength(landmark.getDescription(), 100) + '\n'
            );
        }
    }
}
