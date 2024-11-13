package database.repository.location;

import database.connection.ConnectionManager;
import database.entity.LocationEntity;
import database.mapper.LocationMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository implements ILocationRepository {
    private static LocationRepository instance = null;
    private LocationRepository() {}
    public static LocationRepository getInstance() {
        if (instance == null) {
            instance = new LocationRepository();
        }
        return instance;
    }

    private final List<LocationEntity> repository = fillingTableLocation();

    private List<LocationEntity> fillingTableLocation() {
        ArrayList<LocationEntity> list = new ArrayList<>();
        LocationMapper locationMapper = new LocationMapper();

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    String query = "SELECT * FROM location_landmark";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        LocationEntity locationEntity = locationMapper.mapRow(resultSet);
                        list.add(locationEntity);
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
    public List<LocationEntity> getRepository() {
        return new ArrayList<>(repository);
    }

    @Override
    public LocationEntity getLandmarkById(int idLandmark) {
        return repository.stream().filter(entry -> entry.getId() == idLandmark).findFirst().orElse(null);
    }
}
