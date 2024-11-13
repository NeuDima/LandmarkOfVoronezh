package UI;

import UI.auxiliary.Tools;
import service.Users.UsersService;

import java.util.Scanner;

public class LogIn {
    Tools tools = new Tools();
    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                """
                Достопримечательности Воронежа
                1. Вход
                2. Регистрация"""
        );

        int value = tools.checkNumberInRange(1, 2);

        UsersService usersService = new UsersService();
        if (value == 1) {
            while (true) {
                System.out.print("\nЛогин: ");
                String login = scanner.next();
                System.out.print("Пароль: ");
                String password = scanner.next();

                if (usersService.checkUserLogin(login, password)) {
                    new AllLandmarks().show(usersService.getIdByLogin(login));
                    break;
                } else {
                    System.out.println("""
                            Неверный логин или пароль
                            1. Продолжить
                            2. Назад"""
                    );

                    int number = tools.checkNumberInRange(1, 2);
                    if (number == 2) {
                        show();
                    }
                }
            }
        } else {
            while (true) {
                System.out.print("\nПридумайте логин: ");
                String login = scanner.next();
                System.out.print("Придумайте пароль: ");
                String password = scanner.next();

                if (usersService.isNewUser(login, password)) {
                    new AllLandmarks().show(usersService.getIdByLogin(login));
                    break;
                } else {
                    System.out.println("""
                            Данный логин уже занят
                            1. Продолжить
                            2. Назад"""
                    );

                    int number = tools.checkNumberInRange(1, 2);
                    if (number == 2) {
                        show();
                    }
                    System.out.println("Данный логин уже занят");
                }
            }
        }
    }
}
