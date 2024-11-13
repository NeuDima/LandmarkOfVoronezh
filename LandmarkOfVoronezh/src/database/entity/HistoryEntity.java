package database.entity;

import java.util.Calendar;

public class HistoryEntity {
    private final int id;
    private final String nameClipping;
    private final String description;
    private final String source;
    private final Calendar date;
    private final int idLandmark;

    public HistoryEntity(int id, String name_clipping, String description, String source, Calendar date, int idLandmark) {
        this.id = id;
        this.nameClipping = name_clipping;
        this.description = description;
        this.source = source;
        this.date = date;
        this.idLandmark = idLandmark;
    }

    public HistoryEntity(int id, String name_clipping, String description, String source, int idLandmark) {
        this.id = id;
        this.nameClipping = name_clipping;
        this.description = description;
        this.source = source;
        this.date = null;
        this.idLandmark = idLandmark;
    }

    public int getId() {
        return id;
    }

    public String getNameClipping() {
        return nameClipping;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public Calendar getDate() {
        return date;
    }

    public int getIdLandmark() {
        return idLandmark;
    }
}
