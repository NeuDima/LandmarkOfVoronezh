package UI;

import UI.auxiliary.Tools;
import database.entity.HistoryEntity;
import service.Landmark.LandmarkService;

import java.util.ArrayList;
import java.util.List;

public class News {
    Tools tools = new Tools();

    public void show(int idLandmark, int idUser) {
        showNews(idLandmark);
        actions(idLandmark, idUser);
    }

    private void showNews(int idLandmark) {
        List<HistoryEntity> histories = new LandmarkService().getHistoriesByIdLandmark(idLandmark);

        int count = 0;
        for (HistoryEntity history : histories) {
            count++;
            System.out.println(
                    count + ". " + history.getNameClipping() + "\n" +
                            "   Источнок: " + history.getSource() + "\n"
            );

            ArrayList<String> description = tools.breakToLength(history.getDescription(), 200);
            for (String str : description) {
                System.out.println("   " + str);
            }

            if (history.getDate() != null) {
                System.out.println("   " + tools.calendarToStr(history.getDate()));
            }
            System.out.println();
        }
    }

    private void actions(int idLandmark, int idUser) {
        System.out.println("""
                
                Действия:
                1. Назад"""
        );

        int value = tools.checkNumberInRange(1, 1);
        if (value == 1) {
            new LandmarkMoreDetails().show(idLandmark, idUser);
        }
    }
}
