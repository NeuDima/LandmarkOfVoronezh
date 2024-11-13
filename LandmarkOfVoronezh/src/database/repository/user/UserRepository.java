package database.repository.user;

import database.connection.ConnectionManager;
import database.entity.UserEntity;
import database.mapper.UserMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static UserRepository instance = null;
    private UserRepository() {}
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private ArrayList<UserEntity> repository = readTableUser();

    private ArrayList<UserEntity> readTableUser() {
        ArrayList<UserEntity> list = new ArrayList<>();
        UserMapper userMapper = new UserMapper();

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    String query = "SELECT * FROM user_date";
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        UserEntity userEntity = userMapper.mapRow(resultSet);
                        list.add(userEntity);
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
    public void addUser(UserEntity user) {
        String sql = "INSERT INTO public.user_date (login, password) values (?, ?)";

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.executeUpdate();

                    repository = readTableUser();
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
    public void updateUser(UserEntity userOld, UserEntity userNew) {
        String sql = "UPDATE public.user_date SET login = ?, password = ? WHERE id = ?";

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, userNew.getLogin());
                    preparedStatement.setString(2, userNew.getPassword());
                    preparedStatement.setInt(3, userOld.getId());
                    preparedStatement.executeUpdate();

                    repository = readTableUser();
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
    public void deleteUser(UserEntity user) {
        String sql = "DELETE FROM public.user_date WHERE id = ?";

        try (Connection connection = ConnectionManager.open()) {
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.executeUpdate();

                    repository = readTableUser();
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
    public List<UserEntity> getRepository() {
        return new ArrayList<>(repository);
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        return repository.stream().filter(entry -> entry.getLogin().equals(login)).findFirst().orElse(null);
    }

    @Override
    public UserEntity getUserById(int id) {
        return repository.stream().filter(entry -> entry.getId() == id).findFirst().orElse(null);
    }

//    @Override
//    public void addUser(UserEntity user) {
//        repository.add(user);
//    }
}
