package gallows;

import java.util.Scanner;

public class Game {
    private static Session session;

    public static void startGame() {
        System.out.println("Игра виселица начнается, правила можно узнать тут https://ru.wikipedia.org/wiki/Виселица_(игра)");
        session = new Session(WordRepo.getRandomWord());
        System.out.println("Игра началась длинна слова: " + session.getWord().length());

        Scanner scanner = new Scanner(System.in);
        while (session.isAlive()) {
            System.out.println("ведите букву: ");
            String letter = scanner.nextLine();
            if (letter.length() > 1 || session.isRepeatedLetter(letter)) {
                while (letter.length() > 1 || session.isRepeatedLetter(letter)) {
                    System.out.println("Вы ввели >1 буквы или вы вводили уже эту букву, букву попробуйте ещё раз");
                    letter = scanner.nextLine();
                }
            }
            session.guess(letter);
        }
        System.out.println("Если хотите сыграть ещё раз да/нет");
        String wannaPlay = scanner.nextLine();
        if (wannaPlay.equalsIgnoreCase("да")) {
            startGame();
        }
    }

}
