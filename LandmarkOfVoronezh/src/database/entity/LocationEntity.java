package database.entity;

public class LocationEntity {
    private final int id;
    private final String streetName;
    private final String home;
    private String coordinates = "";

    public LocationEntity(int id, String streetName, String home, String coordinates) {
        this.id = id;
        this.streetName = streetName;
        this.home = home;
        this.coordinates = coordinates;
    }

    public LocationEntity(int id, String streetName, String home) {
        this.id = id;
        this.streetName = streetName;
        this.home = home;
    }

    public int getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String  getHome() {
        return home;
    }

    public String  getCoordinates() {
        return coordinates;
    }
}
