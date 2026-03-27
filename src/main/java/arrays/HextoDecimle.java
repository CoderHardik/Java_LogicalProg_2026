/*
A10 = 10x16^2 + 1 x16^1 + 0x16^0
Make string hex and each element location represent value 
hexconvert = "0123456789ABCDEF"; 
[A,1,0]
int res
res = n.charAt[i]
*/

public class HextoDecimle {
    public static void main(String[] args) {
        String n = "A10";
        int result = hextodex(n);
        System.out.println(result);
    }

    public static int hextodex(String n){
        String hexconvert = "0123456789ABCDEF"; // here each element position represent it's value
        String ip = n.toUpperCase();
        int result=0;
        for(int i=0; i<ip.length(); i++){

            char a = ip.charAt(i);
            int temp= hexconvert.indexOf(a);
            result = (16*result)+temp; // try to understand this logic. Basically it should be temp*16powi but if look you will have to multiply 16 next time everytime so they are multiplying it with result itself
        }
        
        return result;
    }


}
