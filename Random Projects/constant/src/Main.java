public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Constant c;
        c = new Constant("golden ratio", 1.61803399);
        System.out.println(c.getName());

        Integer i, alias;
        i = new Integer(33);
        alias = i;

        System.out.println(i.intValue());
        System.out.println(alias.intValue());
        i = new Integer(5);
        System.out.println(i.intValue());
        System.out.println(alias.intValue());
    }
}
