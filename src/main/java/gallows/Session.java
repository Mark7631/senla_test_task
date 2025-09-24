package gallows;

public class Session {
    private String word;
    private short errorsCounter;
    private String errorLetters;
    private String guessedLetters;
    private char lastLetter;
    private boolean isAlive = true;

    public Session(String word) {
        this.word = word;
        errorsCounter = 0;
        errorLetters = "";
        guessedLetters = "";
    }

    public void guess(String letter) { // обработка буквы пользователя
        lastLetter = letter.charAt(0);
        if (word.contains(letter)) {
            guessedLetters += letter;
            showState();
            if (!getPartEncryptedWord().contains("_")) {
                endSession();
            }
        } else {
            errorsCounter++;
            errorLetters += letter;
            showState();
            if (errorsCounter >= 6) {
                endSession();
            }
        }
    }

    public void showState() { // выводим информацию о игре
        String[] state = getGallowsImage(); // получаем картмнку виселицы взависимости от количества ошибок
        state[1] += "Слово: " + getPartEncryptedWord(); // дополняем виселицу информацией для пользователя
        state[2] += "Ошибки (" + errorsCounter + "): " + errorLetters;
        state[3] += "Буква: " + lastLetter;
        for (String s : state) {
            System.out.println(s);
        }
    }

    public String[] getGallowsImage() {
        String[] image = {
                " ____",
                "    |",
                "    |",
                "    |",
                "____|"
        };
        switch (errorsCounter) {
            case 0:
                break;
            case 1:
                image[1] = " o  |";
                break;
            case 2:
                image[1] = " o  |";
                image[2] = " |  |";
                break;
            case 3:
                image[1] = " o  |";
                image[2] = "/|  |";
                break;
            case 4:
                image[1] = " o  |";
                image[2] = "/|\\ |";
                break;
            case 5:
                image[1] = " o  |";
                image[2] = "/|\\ |";
                image[3] = "/   |";
                break;
            case 6:
                image[1] = " o  |";
                image[2] = "/|\\ |";
                image[3] = "/ \\ |";
                break;
            default:
                System.out.println("incorrect input in getGallowsImage in class Session");
        }
        return image;
    }

    public String getPartEncryptedWord() { // получаем слова с открытыми угаданными буквами
        StringBuilder stringBuilder = new StringBuilder();
        String[] letters = word.split("");
        for (String letter : letters) {
            if (guessedLetters.contains(letter)) {
                stringBuilder.append(letter);
            } else {
                stringBuilder.append("_");
            }
        }
        return stringBuilder.toString();
    }

    public void endSession() {
        boolean isLose = errorsCounter == 6;
        System.out.printf("Game over, you %s, word: " + word + "\n", (isLose) ? "lose" : "win");
        isAlive = false;
    }

    public boolean isRepeatedLetter(String letter) {
        return errorLetters.contains(letter) || guessedLetters.contains(letter);
    }

    public String getWord() {
        return word;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
