package database.repository.landmark;

import database.connection.ConnectionManager;
import database.entity.LandmarkEntity;
import database.mapper.LandmarkMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LandmarkRepository implements ILandmarkRepository {
    private static LandmarkRepository instance = null;

    private LandmarkRepository() {
    }

    public static LandmarkRepository getInstance() {
        if (instance == null) {
            instance = new LandmarkRepository();
        }
        return instance;
    }

    private final List<LandmarkEntity> repository = fillingTableLandmark();

    private List<LandmarkEntity> fillingTableLandmark() {
        ArrayList<LandmarkEntity> list = new ArrayList<>();
        LandmarkMapper landmarkMapper = new LandmarkMapper();

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    String query = "SELECT * FROM landmark";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        LandmarkEntity landmarkEntity = landmarkMapper.mapRow(resultSet);
                        list.add(landmarkEntity);
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
    public List<LandmarkEntity> getRepository() {
        return new ArrayList<>(repository);
    }

    @Override
    public LandmarkEntity getLandmarkById(int idLandmark) {
        return repository.stream().filter(entry -> entry.getId() == idLandmark).findFirst().orElse(null);
    }
}