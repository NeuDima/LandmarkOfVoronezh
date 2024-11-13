package database.mapper;

import database.entity.UserLandmarkEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLandmarkMapper  implements IRowMapper<UserLandmarkEntity> {
    @Override
    public UserLandmarkEntity mapRow(ResultSet rs) throws SQLException {
        int idUser = rs.getInt("user_id");
        int idLandmark = rs.getInt("landmark_id");

        return new UserLandmarkEntity(idUser, idLandmark);
    }
}
