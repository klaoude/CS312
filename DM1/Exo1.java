//package com.journaldev.threads;

public class Exo1 {
    static boolean isChronosOver(Chrono c1, Chrono2 c2) {
        if(c1.getSecondes() == 0 && c1.getMinutes() == 0 && c1.getHeures() == 0)
            return true;
        if(c2.getSecondes() == 0 && c2.getMinutes() == 0 && c2.getHeures() == 0)
            return true;
        return false;
    }

    public static void main(String[] args) {
        Chrono c1 = new Chrono();
        c1.setMinutes(30);
        c1.setHeures(1);

        Chrono2 c2 = new Chrono2();
        c2.setMinutes(30);
        c2.setHeures(1);

        while(!isChronosOver(c1, c2)) {
            System.out.printf("Chrono1 (%02d:%02d:%02d)\n", c1.getHeures(), c1.getMinutes(), c1.getSecondes());
            System.out.printf("Chrono2 (%02d:%02d:%02d)\n\n", c2.getHeures(), c2.getMinutes(), c2.getSecondes());
            c1.rebours(1);
            c2.rebours(1);
            try {
                java.lang.Thread.sleep(1000);
            }
            catch (Exception e) { ; }
        }

        System.out.printf("Chrono1 (%02d:%02d:%02d)\n", c1.getHeures(), c1.getMinutes(), c1.getSecondes());
        System.out.printf("Chrono2 (%02d:%02d:%02d)\n\n", c2.getHeures(), c2.getMinutes(), c2.getSecondes());

        System.out.println("Fin du cours !!");
    }
}