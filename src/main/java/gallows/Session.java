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

    public boolean isAlive() {
        return isAlive;
    }

    public String getWord() {
        return word;
    }
    public void guess(String letter) {
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

    public boolean isRepeatedLetter(String letter) {
        return errorLetters.contains(letter) || guessedLetters.contains(letter);
    }

    public void showState() {
        String[] state = getGallowsImage();
        state[1] += "Слово: " + getPartEncryptedWord();
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

    public String getPartEncryptedWord() {
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
}
