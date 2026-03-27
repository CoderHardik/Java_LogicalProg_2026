public class InheritedClassForMethodOverRiding extends MethodOverRiding{
    public static void main(String [] args){
        InheritedClassForMethodOverRiding i = new InheritedClassForMethodOverRiding();
        i.makecall();
        i.sendMessage();

    }

    // Following method is example of methodoveriding. It will basically over ride earlier parent method and implement in this methid in class
    public void makecall(){
        System.out.print("iPhone is making call");
    }

    /* Remember that you cannot make another child class that inherit this child class because multiple inheritance is not allowed in Java
    Reason is diamond problem where java will not know which parent class it should refer to when you run a method.
     */
}
    

