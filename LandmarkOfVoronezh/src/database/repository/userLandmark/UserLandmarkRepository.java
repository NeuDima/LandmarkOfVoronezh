package database.repository.userLandmark;

import database.connection.ConnectionManager;
import database.entity.UserLandmarkEntity;
import database.mapper.UserLandmarkMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserLandmarkRepository implements IUserLandmarkRepository {
    private static UserLandmarkRepository instance = null;
    private UserLandmarkRepository() {}
    public static UserLandmarkRepository getInstance() {
        if (instance == null) {
            instance = new UserLandmarkRepository();
        }
        return instance;
    }

    private List<UserLandmarkEntity> repository = readTableUserLandmark();

    private List<UserLandmarkEntity> readTableUserLandmark() {
        ArrayList<UserLandmarkEntity> list = new ArrayList<>();
        UserLandmarkMapper userLandmarkMapper = new UserLandmarkMapper();

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    String query = "SELECT * FROM user_landmark";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        UserLandmarkEntity userLandmarkEntity = userLandmarkMapper.mapRow(resultSet);
                        list.add(userLandmarkEntity);
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
    public void addUserLandmark(UserLandmarkEntity userLandmark) {
        String sql = "INSERT INTO public.user_landmark (user_id, landmark_id) values (?, ?)";
        changeUserLandmark(sql, userLandmark);
    }

    @Override
    public void deleteUserLandmark(UserLandmarkEntity userLandmark) {
        String sql = "DELETE FROM public.user_landmark WHERE user_id = ? AND landmark_id = ?";
        changeUserLandmark(sql, userLandmark);
    }

    private void changeUserLandmark(String sql, UserLandmarkEntity userLandmark) {
        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userLandmark.getIdUser());
                    preparedStatement.setInt(2, userLandmark.getIdLandmark());
                    preparedStatement.executeUpdate();

                    repository = readTableUserLandmark();
                }
            } else {
                System.out.println("Failed to connect to PostgreSQL database.");
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserLandmarkEntity> getRepository() {
        return new ArrayList<>(repository);
    }

    @Override
    public List<UserLandmarkEntity> getUserLandmarkByIdUser(int idUser) {
        return repository.stream().filter(entry -> entry.getIdUser() == idUser).collect(Collectors.toList());
    }
}
