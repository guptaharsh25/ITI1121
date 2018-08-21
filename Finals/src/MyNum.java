import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyNum extends MyInt implements Printer{

    public MyNum(int value){
        super(value);
    }

    public String stringer() {
        return "" + this.getValue();
    }

    static int x = 0;

    public static void main(String[] args){
        String date = LocalDate.parse("2014-05-04").format(DateTimeFormatter.ISO_DATE);
        System.out.println(date);
    }


}
