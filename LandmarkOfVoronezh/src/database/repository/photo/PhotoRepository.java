package database.repository.photo;

import database.connection.ConnectionManager;
import database.entity.PhotoEntity;
import database.mapper.PhotoMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhotoRepository implements IPhotoRepository {
    private static PhotoRepository instance = null;
    private PhotoRepository() {}
    public static PhotoRepository getInstance() {
        if (instance == null) {
            instance = new PhotoRepository();
        }
        return instance;
    }

    private final List<PhotoEntity> repository = fillingTablePhoto();

    private List<PhotoEntity> fillingTablePhoto() {
        ArrayList<PhotoEntity> list = new ArrayList<>();
        PhotoMapper photoMapper = new PhotoMapper();

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    String query = "SELECT * FROM photo";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        PhotoEntity photoEntity = photoMapper.mapRow(resultSet);
                        list.add(photoEntity);
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
    public List<PhotoEntity> getRepository() {
        return new ArrayList<>(repository);
    }

    @Override
    public List<PhotoEntity> getPhotosByIdLandmark(int idLandmark) {
        return repository.stream().filter(entry -> entry.getIdLandmark() == idLandmark).collect(Collectors.toList());
    }
}
