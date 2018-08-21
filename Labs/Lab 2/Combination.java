public class Combination{
    private int num1;
    private int num2;
    private int num3;

    public Combination(int num1, int num2, int num3){
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public boolean equals(Combination other){
        return ((this.num1 == other.num1) && (this.num2 == other.num2) && (this.num3 == other.num3));
    }

    public String toString(){
        return "" + this.num1 + ":" + this.num2 + ":" + this.num3;
    }
}