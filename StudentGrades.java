import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalMarks = 0;
        int n = 5;
        int[] marks = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Please enter the marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt(); 
            totalMarks += marks[i];      
        }
        double averagePercentage = (double) totalMarks / n;
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';  
            System.out.println("Excellent");
        } else if (averagePercentage >= 80) {
            grade = 'B'; 
            System.out.println("Very Good");
        } else if (averagePercentage >= 70) {
            grade = 'C';  
            System.out.println("Good");
        } else if (averagePercentage >= 60) {
            grade = 'D';
            System.out.println("Fair");
        } else {
            grade = 'F';
            System.out.println("Fail");
        }
        System.out.println("\n----- Results -----");
        System.out.println("Total Marks Obtained: " + totalMarks + " out of " + (n * 100));
        System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        System.out.println("Grade: " + grade);
        scanner.close();
    }
}

    

