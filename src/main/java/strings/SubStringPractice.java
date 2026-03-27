public class SubStringPractice {


    public static void main(String[] args) {
        String s = "This is";
        System.out.println(reversesb(s));
    }

    public static String reversesb(String s){
        if (s.isEmpty()) {
            return s;
        }
			
        return reversesb(s.substring(1)) + s.charAt(0);
            
    }
    
}
