public class SumUptoOneDigit {
    public static void main(String[] args) {
        int num = 199;
        int result;

        if (num == 0) {
            result = 0;
        } else {
            // The formula 1 + (num - 1) % 9 handles the case 
            // where num is a multiple of 9 (like 18 or 27).
            result = 1 + (num - 1) % 9;
        }

        System.out.println("Final Single Digit (Mod 9): " + result);
    }
}

