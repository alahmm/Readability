class Problem {
    public static void main(String[] args) {
/*        for (int i = 0; i < args.length; i += 2) {
            System.out.println(args[i] = args[i + 1]);
        }*/
/*        int index = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("test")) {
                index = i;
            }
        }
        System.out.println(index);*/
        int change = 0;
        boolean isExist = true;
        switch (args[0]) {
            case "+" -> {
                for (int i = 1; i < args.length; i++) {
                    change += Integer.parseInt(args[i]);
                }
            }
            case "*" -> {
                change = 1;
                for (int i = 1; i < args.length; i++) {
                    change *= Integer.parseInt(args[i]);
                }
            }
            case "-" -> {
                change = Integer.parseInt(args[1]);
                for (int i = 2; i < args.length; i++) {
                    change -= Integer.parseInt(args[i]);
                }
            }
            default -> isExist = false;
        }
        if (isExist) {
            System.out.println(Math.abs(change));
        } else {
            System.out.println("Unknown operator");
        }
/*        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("test")) {
                index = i;
            }
        }*/
    }
}
