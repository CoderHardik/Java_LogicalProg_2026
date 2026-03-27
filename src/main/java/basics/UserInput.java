import java.util.Scanner;

public class UserInput {
    public static void main (String []Args ){
        Scanner scan = new Scanner (System.in);
        System.out.println("Enter number: ");
        int UserInput_number = scan.nextInt();
        System.out.println("The entered number is; ");
        System.out.println(UserInput_number);
    }
}
