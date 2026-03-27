public class ReverseStringWithRec {
    public static void main(String[] args) {

        String st = "I am me!";
        System.out.println(revstring(st));
        
    }

    public static String revstring(String s){

        StringBuilder sb= new StringBuilder();
        String result = "";
        if(s.isEmpty()) 
            return s;

        else{
            sb.append(revstring(s.substring(1))+s.charAt(0));
            result= sb.toString();
            return result;

        }
    }
    

}
