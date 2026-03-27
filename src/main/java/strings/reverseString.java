import java.util.ArrayList;

public class reverseString {
 
    public static void main(String[] args) {
        String input = "This is";
        System.out.println(revString(input));
    }

/* 
Following code with Char at
    public static String revString(String input){

        String rev ="";
        for (int i = input.length()-1; i>=0; i--){
            rev = rev+""+input.charAt(i);
        }
       
        return rev;
        
    }
*/
/* 
 Following code with recursion
    public static String revString(String input){
    
        if(input.isEmpty()){
            return input;
        }

        else
        {
            return revString(input.substring(1))+input.charAt(0);
        }
        return input;
    }

    Here both above method's complexity is O(n²) because there is one for loop and in each loop we are creating new string
    so it each time new string cause n and for loop n which is n2

    String builder is clean way because it it had append method and does not create new string each time
*/

public static String revString(String input){

    if(input==null){
        return null;
    }

    StringBuilder sb = new StringBuilder();
    for (int i =input.length()-1; i<=0; i--){
        sb.append(input.charAt(i));
    }
    return sb.toString();

}


}
