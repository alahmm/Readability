package estimation;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void AgeEstimator(double score) {
        double score2 = Math.ceil(score);
        long score3 = (long)score2;
        System.out.printf("This text should be understood by %d-%d year-olds.%n", score3 + 4, score3 + 5);
    }

    public static void Estimator(String input) {
        double counter = 0;
        double counterC = 0;
        String regex = "[!.?]";
        String regexC = "[^ \\n\\t]";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            String s = "" + c;
            if (s.matches(regexC)) {
                counterC++;
            }
        }
/*        Arrays.stream(variable.split("[ ,]")) //split the sentence into words
                .filter(s -> s.matches("[\\w-]+")) //filter only matching words
                .count();*/
        String[] list = input.split(regex);

        for (String variable : list
        ) {
            counter += Arrays.stream(variable.split(" ")) //split the sentence into words
                    .filter(s -> s.matches("[a-zA-Z0-9,()]+")) //filter only matching words
                    .count();
        }
        System.out.printf("Words: %d%n", (int) counter);
        System.out.printf("Sentences: %d%n", list.length);
        System.out.printf("Characters: %d%n", (int) counterC);
        double score = 4.71 * counterC / counter + 0.5 * counter / (double) list.length - 21.43;
        score = ((long) (score * 100)) / 100.0; // truncates down to 0
        System.out.printf("The score is: %.2f%n", score);
        AgeEstimator(score);
/*        double d = (double)(counter/list.length);
        if (d > 10) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }*/
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) throws IOException {
        System.out.println("the text is:");
/*        Scanner scanner = new Scanner(new File(args[0]));
        while (scanner.hasNext()) {

        String input = scanner.nextLine();
        Estimator(input);

        }
        scanner.close();*/
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Estimator(input);
    }
}
