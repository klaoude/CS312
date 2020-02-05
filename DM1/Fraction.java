public class Fraction {
    private int denominateur;
    private int numerateur;

    private int pgcd(int x, int y) {
        int r = 0;
        while(y != 0) {
            r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    private int ppcm(int x, int y) {
        int p = x * y;
        while(x != y) {
            if(x < y)
                y -= x;
            else
                x -= y;
        }
        return p / x;
    }

    public int getDenominateur() { return denominateur; }
    public int getNumerateur() { return numerateur; }

    public Fraction(int den, int num) {
        denominateur = den;
        numerateur = num;
    }

    public void add(int x) {
        denominateur += numerateur * x;
    }

    public void sub(int x) {
        denominateur -= numerateur * x;
    }

    public void mult(int x) {
        denominateur *= x;
    }

    public void reduire() {
        int pcd = pgcd(denominateur, numerateur);
        denominateur /= pcd;
        numerateur /= pcd;
    }

    public void addFraction(Fraction f) {
        int pcm = ppcm(numerateur, f.getNumerateur());
        Fraction f1 = new Fraction(denominateur * pcm / numerateur, pcm);
        Fraction f2 = new Fraction(f.getDenominateur() * pcm / f.getNumerateur(), pcm);
        numerateur = pcm;
        denominateur = f1.getDenominateur() + f2.getDenominateur();
    }

    public void multFraction(Fraction f) {
        denominateur *= f.getDenominateur();
        numerateur *= f.getNumerateur();
    }

    @Override
    public String toString() {
        return getDenominateur() + "/" + getNumerateur();
    }
}