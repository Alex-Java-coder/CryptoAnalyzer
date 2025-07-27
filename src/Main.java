import Command.BruteForce;
import Command.Decrypt;
import Command.Encrypt;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static String key;
    public static String numRevers;

    public static void main(String[] args) throws IOException {

        System.out.println("Криптоанализатор.");

        // какой метод в лево или в право
        revers();

        // ввод ключа
        System.out.println("Введите ключ шифрования.");
        System.out.println("     от 0 до 144        ");
        key = scanner.nextLine();

        System.out.println("Выберите пункт меню.");

        commandsMenu();

    }

    private static void commandsMenu() throws IOException {

        while (true) {
            printMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1": {
                    Encrypt.encryptText(key, numRevers);
                    System.out.println("Шифрование завершено.");
                    break;
                }
                case "2": {
                    Decrypt.decryptText(key, numRevers);
                    System.out.println("Расшифровка завершена.");
                    break;
                }
                case "3": {
                    BruteForce.bruteForceDecrypt();
                    System.out.println("Перебор завершён.");
                    break;
                }
                case "4": {
                    System.out.println("Расшифровка статистическим анализом не сделана.");
                    break;
                }
                case "5": {
                    System.out.println("Выход.");
                    return;
                }
                default:
                    System.out.println("Выберите правильный пункт меню.");
            }

        }

    }

    private static void printMenu() {

        System.out.println("1 - Шифрование (Шифр Цезаря).");
        System.out.println("2 - Расшифровка (Шифр Цезаря).");
        System.out.println("3 - Расшифровка (Перебор паролей).");
        System.out.println("4 - Расшифровка (Статистический анализ).");
        System.out.println("5 - Выход.");

    }

    private static void revers() {

        while (true) {
            System.out.println("Выберите направление шифрования:");
            System.out.println("1 - влево");
            System.out.println("2 - вправо");
            String input = scanner.nextLine();

            if (input.equals("1") || input.equals("2")) {
                numRevers = input;
                break;
            } else {
                System.out.println("Неверный ввод. Введите 1 или 2.");
            }
        }

    }

}