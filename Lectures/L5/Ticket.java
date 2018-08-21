//Objective: we need to create a ticketing system where each ticket has a unique serial number.

public class Ticket{

    //This variable will be shared with every instance of the class.
    private static int nextSerial = 100;

    private int serial;

    public Ticket(){
        serial = nextSerial;
        nextSerial++;

    }

    public int getSerial(){
        return serial;
    }
}