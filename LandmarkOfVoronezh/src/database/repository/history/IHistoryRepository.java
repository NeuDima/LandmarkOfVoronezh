package database.repository.history;

import database.entity.HistoryEntity;

import java.util.List;

public interface IHistoryRepository {
    List<HistoryEntity> getRepository();

    List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark);
}
