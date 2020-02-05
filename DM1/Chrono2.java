public class Chrono2 {
    private int nbSecondes;

    public Chrono2() {
        nbSecondes = 0;
    }

    public int getHeures() { return nbSecondes / 3600; }
    public int getMinutes() { return nbSecondes / 60 % 60; }
    public int getSecondes() { return nbSecondes % 60; }

    public void setSecondes(int sec) { nbSecondes += sec; }
    public void setMinutes(int min) { nbSecondes += min * 60; }
    public void setHeures(int h) { nbSecondes += h * 3600; }

    public void rebours(int s) {
        nbSecondes -= s;
    }
}
