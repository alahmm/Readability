package estimation;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void AgeEstimator(double score) {
        double score2 = Math.ceil(score);
        long score3 = (long)score2;
        System.out.printf("This text should be understood by %d-%d year-olds.%n", score3 + 4, score3 + 5);
    }

        public static int SyllableCounter(String string) {
            int counter = 0;
            final String regex = "[^aeiouyAEIOUY]?[aiouyAEIOUY][^aeiouyAEIOUY]|[^aeiouyAEIOUY]?[eE][^aeiouyAEIOUY .!,?]";

            final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                counter ++;
            }
            //System.out.println(counter);

            return counter;
        }

    public static void Estimator(String input) {
        double counter = 0;
        double counterC = 0;
        double counterS = 0;
        int counterP = 0;
        String regex = "[!.?]";
        String regexC = "[^ \\n\\t]";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            String s = "" + c;
            if (s.matches(regexC)) {
                counterC++;
            }
        }
        String[] list = input.split(regex);
        counterS += SyllableCounter(input);
        for (String variable : list
        ) {
            counter += Arrays.stream(variable.split(" ")) //split the sentence into words
                    .filter(s -> s.matches("[a-zA-Z0-9,()]+")) //filter only matching words
                    .count();
            String[] listOfPoly = variable.split(" ");
            for (String var : listOfPoly
                 ) {
                if (SyllableCounter(var) >= 2) {
                    counterP ++;
                }
            }

        }
        System.out.printf("Words: %d%n", (int) counter);
        System.out.printf("Sentences: %d%n", list.length);
        System.out.printf("Characters: %d%n", (int) counterC);
        System.out.printf("Syllables: %d%n",  counterS);

        double score = 4.71 * counterC / counter + 0.5 * counter / (double) list.length - 21.43;
        score = ((long) (score * 100)) / 100.0; // truncates down to 0
        double scoreFK = 0.39 * counter / (double) list.length + 11.8 * counterS / counter - 15.59;
        scoreFK= ((long) (scoreFK * 100)) / 100.0;

        System.out.printf("The score is: %.2f%n", score);
        AgeEstimator(score);
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
