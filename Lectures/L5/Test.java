public class Test{

    public static void main(String [] args){
        Ticket t1, t2, t3;

        t1 = new Ticket();

        System.out.println(t1.getSerial());

        t2 = new Ticket();

        System.out.println(t1.getSerial());
        System.out.println(t2.getSerial());

        t3 = new Ticket();
        System.out.println(t1.getSerial());
        System.out.println(t2.getSerial());
        System.out.println(t3.getSerial());
    }
}