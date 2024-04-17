public class Fraction {
    long num;
    long den;
    public Fraction(String input){
        if (input.contains(".")){
            int nulls = input.length() - input.indexOf('.') - 1;
            input = input.replace(".","");
            input += "/1";
            for (int i = 0; i < nulls; i ++){
                input += "0";
            }
        }
        if (input.contains("/")){
            String[] input_array = input.split("/");
            num = Long.parseLong(input_array[0]);
            den = Long.parseLong(input_array[1]);
        }
        else{
            num = Long.parseLong(input);
            den = 1;
        }
        FractionReduce();
    }
    public void PrintFraction(){
        if (this.num == 0){
            System.out.print(0 + " ");
        }
        else if (this.den == 1){
            System.out.print(this.num + " ");
        }
        else {
            System.out.print(this.num + "/" + this.den + " ");
        };
    }
    // сокраение
    public void FractionReduce(){
        long gcd = gcd(this.num, this.den);
        this.num /= gcd;
        this.den /= gcd;
    }
    public long gcd(long a, long b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
    // сложение
    public Fraction FractionAddidion(Fraction summand1, Fraction summand2){
        Fraction c = new Fraction("0");
        c.num = summand1.num*summand2.den + summand2.num*summand1.den;
        c.den = summand1.den*summand2.den;
        Fraction fraction = new Fraction(Long.toString(c.num) + "/" + Long.toString(c.den));
        fraction.FractionReduce();
        return fraction;
    }
    // вычитание
    public Fraction FractionSubtraction(Fraction minuend, Fraction subtrahend){
        Fraction c = new Fraction("0");
        c.num = minuend.num*subtrahend.den - subtrahend.num*minuend.den;
        c.den = minuend.den*subtrahend.den;
        Fraction fraction = new Fraction(Long.toString(c.num) + "/" + Long.toString(c.den));
        fraction.FractionReduce();
        return fraction;
    }

    // умножение
    public Fraction FractionMultiplication(Fraction factor1, Fraction factor2){
        Fraction c = new Fraction("0");
        c.num = factor1.num*factor2.num;
        c.den = factor1.den*factor2.den;
        Fraction fraction = new Fraction(Long.toString(c.num) + "/" + Long.toString(c.den));
        fraction.FractionReduce();
        return fraction;
    }
    // деление
    public Fraction FractionDivision(Fraction dividend, Fraction divisor){
        Fraction c = new Fraction("0");
        if (divisor.num != 0) {
            c.num = dividend.num * divisor.den;
            c.den = dividend.den * divisor.num;
            Fraction fraction = new Fraction(Long.toString(c.num) + "/" + Long.toString(c.den));
            fraction.FractionReduce();
            return fraction;
        }
        else{
            return null;
        }
    }
    public boolean Eqal(Fraction a){
        boolean flag = false;
        if (this.den*this.num == a.den*a.num){
            flag = true;
        }
        return flag;
    }
    // Если a>b: true
    public boolean FractionGreaterThan(Fraction a, Fraction b){
        if (a.LessThanZero() & b.LessThanZero()){
            return (FractionSubtraction(a.abc(),b.abc())).LessThanZero();
        }
        else if (!(a.LessThanZero()) & !(b.LessThanZero())){
            return !((FractionSubtraction(a,b)).LessThanZero());
        }
        else {
            return !(a.LessThanZero());
        }
    }
    public Fraction abc(){
        return new Fraction(Long.toString(this.num).replace("-", ""));
    }
    // Меньше 0
    public boolean LessThanZero(){
        return Long.toString(this.num).contains("-") | Long.toString(this.den).contains("-");
    }

}
