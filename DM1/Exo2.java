public class Exo2 {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 3);
        f1.addFraction(f2);
        System.out.println("1/2 + 2/3 = " + f1);
        
        f1 = new Fraction(7, 8);
        f1.add(2);
        System.out.println("7/8 + 2 = " + f1);

        f1 = new Fraction(7, 8);
        f1.sub(1);
        System.out.println("7/8 - 1 = " + f1);

        f1 = new Fraction(123, 456);
        f2 = new Fraction(789, 10);
        f1.addFraction(f2);
        f1.add(11);
        f1.mult(12);
        f1.reduire();
        System.out.println("(123/456+789/10 +11)*12 = " + f2);
    }
}