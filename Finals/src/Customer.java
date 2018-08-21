import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;


public class Customer {
    ElectricAccount acct = new ElectricAccount();

    public void useElectricity_B(double kWh){
        acct.addkWh_B(kWh);
    }

    public void useElectricity_D(double kWh){
        acct.addkWh_D(kWh);
    }

    static int [][] arr;

    public static void main(String[] args){
        int i = 0;
        do{
            i+=2;
        }
        while(--i<5);
        System.out.println(i);
    }

    public static void repair(){
        System.out.println("2 GB");
    }
}

