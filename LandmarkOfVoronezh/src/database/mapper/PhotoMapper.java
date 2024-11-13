package database.mapper;

import database.entity.PhotoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PhotoMapper  implements IRowMapper<PhotoEntity> {
    @Override
    public PhotoEntity mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String imageURL = rs.getString("image_url");
        Calendar date = setTime(rs.getDate("date_photo"));
        int idLandmark = rs.getInt("id_landmark");

        return new PhotoEntity(id, imageURL, date, idLandmark);
    }

    private Calendar setTime(java.sql.Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
