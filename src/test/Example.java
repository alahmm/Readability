package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
    public static void main(String[] args) {
        int counter = 0;
        final String regex = "[^aeiouyAEIOUY]?[aiouyAEIOUY][^aeiouyAEIOUY]|[^aeiouyAEIOUY]?[eE][^aeiouyAEIOUY .!,?]";
        final String string = "hallo wikipedia";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            //System.out.println("Full match: " + matcher.group(0));
            //counter ++;
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
                counter ++;
            }
        }
        System.out.println(counter);
    }
}