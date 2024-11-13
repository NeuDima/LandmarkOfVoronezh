package database.mapper;

import database.entity.LocationEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements IRowMapper<LocationEntity> {
    @Override
    public LocationEntity mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String streetName = rs.getString("street_name");
        String home = rs.getString("home");
        String coordinates = rs.getString("coordinates");

        return new LocationEntity(id, streetName, home, coordinates);
    }
}
