public class SecurityAgent{
    private DoorLock doorLock;
    private Combination combination;

    public SecurityAgent(){
        Combination combination = new Combination(1,2,3);
        DoorLock doorLock = new DoorLock(combination);

        this.doorLock = doorLock;
        this.combination = combination;
    }

    public DoorLock getDoorLock(){
        return this.doorLock;
    }

    public void activateDoorLock(){
        this.doorLock.activate(this.combination);
    }


}