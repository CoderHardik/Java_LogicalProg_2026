public class getSetParentClass {
private static int age ;
/* What is encapsulation?
A. Defining or Hiding data as private is encapsulation
Or
Wrapping up data or function in single entity and it can be achieved by class
 */
    public static void main (String [] args){
        age=52;
    }

    public void setAge(int age){
        this.age=age;  // Here, this keyword is to differentiate between local and global variable if they have same name
    }

    public int getAge(){
        return age;
    }
    
}
