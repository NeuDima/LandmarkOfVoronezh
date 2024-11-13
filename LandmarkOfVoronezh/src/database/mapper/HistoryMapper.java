package database.mapper;

import database.entity.HistoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HistoryMapper implements IRowMapper<HistoryEntity> {
    @Override
    public HistoryEntity mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nameClipping = rs.getString("name_clipping");
        String description = rs.getString("description");
        String source = rs.getString("source");
        Calendar date = setTime(rs.getDate("date_history"));
        int idLandmark = rs.getInt("id_landmark");

        return new HistoryEntity(id, nameClipping, description, source, date, idLandmark);
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