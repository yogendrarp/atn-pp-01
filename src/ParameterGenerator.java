import java.util.Scanner;

public class ParameterGenerator {
    public static void main(String[] args) {
        //From the project guidelines, fix number of nodes to 21
        final int NODES = 21;
        final int STUDENT_ID_LENGTH = 10;
        //Repeat the first digit of the student id
        final int repeatDigitPosition = 0;
        StringBuilder studentIdBuilder = new StringBuilder();
        int k = 0;
        Scanner inputScanner = new Scanner(System.in);
        String studentId = "";

        while (studentId.length() != STUDENT_ID_LENGTH) {
            System.out.println("Enter your 10 digit Student id, ex 2021565751");
            studentId = inputScanner.nextLine();
        }
        studentIdBuilder.append(studentId);
        studentIdBuilder.append(studentId);
        studentIdBuilder.append(studentId.charAt(repeatDigitPosition));
        char[] digits = studentIdBuilder.toString().toCharArray();

        digits[NODES - 1] = digits[0];

        System.out.println(digits);

        while (k < 3 || k > 14) {
            System.out.println("Enter the value for k between 3 and 14,inclusive");
            k = inputScanner.nextInt();
        }

        int[][] trafficDemands = new int[NODES][NODES];
        for (int i = 0; i < NODES; i++) {
            for (int j = 0; j < NODES; j++) {
                trafficDemands[i][j] = Math.abs(digits[i] - digits[j]);
            }
        }

    }
}
