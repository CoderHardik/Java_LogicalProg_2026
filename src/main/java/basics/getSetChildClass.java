public class getSetChildClass extends getSetParentClass {
    public static void main(String [] args){
        getSetChildClass g = new getSetChildClass();
        System.out.println(g.getAge()); // Here there will be no value assigned so no result
        g.setAge(30);
        System.out.println(g.getAge());

    }
    
}


