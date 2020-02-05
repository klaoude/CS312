public class Chrono {
    private int heures;
    private int minutes;
    private int secondes;

    public Chrono() {
        heures = 0;
        minutes = 0;
        secondes = 0;
    }

    public int getHeures() { return heures; }
    public int getMinutes() { return minutes; }
    public int getSecondes() { return secondes; }

    public void setSecondes(int sec) { secondes = sec; }
    public void setMinutes(int min) { minutes = min; }
    public void setHeures(int h) { heures = h; }

    public void rebours(int s) {
        secondes -= s;
        if(secondes < 0)
        {
            secondes = 60 - java.lang.Math.abs(secondes);
            minutes--;
        }
        if(minutes < 0)
        {
            minutes = 60 - java.lang.Math.abs(minutes);
            heures--;
        }
    }
}