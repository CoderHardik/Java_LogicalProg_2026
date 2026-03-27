public class FindTwoMax {
    public static void main(String[] args) {
        int a[] = {1,2,3,4,8,6,7};
        findmax(a);
    }

    public static void findmax(int a[]){
        int max=0, sec_max=0;
		
		for(int i=0; i<a.length; i++) {
			if(max<a[i]) {
				sec_max=max;
				max=a[i];
			}
			else if(sec_max<a[i]) {       //  <-- This condition is important because last element can be second max but not max and only this line can catch it
				sec_max=a[i];
			}
		}// end of for
		
	System.out.println("Maximum number in given array is: "+max+" and second maximum is: "+sec_max);	
    }
}
