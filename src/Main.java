public class Main {
    public static void main(String[] args) {
/*        String regex = "0\\.[0-9]{2}9{0,2}5*";
        System.out.println("0.129955".matches(regex));
        System.out.println("0.1295".matches(regex));
        System.out.println("0.1299".matches(regex));
        System.out.println("0.125".matches(regex));
        System.out.println("0.129995".matches(regex));*/
        String regex1 = "ab+a";
        String regex2 = "ab*a";
        String regex3 = "ab{0,3}a";
        String regex4 = "ab{3,}a";
        System.out.println("aa".matches(regex1));
        System.out.println("aba".matches(regex1));
        System.out.println("abbba".matches(regex1));
        System.out.println("abbbbba".matches(regex1));
        System.out.println("aa".matches(regex2));
        System.out.println("aba".matches(regex2));
        System.out.println("abbba".matches(regex2));
        System.out.println("abbbbba".matches(regex2));
        System.out.println("aa".matches(regex3));
        System.out.println("aba".matches(regex3));
        System.out.println("abbba".matches(regex3));
        System.out.println("abbbbba".matches(regex3));
        System.out.println("aa".matches(regex4));
        System.out.println("aba".matches(regex4));
        System.out.println("abbba".matches(regex4));
        System.out.println("abbbbba".matches(regex4));

    }
}