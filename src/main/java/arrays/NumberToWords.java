public class NumberToWords {
    public static void main(String[] args) {
        int n = 1111;
        System.out.println(numtoword(n));
    }

    public static String numtoword(int n){

        String[] units = {"","One","Two","three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        String [] tens = {"","", "twenty","thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"}; // first 2 empty represent 0 and 10. 10 already covered in above

        if (n==0) return "zero";

        if (n<20) return units[n];

        
        if (n<100){
            String res = tens[n/10];
            if (n%10!=0){
                res = res+" "+ units[n%10];
            }
            else{
                res = res+"";
            }
        }

        if (n<1000){
            if (n%100!=0){
                return units[n/100]+" hundred "+numtoword(n%100);
            }
            else{
                return units[n/100] +" hundred ";
            }
        }

        if (n<10000){
            if (n%1000!=0){
                return units[n/1000]+" thousand "+numtoword(n%1000);
            }
            else{
                return units[n/1000]+" thousand ";
            }
        }

        return "number to large";
    }
    
}
