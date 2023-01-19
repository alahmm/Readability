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
    public static long AgeEstimator(double score) {
        double score2 = Math.ceil(score);
        long score3 = (long)score2;
        //System.out.printf("This text should be understood by %d-%d year-olds.%n", score3 + 4, score3 + 5);
        return score3 + 5;
    }

        public static int SyllableCounter(String string) {
            int counter = 0;
            final String regex = "[^aeiouyAEIOUY]?[aiouyAIOUY][^aeiouyAEIOUY]|[eE][^aeiouyAEIOUY .!,?]|[aeiouyAEIOUY][aeiouyAEIOUY]|[^aeiouyAEIOUY][aiouyAEIOUY]|/\\b(\\w)\\b/";

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
        double counterP = 0;
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
                if (SyllableCounter(var) > 2) {
                    counterP ++;
                }
                }
            }

        System.out.printf("Words: %d%n", (int) counter);
        System.out.printf("Sentences: %d%n", list.length);
        System.out.printf("Characters: %d%n", (int) counterC);
        System.out.printf("Syllables: %d%n",  (int)counterS);
        System.out.printf("Polysyllables: %d%n",  (int)counterP);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        Scanner scanner = new Scanner(System.in);
        String input2 = scanner.next();


        double score = 4.71 * counterC / counter + 0.5 * counter / (double) list.length - 21.43;
        score = ((long) (score * 100)) / 100.0; // truncates down to 0
        double scoreFK = 0.39 * counter / (double) list.length + 11.8 * counterS / counter - 15.59;
        scoreFK= ((long) (scoreFK * 100)) / 100.0;
        double scoreSMOG = 1.043 * Math.sqrt(counterP * 30 / (double) list.length) + 3.1291;
        scoreSMOG= ((long) (scoreSMOG * 100)) / 100.0;
        double L = 100 * counterC / counter;
        double S = (double) list.length * 100 / counter;
        double scoreCL = 0.0588 * L - 0.296 * S - 15.8;
        scoreCL= ((long) (scoreCL * 100)) / 100.0;
        System.out.println();
        if (input2.equals("ARI")) {
            System.out.printf("Automated Readability Index: %.2f (about %d-year-olds)%n", score, AgeEstimator(score));
        } else if (input2.equals("FK")) {
            System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds)%n", scoreFK, AgeEstimator(scoreFK));
        } else if (input2.equals("SMOG")) {
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds)%n", scoreSMOG, AgeEstimator(scoreSMOG));
        } else if (input2.equals("CL")) {
            System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds)%n", scoreCL, AgeEstimator(scoreCL));
        } else {
            System.out.printf("Automated Readability Index: %.2f (about %d-year-olds)%n", score, AgeEstimator(score));
            System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds)%n", scoreFK, AgeEstimator(scoreFK));
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds)%n", scoreSMOG, AgeEstimator(scoreSMOG));
            System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds)%n", scoreCL, AgeEstimator(scoreCL));
            System.out.println();
            double var = (double) (AgeEstimator(score) + AgeEstimator(scoreFK) + AgeEstimator(scoreSMOG) + AgeEstimator(scoreCL))/ 4;
            System.out.printf("This text should be understood in average by %.2f-year-olds.", var);
        }


    }

    public static void main(String[] args) throws IOException {
        System.out.println("the text is:");
        Scanner scanner = new Scanner(new File(args[0]));
        while (scanner.hasNext()) {

        String input = scanner.nextLine();
        Estimator(input);

        }
        scanner.close();
/*        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Estimator(input);*/
    }
}
