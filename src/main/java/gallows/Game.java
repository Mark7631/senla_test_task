package gallows;

import java.util.Scanner;

public class Game {
    private static Session session;
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void initGame() {
        System.out.println("Игра виселица начнается, правила можно узнать тут https://ru.wikipedia.org/wiki/Виселица_(игра)");
        session = new Session(WordRepo.getRandomWord());
        System.out.println("Игра началась длинна слова: " + session.getWord().length());

        Scanner scanner = new Scanner(System.in);
        while (session.isAlive()) { // проверка что игра не закончена
            System.out.println("ведите букву: ");
            String letter = scanner.nextLine();
            if (letter.length() > 1 || session.isRepeatedLetter(letter) || !ALPHABET.contains(letter)) { // проверка введёного значения на то, то это буква, что не вводилось ранее и то, что это буква русского алфавита
                while (letter.length() > 1 || session.isRepeatedLetter(letter) || !ALPHABET.contains(letter)) { // добиваемся от пользователя корректного значения
                    System.out.println("Вы ввели >1 буквы или вы вводили уже эту букву, букву попробуйте ещё раз");
                    letter = scanner.nextLine();
                }
            }
            session.guess(letter);
        }
        System.out.println("Если хотите сыграть ещё раз да/нет");
        String wannaPlay = scanner.nextLine();
        if (wannaPlay.equalsIgnoreCase("да")) {
            initGame();
        }
    }

}
