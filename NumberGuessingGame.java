import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private int targetNumber;
    private int maxAttempts;
    private int attemptsLeft;
    private int range;

    public NumberGuessingGame(int range, int maxAttempts) {
        this.range = range;
        Random random = new Random();
        this.targetNumber = random.nextInt(range) + 1;
        this.maxAttempts = maxAttempts;
        this.attemptsLeft = maxAttempts;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have " + maxAttempts + " attempts to guess the correct number between 1 and " + range + ".");

        while (attemptsLeft > 0) {
            System.out.println("\nAttempts left: " + attemptsLeft);
            int guess = getValidGuess(scanner);

            if (guess == targetNumber) {
                System.out.println("Congratulations! You guessed the right number: " + targetNumber);
                return;
            } else if (guess > targetNumber) {
                System.out.println("Too High! Try again.");
            } else {
                System.out.println("Too Low! Try again.");
            }
            attemptsLeft--;
        }

        System.out.println("\nYou've used all your attempts. The correct number was " + targetNumber);
        System.out.println("Better luck next time!");
    }

    private int getValidGuess(Scanner scanner) {
        int guess = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Enter your guess: ");
            try {
                guess = scanner.nextInt();

                if (guess < 1 || guess > range) {
                    System.out.println("Please enter a number between 1 and " + range + ".");
                } else {
                    isValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        return guess;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int range = 100; // Default range
        int maxAttempts = 10; // Default maximum attempts

        System.out.println("Welcome to the Number Guessing Game!");

        boolean validInput = false;
        while (!validInput) {
            System.out.println("Select the range:");
            System.out.println("1. 1 to 100");
            System.out.println("2. 1 to 1000");
            System.out.print("Choose an option: ");

            try {
                int rangeOption = scanner.nextInt();
                if (rangeOption == 1) {
                    range = 100;
                    validInput = true;
                } else if (rangeOption == 2) {
                    range = 1000;
                    validInput = true;
                } else {
                    System.out.println("Invalid option. Please select 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        NumberGuessingGame game = new NumberGuessingGame(range, maxAttempts);
        game.playGame();
        scanner.close();
    }
}
