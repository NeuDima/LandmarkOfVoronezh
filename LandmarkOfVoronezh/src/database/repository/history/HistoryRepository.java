package database.repository.history;

import database.connection.ConnectionManager;
import database.entity.HistoryEntity;
import database.mapper.HistoryMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class HistoryRepository implements IHistoryRepository {
    private static HistoryRepository instance = null;
    private HistoryRepository() {}
    public static HistoryRepository getInstance() {
        if (instance == null) {
            instance = new HistoryRepository();
        }
        return instance;
    }

    private final List<HistoryEntity> repository = fillingTableHistory();

    private List<HistoryEntity> fillingTableHistory() {
        ArrayList<HistoryEntity> list = new ArrayList<>();
        HistoryMapper historyMapper = new HistoryMapper();

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    String query = "SELECT * FROM historical_clipping";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        HistoryEntity historyEntity = historyMapper.mapRow(resultSet);
                        list.add(historyEntity);
                    }
                }
            } else {
                System.out.println("Failed to connect to PostgreSQL database.");
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<HistoryEntity> getRepository() {
        return new ArrayList<>(repository);
    }

    @Override
    public List<HistoryEntity> getHistoriesByIdLandmark(int idLandmark) {
        return repository.stream().filter(entry -> entry.getIdLandmark() == idLandmark).collect(Collectors.toList());
    }
}


