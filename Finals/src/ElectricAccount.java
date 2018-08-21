public class ElectricAccount {
    private double kWh;
    private double rate = 0.07;
    private double bill;

    float f;

    public void addkWh_B(double kWh){
        if(kWh > 0){
            this.kWh += kWh;
            this.bill = this.kWh * this.rate;
        }
    }

    public void addkWh_D(double kWh){
        if(kWh > 0){
            this.kWh += kWh;
            setBill(this.kWh);
        }
    }

    public void setBill(double kWh){
        bill = kWh * rate;
    }

    public double getBill(){
        return bill;
    }
}
