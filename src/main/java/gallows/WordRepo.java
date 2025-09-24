package gallows;

public class WordRepo {
    private final static String[] words = {"эскалатор", "хутор", "менеджмент", "закат", "библиотека"};

    public static String getRandomWord() {
        return words[(int) (Math.random() * (words.length))];
    }
}
