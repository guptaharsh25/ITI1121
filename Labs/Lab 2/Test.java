public class Test{
    public static void main (String [] args){
        combinationTester();
    }

    public static void combinationTester(){
        Combination c1, c2, c3;

        //instantiating new objects
        c1 = new Combination(1, 2, 3);
        c2 = new Combination(1, 2, 3);
        c3 = new Combination(3, 2, 1);

        //testing .equals method
        System.out.println(c1.equals(c2));
        System.out.println(c1.equals(c3));

        //testing .toString method
        System.out.println(c1);
    }
}