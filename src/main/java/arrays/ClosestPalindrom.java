/*
Closest palindrom-
number -127
Approach:
- Firs make string of Integer
- Find the length of string
- Find the mid by l+1/2
- find 3 candidates by calling method (prefix, length is even or odd = l/%2==0)
candidate 0 = method (prefix, bool even)
cadidate 1 = method (prefix+1, bpol even)
cadidate 2 = method (prefix-1, bpol even )

- method to find palindrome (prefix, even)
String prefix = string.substring (0,prefix)
String Builder rev = prefix.reverse()
if (even){
rev}
else
{
    rev = rev.subString(0)}

    return (prefix+rev)
 */

public class ClosestPalindrom {
    public static void main(String[] args) {
        int i = 1222;
        String s = String.valueOf(i); //Convert Int to string
        int length =s.length(); //Length of string
        int mid = (length+1)/2; //Mid digit of number
        long prefix = Long.parseLong(s.substring(0,mid)); //find first digit upto mid (to reverse)
        long candidate [] = new long [3]; //Create final candidate array
        candidate[0] = palindrom(prefix, length%2==0); // call methods to get result
        candidate[1] = palindrom(prefix+1, length%2==0);
        candidate[2] = palindrom(prefix-1, length%2==0);

        long closest = candidate[0];
        long mincand = Math.abs(i-closest);
        long res=0;
        for (long t: candidate){
            res = Math.abs(t-i);
            if(res!=0 && res<mincand){
                mincand=res;
                closest = t;
            }
        }
        System.out.println(closest+" is the closest palindrom");
    }

    public static long palindrom(long prefix, boolean even){
        String res = String.valueOf(prefix); //convert first digits to string
        String rev= new StringBuilder(res).reverse().toString(); // reverse string
        String secondhalf="";
        long palindrom=0; 
        if (even){
            secondhalf=rev; // if number of i/p digit even then reverse is number
        }
        else{
            secondhalf=rev.substring(1); // if num of digit odd then remove first digit ; 123-> 12 prefix-> 21 is reverse ->1 so result '12'+'1'=121
        }
        palindrom = Long.parseLong(prefix+secondhalf);

        return palindrom;
    }
        

}
