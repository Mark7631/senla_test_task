package passwordGenerator;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class PasswordGeneratorApplication {
    private static final String ALPHABET = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
    private static final int ALPHABET_LEN = ALPHABET.length();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Эта программа генерирует надёжные пароли");
        start();
    }

    private static void start() {
        try {
            System.out.println("Напишите необходимую длинну пароля от 8 до 12(включительно)");
            StringBuilder password = new StringBuilder();
            int passwordLen = Integer.parseInt(scanner.nextLine());
            if (!(8 <= passwordLen && passwordLen <= 12)) throw new IOException("невверный ввод");
            for (int i = 0; i < passwordLen; i++) {
                password.append(getRandomLetter());
            }
            System.out.printf("ваш пароль: %s\n", password);
        } catch (Exception e) {
            System.out.println("неправельный ввод");
            start();
        }
    }

    private static char getRandomLetter() {
        return ALPHABET.charAt(new Random().nextInt(ALPHABET_LEN));
    }
}
