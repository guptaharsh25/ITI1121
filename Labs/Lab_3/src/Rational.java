public class Rational {
    private int numerator;

    private int denominator;

    public Rational(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;

        this.reduce();
    }

    public Rational(int numerator){
        this.numerator = numerator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator(){
        return this.denominator;
    }

    public Rational plus(Rational other){
        int newN = this.getNumerator()*other.getDenominator() + other.getNumerator()*this.getDenominator();
        int newD = this.getDenominator()*other.getDenominator();

        return new Rational(newN, newD);
    }

    public static Rational plus(Rational r1, Rational r2){
        int newN = r1.getNumerator()*r2.getDenominator() + r2.getNumerator()*r1.getDenominator();
        int newD = r1.getDenominator()*r2.getDenominator();

        return new Rational(newN, newD);
    }

    private static int gcd(int n1, int n2){
        int gcd = 1;

        for(int i = 1; i <= n1 && i <= n2; ++i)
        {
            // Checks if i is factor of both integers
            if(n1 % i==0 && n2 % i==0)
                gcd = i;
        }

        return gcd;
    }

    private void reduce(){
        for(int i = 2; i < this.getNumerator() && i < this.getDenominator();i++){
            if(this.getNumerator() % i == 0 && this.getDenominator() % i == 0){
                this.numerator = (int) this.numerator/i;
                this.denominator = (int) this.denominator/i;
            }
        }

    }

    public boolean equals(Rational o){
        return ((this.getNumerator() == o.getNumerator()) && (this.getDenominator() == o.getDenominator()));
    }

    public String toString(){
        if(this.getDenominator() == 1){
            return this.getNumerator() + "";
        }
        else{
            return this.getNumerator() + "/" + this.getDenominator();
        }
    }


    public int compareTo(Rational o){
        double thisRational = this.getNumerator()/this.getDenominator();
        double otherRational = o.getNumerator()/o.getDenominator();

        if (thisRational < otherRational){
            return -1;
        }
        else if (thisRational == otherRational){
            return 0;
        }
        else{
            return 1;
        }
    }
}
