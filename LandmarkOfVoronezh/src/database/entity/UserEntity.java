package database.entity;

import java.util.Random;

public class UserEntity {
    private final int id;
    private final String login;
    private final String password;

    public UserEntity(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserEntity(String login, String password) {
        this.id = new Random().nextInt();
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
