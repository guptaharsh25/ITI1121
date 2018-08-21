public class DoorLock{
    private static int MAX_NUM_OF_ATTEMPTS = 3;

    private Combination combination;
    private boolean isOpen;
    private boolean isDoorLockActive;
    private int numAttempts;

    public DoorLock(Combination combination){
        this.combination = combination;
        this.isOpen = false;
        this.isDoorLockActive = true;
        this.numAttempts = 0;
    }

    public boolean isDoorOpen(){
        return this.isOpen;
    }

    public boolean isActivated(){
        return this.isDoorLockActive;
    }

    public void activate (Combination c){
        if (c.equals(this.combination)){
            this.isDoorLockActive = true;
        }
    }

    public boolean open(Combination combination){
        if(this.isDoorLockActive){
            if (combination.equals(this.combination)){
                this.isOpen = true;
                this.numAttempts = 0;

                return true;
            }
            else{
                this.numAttempts++;
                return false;
            }

            if(this.numAttempts == MAX_NUM_OF_ATTEMPTS){
                this.isDoorLockActive = false;
                return false;
            }
        }
    }

}