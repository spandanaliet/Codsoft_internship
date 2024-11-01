import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleQuizApp {

    // Quiz Questions, Options, and Answers
    private static String[] questions = {
        "1. What is the capital of France?",
        "2. Which planet is known as the Red Planet?",
        "3. Who wrote 'Romeo and Juliet'?",
        "4. What is the largest ocean on Earth?",
        "5. Which element has the chemical symbol 'O'?"
    };

    private static String[][] options = {
        {"A. Paris", "B. London", "C. Rome", "D. Berlin"},
        {"A. Venus", "B. Mars", "C. Jupiter", "D. Saturn"},
        {"A. Charles Dickens", "B. William Shakespeare", "C. Mark Twain", "D. Leo Tolstoy"},
        {"A. Atlantic", "B. Indian", "C. Pacific", "D. Arctic"},
        {"A. Oxygen", "B. Gold", "C. Silver", "D. Iron"}
    };

    private static char[] correctAnswers = {'A', 'B', 'B', 'C', 'A'};
    private static int score = 0;  // Track score
    private static boolean answerSubmitted = false;  // Check if answer was submitted in time
    private static final int TIME_LIMIT = 10000;  // Time limit in milliseconds

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz! You have 10 seconds to answer each question.\n");

        // Loop through all questions
        for (int i = 0; i < questions.length; i++) {
            presentQuestion(scanner, i);
        }

        // Final Result
        System.out.println("\nQuiz Over!");
        System.out.println("Your Final Score: " + score + "/" + questions.length);
    }

    // Method to present each question
    public static void presentQuestion(Scanner scanner, int questionIndex) {
        System.out.println(questions[questionIndex]);
        for (String option : options[questionIndex]) {
            System.out.println(option);
        }

        answerSubmitted = false;  // Reset for each question
        Timer timer = new Timer();  // Timer for question

        // Timer task to handle time-out
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!answerSubmitted) {
                    System.out.println("\nTime's up for this question!\n");
                }
                timer.cancel();
                synchronized (SimpleQuizApp.class) {
                    SimpleQuizApp.class.notify();
                }
            }
        };

        // Schedule the timer task to run after the TIME_LIMIT
        timer.schedule(task, TIME_LIMIT);

        System.out.print("Your answer (A/B/C/D): ");
        long startTime = System.currentTimeMillis();

        synchronized (SimpleQuizApp.class) {
            while (!answerSubmitted && System.currentTimeMillis() - startTime < TIME_LIMIT) {
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine().toUpperCase();
                    if (input.length() == 1 && "ABCD".contains(input)) {
                        char userAnswer = input.charAt(0);
                        checkAnswer(userAnswer, questionIndex);
                        answerSubmitted = true;
                    }
                }
            }
        }
    }

    // Check if the user's answer is correct
    public static void checkAnswer(char userAnswer, int questionIndex) {
        if (userAnswer == correctAnswers[questionIndex]) {
            System.out.println("Correct!\n");
            score++;  // Increment score for correct answers
        } else {
            System.out.println("Incorrect. The correct answer was: " + correctAnswers[questionIndex] + "\n");
        }
    }
}

    

