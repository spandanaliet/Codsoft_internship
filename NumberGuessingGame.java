import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean keepPlaying = true;
        int totalScore = 0;
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I will pick a number between 1 and 100, and you have to guess it.");
        while (keepPlaying) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0; 
            int maxAttempts = 7;
            boolean guessedCorrectly = false;

            System.out.println("\nI have picked a number. Try to guess it! You have " + maxAttempts + " tries.");
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                if (userGuess == numberToGuess) {
                    System.out.println(" Congrats! You guessed it right!");
                    guessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1); 
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
                System.out.println("You have " + (maxAttempts - attempts) + " tries remaining.");
            }
            if (!guessedCorrectly) {
                System.out.println("Oh no! You've run out of tries. The correct number was: " + numberToGuess);
            }
            System.out.println("Your current score is: " + totalScore);
            System.out.print("Would you like to play another round? (yes/no): ");
            String playAgainResponse = scanner.next();
            if (!playAgainResponse.equalsIgnoreCase("yes")) {
                keepPlaying = false;
            }
        }

        System.out.println("\nThank you for playing! Your final score is: " + totalScore);
        scanner.close(); 
    }
}
