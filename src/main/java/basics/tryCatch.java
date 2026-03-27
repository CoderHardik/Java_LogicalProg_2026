public class tryCatch {

    /*Note - In this program, if you only run part 1 it will error and execution will break due to arithmetic excetion. 
    To control we added try catch which will take care of exception in try section 
    and catch exception that we know of and let the execution of code complete.
    */
    public static void main (String [] Args){
        int a,b;
        a=100;
        b=0;
        /* Start of Part 1
        System.out.println("Division"+(a/b));
        System.out.println("Execution complete");
        End of part 1 */

//Part 2
    try {
        System.out.println("Division"+(a/b));
    } catch(Exception e){
        System.out.println("exception is "+e);
    }

    System.out.println("execution completed");
}
}

/* Error and Exception
Types of exception
1. Checked Exception - Show up during compile time. Coder has to fix this or else program will not run
2. Unchecked Exception - Show up during run time.

Error:
Error is not in User's hand.
example - Memory leak

If you do not know if error will occur or exception in try catch then user Throwable (it is super class of error and exception)
ex. catch(Throwable e)
*/

/* Multiple Try Catch

try
Catch 1
Catch 2
Catch 3

*/

/* Finally

try()
catch ()
Finally()

Why Finally
- In above code, it might go through try or catch but it will surely go through finally

*/

/* Final
 
public class sample{
public static final int i=25;

public static void main (String [] args){
    i =90; // Here code will throw error that final variable cannot be changed
}
}

In short, as name suggest, for final variable - you can not change variable
*/


/* Final vs Finally

- Final is keyword
- Finally is used in try, catch, finally- part of error handling
*/