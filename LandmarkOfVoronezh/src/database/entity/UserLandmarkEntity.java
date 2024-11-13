package database.entity;

public class UserLandmarkEntity {
    private final int idUser;
    private final int idLandmark;

    public UserLandmarkEntity(int idUser, int idLandmark) {
        this.idUser = idUser;
        this.idLandmark = idLandmark;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdLandmark() {
        return idLandmark;
    }
}
