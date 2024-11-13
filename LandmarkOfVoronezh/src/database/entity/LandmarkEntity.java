package database.entity;

public class LandmarkEntity {
    private final int id;
    private final String name;
    private final String description;
    private final String history;

    public LandmarkEntity(int id, String name, String description, String history) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.history = history;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHistory() {
        return history;
    }
}
