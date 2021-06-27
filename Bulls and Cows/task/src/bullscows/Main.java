package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static int MAX_INPUT_LENGTH           = 36;
    private final static int NUMERIC_SYMBOLS            = 10;
    private final static int NON_NUMERIC_SYMBOLS        = 26;
    private final static int FIRST_ASCII_LETTER_CODE    = 97;


    private static String secretCode;
    private static String answer = "";
    private static String grade = "";

    private static int      turn = 0;
    private static int      bulls = 0;
    private static int      cows = 0;
    private static int      length = 0;
    private static int      possibleCharacters = 0;

    public static void main(String[] args) {


        printInputLengthRequest();
        try {
            setLength(Integer.parseInt(getInputLength()));

            if (getLength() > MAX_INPUT_LENGTH || getLength() == 0) {
                System.out.println("Error: can't generate a secret number with a length of 36 because there aren't enough unique digits.");
            } else {

                printPossibleCharactersRequest();
                setPossibleCharacters(Integer.parseInt(getInputLength()));

                if (getPossibleCharacters() >= getLength() && getPossibleCharacters() > 0 && getPossibleCharacters() <= MAX_INPUT_LENGTH) {

                    boolean error = false;
                    printRange();

                    System.out.println("Okay, let's start a game!");
                    generateSecretCode();

                    while (!getSecretCode().equals(getAnswer()) && !error) {
                        addTurn();
                        setAnswer(getInput());
                        if (isValidAnswer()) {
                            grade();
                            printTurn();
                        } else {
                            error = true;
                            System.out.println("error");
                        }
                    }
                } else {
                    System.out.println("error");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }

    private static boolean isValidAnswer() {

        boolean isValid = getAnswer().length() == getLength();

        for (int i = 0; i < getAnswer().length() ; i++) {
            if (getPossibleCharacters() > NUMERIC_SYMBOLS) {
                isValid = isValid && (getAnswer().charAt(i) >= '0' && getAnswer().charAt(i) < (char)(NUMERIC_SYMBOLS + 48)
                                || getAnswer().charAt(i) >= 'a' && getAnswer().charAt(i) < (char) (getPossibleCharacters() - NUMERIC_SYMBOLS + FIRST_ASCII_LETTER_CODE));
            } else {
                isValid = isValid && (getAnswer().charAt(i) >= '0' && getAnswer().charAt(i) < (char) (getPossibleCharacters() + 48));
            }
        }

        return isValid;
    }

    private static void grade() {
        countBulls();
        countCows();

        StringBuilder sb = new StringBuilder();

        if (bulls != 0) {
            sb.append(bulls);
            sb.append(" bull(s)");
        }

        if (cows != 0) {

            if (! sb.toString().isEmpty()) {
                sb.append(" and ");
            }

            sb.append(cows);
            sb.append(" cow(s)");
        }

        setGrade(sb.toString().isEmpty() ? "None" : sb.toString());
    }


    private static void countBulls() {
        bulls = 0;

        for (int i = 0; i < getSecretCode().length(); i++) {
            if (getAnswer().charAt(i) == getSecretCode().charAt(i)) {
                bulls++;
            }
        }
    }

    private static void countCows() {
        cows = 0;

        for (int i = 0; i < getAnswer().length(); i++) {//TODO: check repeated numbers
            if (getSecretCode().contains(String.valueOf(getAnswer().charAt(i)))) {
                cows++;
            }
        }

        cows -= bulls;
    }

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static String getInputLength() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    private static void printInputLengthRequest() {
        System.out.println("Input the length of the secret code:");
    }

    private static void printPossibleCharactersRequest() {
        System.out.println("Input the number of possible symbols in the code:");
    }

    private static void printRange() {

        String range1 = String.valueOf(getPossibleCharacters() - 1);
        String range2 = "";
        if (getPossibleCharacters() > NUMERIC_SYMBOLS) {
            range1 = String.valueOf(NUMERIC_SYMBOLS - 1);
            range2 = String.valueOf((char) (getPossibleCharacters() - NUMERIC_SYMBOLS + FIRST_ASCII_LETTER_CODE - 1));
        }


        String asterisks = "*".repeat(getLength());
/*
        StringBuilder asterisks = new StringBuilder();
        for (int i = 0; i < getLength(); i++) {
            asterisks.append("*");
        }*/
        if (range2.isEmpty()) {
            System.out.printf("The secret is prepared: %s (0-%s).%n", asterisks, range1);
        } else {
            System.out.printf("The secret is prepared: %s (0-%s, a-%s).%n", asterisks, range1, range2);
        }
    }

    private static void printTurn() {
        System.out.printf("Turn %d. Answer:%n", getTurn());
        System.out.println(getAnswer());
        System.out.printf("Grade: %s%n%n", getGrade());
    }

    private static void generateSecretCode() {
        StringBuilder result = new StringBuilder();

        while(result.length() < Main.getLength()) {
            char character = getRandomValue();

            if (!result.toString().contains(String.valueOf(character))) {
                result.append(character);
            }
        }

        setSecretCode(result.toString());
    }

    private static char getRandomValue() {
        Random random = new Random();
        char result;

        if (getPossibleCharacters() <= NUMERIC_SYMBOLS) {
            result = (char) (random.nextInt(getPossibleCharacters()) + 48);
        } else if (random.nextInt(2) == 1) {
            result = (char) (random.nextInt(NUMERIC_SYMBOLS) + 48);
        } else {
            result = (char) (random.nextInt(getPossibleCharacters() - NUMERIC_SYMBOLS) + FIRST_ASCII_LETTER_CODE);
        }

        return result;
    }

    public static String getSecretCode() {
        return secretCode;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setSecretCode(String secretCode) {
        Main.secretCode = secretCode;
    }

    public static void setAnswer(String answer) {
        Main.answer = answer;
    }

    public static String getGrade() {
        return grade;
    }

    private static void setGrade(String grade) {
        Main.grade = grade;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        Main.turn = turn;
    }

    public static void addTurn() {
        Main.turn++;
    }

    public static void setLength(int length) {
        Main.length = length;
    }

    public static int getLength() {
        return Main.length;
    }

    public static int getPossibleCharacters() {
        return possibleCharacters;
    }

    public static void setPossibleCharacters(int possibleCharacters) {
        Main.possibleCharacters = possibleCharacters;
    }
}
