/*Given a string containing alphanumeric characters. Find the sum of the numbers in that string.
Input:- aa123bb4
Output:- 127

Input:- bbb5cc5dd12
Output:- 22
 * https://www.geeksforgeeks.org/amazon-qa-role-support-engineer-intern-interview-experience/
 * 
 */

public class SumFromAplhanumeric {

    public static void main(String[] args) {
        String s = "aa123bb4";
        System.out.println(sumfromalpha(s));
        
    }

    public static int sumfromalpha(String s) {
        if (s.isEmpty()) return 0;
        String temp="0";
        int result=0;
        for (char c: s.toCharArray()){
            if(Character.isDigit(c)){
                temp=temp+c;
            }
    
            else{
                result = result + Integer.parseInt(temp);
                temp="0";
            }

        }
         // Add the final value remaining in temp
        return result+ Integer.parseInt(temp);
}

}
