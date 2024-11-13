package database.entity;

import java.util.Calendar;

public class PhotoEntity {
    private final int id;
    private final String imageURL;
    private final Calendar date;
    private final int idLandmark;

    public PhotoEntity(int id, String imageURL, Calendar date, int idLandmark) {
        this.id = id;
        this.imageURL = imageURL;
        this.date = date;
        this.idLandmark = idLandmark;
    }

    public PhotoEntity(int id, String imageURL, int idLandmark) {
        this.id = id;
        this.imageURL = imageURL;
        this.date = null;
        this.idLandmark = idLandmark;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Calendar getDate() {
        return date;
    }

    public int getIdLandmark() {
        return idLandmark;
    }
}
