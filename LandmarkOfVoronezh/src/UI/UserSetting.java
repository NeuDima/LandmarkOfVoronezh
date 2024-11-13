package UI;

import UI.auxiliary.Tools;
import service.Users.UserSettingsService;

import java.util.Scanner;

public class UserSetting {

    private final int idUser;
    Tools tools = new Tools();
    Scanner scanner = new Scanner(System.in);
    UserSettingsService userSettingsService;

    public UserSetting(int idUser) {
        this.idUser = idUser;
        this.userSettingsService = new UserSettingsService(idUser);
    }


    public void show() {
        while (true) {
            System.out.println();

            System.out.println("""
                Действия:
                1. Изменить логин
                2. Изменить пароль
                3. Удалить аккаунт
                4. Назад""");

            int value = tools.checkNumberInRange(1, 4);
            if (value == 1) {
                updateLogin();
            } else if (value == 2) {
                updatePassword();
            } else if (value == 3) {
                deleteUser();
            } else {
                break;
            }
        }
        new AllLandmarks().show(idUser);
    }

    private void updateLogin() {
        while (true) {
            System.out.print("Введите пароль: ");
            String password = scanner.next();
            if (userSettingsService.checkTrueUser(password)) {
                System.out.print("Введите новый логин: ");
                String loginNew = scanner.next();

                if (userSettingsService.updateLoginUser(loginNew)) {
                    System.out.println("Логин успешно изменён");
                    break;
                } else {
                    System.out.println("""
                                    Данный логин уже занят
                                    1. Повторить попытку
                                    2. Назад""");

                    int number = tools.checkNumberInRange(1, 2);

                    if (number == 2) {
                        break;
                    }
                }
            } else {
                System.out.println("""
                                    Неверный пароль!
                                    1. Повторить попытку
                                    2. Назад""");

                int number = tools.checkNumberInRange(1, 2);

                if (number == 2) {
                    break;
                }
            }
        }
    }

    private void updatePassword() {
        while (true) {
            System.out.print("Введите старый пароль: ");
            String password = scanner.next();
            if (userSettingsService.checkTrueUser(password)) {
                while (true) {
                    System.out.print("Введите новый пароль: ");
                    String passwordNew1 = scanner.next();
                    System.out.print("Повторите новый пароль: ");
                    String passwordNew2 = scanner.next();

                    if (passwordNew1.equals(passwordNew2)) {
                        userSettingsService.updatePasswordUser(passwordNew1);
                        break;
                    } else {
                        System.out.println("""
                                    Пароли должны совпадать
                                    1. Повторить попытку
                                    2. Назад""");

                        int number = tools.checkNumberInRange(1, 2);

                        if (number == 2) {
                            break;
                        }
                    }
                }
                break;
            } else {
                System.out.println("""
                            Неверный пароль!
                            1. Повторить попытку
                            2. Назад""");
                int number = tools.checkNumberInRange(1, 2);

                if (number == 2) {
                    break;
                }
            }
        }
    }

    private void deleteUser() {
        while (true) {
            System.out.print("Введите пароль: ");
            String password = scanner.next();
            if (userSettingsService.checkTrueUser(password)) {

                userSettingsService.deleteUser();
                new LogIn().show();
            } else {
                System.out.println("""
                            Неверный пароль!
                            1. Повторить попытку
                            2. Назад""");

                int number = tools.checkNumberInRange(1, 2);

                if (number == 2) {
                    break;
                }
            }
        }
    }
}
