package database.mapper;

import database.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper  implements IRowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String login = rs.getString("login");
        String password = rs.getString("password");

        return new UserEntity(id, login, password);
    }
}
